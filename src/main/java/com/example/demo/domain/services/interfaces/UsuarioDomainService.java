package com.example.demo.domain.services.interfaces;

import com.example.demo.application.dtos.AutenticarUsuarioRequestDto;
import com.example.demo.application.dtos.AutenticarUsuarioResponseDto;
import com.example.demo.application.dtos.CriarUsuarioRequestDto;
import com.example.demo.application.dtos.CriarUsuarioResponseDto;

public interface UsuarioDomainService {

	public CriarUsuarioResponseDto criarUsuario(CriarUsuarioRequestDto request);

	public AutenticarUsuarioResponseDto autenticarUsuario(AutenticarUsuarioRequestDto request);

}
