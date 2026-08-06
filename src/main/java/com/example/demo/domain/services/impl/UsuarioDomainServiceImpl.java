package com.example.demo.domain.services.impl;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.AutenticarUsuarioRequestDto;
import com.example.demo.application.dtos.AutenticarUsuarioResponseDto;
import com.example.demo.application.dtos.CriarUsuarioRequestDto;
import com.example.demo.application.dtos.CriarUsuarioResponseDto;
import com.example.demo.domain.exceptions.CredenciaisInvalidasException;
import com.example.demo.domain.exceptions.UsuarioComEmailDuplicadoException;
import com.example.demo.domain.models.entities.Usuario;
import com.example.demo.domain.services.interfaces.UsuarioDomainService;
import com.example.demo.infrastructure.components.JwtTokenComponent;
import com.example.demo.infrastructure.components.SHA256Component;
import com.example.demo.infrastructure.repositories.PerfilRepository;
import com.example.demo.infrastructure.repositories.UsuarioRepository;

@Service
public class UsuarioDomainServiceImpl implements UsuarioDomainService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private SHA256Component sha256Component;

	@Autowired
	private JwtTokenComponent jwtTokenComponent;

	@Override
	public CriarUsuarioResponseDto criarUsuario(CriarUsuarioRequestDto request) {

		if (usuarioRepository.findByEmail(request.getEmail()) != null)
			throw new UsuarioComEmailDuplicadoException();

		var usuario = new Usuario();
		usuario.setId(UUID.randomUUID());
		usuario.setNome(request.getNome());
		usuario.setEmail(request.getEmail());
		usuario.setSenha(sha256Component.encrypt(request.getSenha()));
		usuario.setPerfil(perfilRepository.findByNome("Operador"));

		usuarioRepository.save(usuario);

		var response = new CriarUsuarioResponseDto();
		response.setId(usuario.getId());
		response.setNome(usuario.getNome());
		response.setEmail(usuario.getEmail());
		response.setPerfil(usuario.getPerfil().getNome());
		response.setDataCriacao(Instant.now());

		return response;
	}

	@Override
	public AutenticarUsuarioResponseDto autenticarUsuario(AutenticarUsuarioRequestDto request) {

		var usuario = usuarioRepository.findByEmailAndSenha(request.getEmail(),
				sha256Component.encrypt(request.getSenha()));

		if (usuario == null)
			throw new CredenciaisInvalidasException();

		var response = new AutenticarUsuarioResponseDto();
		response.setId(usuario.getId());
		response.setNome(usuario.getNome());
		response.setEmail(usuario.getEmail());
		response.setPerfil(usuario.getPerfil().getNome());
		response.setToken(jwtTokenComponent.getToken(usuario));

		return response;
	}
}
