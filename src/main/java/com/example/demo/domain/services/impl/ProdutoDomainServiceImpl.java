package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.ProdutoRequestDto;
import com.example.demo.application.dtos.ProdutoResponseDto;
import com.example.demo.domain.models.entities.Produto;
import com.example.demo.domain.services.interfaces.ProdutoDomainService;
import com.example.demo.infrastructure.repositories.CategoriaRepository;
import com.example.demo.infrastructure.repositories.ProdutoRepository;

@Service
public class ProdutoDomainServiceImpl implements ProdutoDomainService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String registrarProduto(ProdutoRequestDto request) {

		var produto = modelMapper.map(request, Produto.class);
		produto.setId(UUID.randomUUID());
		produto.setCategoria(categoriaRepository.findById(request.getCategoriaId()).get());

		produtoRepository.save(produto);

		return "Produto cadastrado com sucesso!";
	}

	@Override
	public ProdutoResponseDto atualizarProduto(UUID id, ProdutoRequestDto request) {

		var produto = produtoRepository.findById(id).get();

		produto.setNome(request.getNome());
		produto.setPreco(request.getPreco());
		produto.setQuantidade(request.getQuantidade());
		produto.setCategoria(categoriaRepository.findById(request.getCategoriaId()).get());

		produtoRepository.save(produto);

		return modelMapper.map(produto, ProdutoResponseDto.class);
	}

	@Override
	public String excluirProduto(UUID id) {

		produtoRepository.deleteById(id);

		return "Produto excluído com sucesso!";
	}

	@Override
	public ProdutoResponseDto consultarProdutoPorId(UUID id) {
		return modelMapper.map(produtoRepository.findById(id), ProdutoResponseDto.class);
	}

	@Override
	public List<ProdutoResponseDto> consultarProdutos() {
		return produtoRepository.findAll().stream().map(produto -> modelMapper.map(produto, ProdutoResponseDto.class))
				.toList();
	}

}
