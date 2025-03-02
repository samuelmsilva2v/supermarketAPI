package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.CategoriaRequestDto;
import com.example.demo.application.dtos.CategoriaResponseDto;
import com.example.demo.domain.models.entities.Categoria;
import com.example.demo.domain.services.interfaces.CategoriaDomainService;
import com.example.demo.infrastructure.repositories.CategoriaRepository;

@Service
public class CategoriaDomainServiceImpl implements CategoriaDomainService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String registrarCategoria(CategoriaRequestDto request) {

		var categoria = new Categoria();
		categoria.setId(UUID.randomUUID());
		categoria.setNome(request.getNome());

		categoriaRepository.save(categoria);

		return "Categoria registrada com sucesso!";
	}

	@Override
	public CategoriaResponseDto editarCategoria(UUID id, CategoriaRequestDto request) {

		var categoria = categoriaRepository.findById(id).get();
		categoria.setNome(request.getNome());

		categoriaRepository.save(categoria);

		return modelMapper.map(categoria, CategoriaResponseDto.class);
	}

	@Override
	public String excluirCategoria(UUID id) {

		categoriaRepository.deleteById(id);

		return "Categoria excluída com sucesso!";
	}

	@Override
	public CategoriaResponseDto consultarCategoriaPorId(UUID id) {

		return modelMapper.map(categoriaRepository.findById(id).get(), CategoriaResponseDto.class);
	}

	@Override
	public List<CategoriaResponseDto> consultarCategorias() {

		return categoriaRepository.findAll().stream()
				.map(categoria -> modelMapper.map(categoria, CategoriaResponseDto.class)).toList();
	}

}
