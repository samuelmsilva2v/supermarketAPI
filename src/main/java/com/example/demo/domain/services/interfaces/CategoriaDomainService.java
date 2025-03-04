package com.example.demo.domain.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.example.demo.application.dtos.CategoriaRequestDto;
import com.example.demo.application.dtos.CategoriaResponseDto;

public interface CategoriaDomainService {

	public CategoriaResponseDto registrarCategoria(CategoriaRequestDto request);
	
	public CategoriaResponseDto editarCategoria(UUID id, CategoriaRequestDto request);
	
	public String excluirCategoria(UUID id);
	
	public CategoriaResponseDto consultarCategoriaPorId(UUID id);
	
	public List<CategoriaResponseDto> consultarCategorias();
	
}
