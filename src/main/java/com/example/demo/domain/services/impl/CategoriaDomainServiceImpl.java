package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.CategoriaRequestDto;
import com.example.demo.application.dtos.CategoriaResponseDto;
import com.example.demo.domain.services.interfaces.CategoriaDomainService;

@Service
public class CategoriaDomainServiceImpl implements CategoriaDomainService {

	@Override
	public String registrarCategoria(CategoriaRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoriaResponseDto editarCategoria(UUID id, CategoriaRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excluirCategoria(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoriaResponseDto consultarCategoriaPorId(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoriaResponseDto> consultarCategorias() {
		// TODO Auto-generated method stub
		return null;
	}

}
