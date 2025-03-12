package com.example.demo.domain.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.example.demo.application.dtos.ProdutoRequestDto;
import com.example.demo.application.dtos.ProdutoResponseDto;

public interface ProdutoDomainService {

	public ProdutoResponseDto registrarProduto(ProdutoRequestDto request);

	public ProdutoResponseDto atualizarProduto(UUID id, ProdutoRequestDto request);

	public String excluirProduto(UUID id);

	public ProdutoResponseDto consultarProdutoPorId(UUID id);
	
	public List<ProdutoResponseDto> consultarProdutoPorNome(String nome);

	public List<ProdutoResponseDto> consultarProdutos();
}
