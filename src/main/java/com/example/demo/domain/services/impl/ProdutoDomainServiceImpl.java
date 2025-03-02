package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.ProdutoRequestDto;
import com.example.demo.application.dtos.ProdutoResponseDto;
import com.example.demo.domain.services.interfaces.ProdutoDomainService;

@Service
public class ProdutoDomainServiceImpl implements ProdutoDomainService {

	@Override
	public String registrarProduto(ProdutoRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProdutoResponseDto editarProduto(UUID id, ProdutoRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excluirProduto(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProdutoResponseDto consultarProdutoPorId(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdutoResponseDto> consultarProdutos() {
		// TODO Auto-generated method stub
		return null;
	}

}
