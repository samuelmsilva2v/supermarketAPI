package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.ProdutoRequestDto;
import com.example.demo.application.dtos.ProdutoResponseDto;
import com.example.demo.domain.exceptions.ProdutoComEstoqueException;
import com.example.demo.domain.exceptions.ProdutoComNomeDuplicadoException;
import com.example.demo.domain.models.entities.Produto;
import com.example.demo.domain.services.interfaces.ProdutoDomainService;
import com.example.demo.infrastructure.repositories.CategoriaRepository;
import com.example.demo.infrastructure.repositories.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoDomainServiceImpl implements ProdutoDomainService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProdutoResponseDto registrarProduto(ProdutoRequestDto request) {

		if (produtoRepository.existsByNome(request.getNome()))
			throw new ProdutoComNomeDuplicadoException(request.getNome());

		if (!categoriaRepository.existsById(request.getCategoriaId()))
			throw new EntityNotFoundException("Categoria com ID " + request.getCategoriaId() + " não encontrada.");

		var produto = modelMapper.map(request, Produto.class);
		produto.setId(UUID.randomUUID());
		produto.setCategoria(categoriaRepository.findById(request.getCategoriaId()).get());

		produtoRepository.save(produto);

		return modelMapper.map(produto, ProdutoResponseDto.class);
	}

	@Override
	public ProdutoResponseDto atualizarProduto(UUID id, ProdutoRequestDto request) {
		
		if (produtoRepository.existsByNome(request.getNome()))
			throw new ProdutoComNomeDuplicadoException(request.getNome());

		var produto = produtoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Produto com ID " + id + " não encontrada."));

		if (!categoriaRepository.existsById(request.getCategoriaId()))
			throw new EntityNotFoundException("Categoria com ID " + request.getCategoriaId() + " não encontrada.");

		produto.setNome(request.getNome());
		produto.setPreco(request.getPreco());
		produto.setQuantidade(request.getQuantidade());
		produto.setCategoria(categoriaRepository.findById(request.getCategoriaId()).get());

		produtoRepository.save(produto);

		return modelMapper.map(produto, ProdutoResponseDto.class);
	}

	@Override
	public String excluirProduto(UUID id) {

		if (!produtoRepository.existsById(id))
			throw new EntityNotFoundException("Produto com ID " + id + " não encontrada.");
		
		if (produtoRepository.findById(id).get().getQuantidade() > 0)
			throw new ProdutoComEstoqueException(produtoRepository.findById(id).get().getNome(), produtoRepository.findById(id).get().getQuantidade());

		produtoRepository.deleteById(id);

		return "Produto excluído com sucesso!";
	}

	@Override
	public ProdutoResponseDto consultarProdutoPorId(UUID id) {

		if (!produtoRepository.existsById(id))
			throw new EntityNotFoundException("Produto com ID " + id + " não encontrada.");

		return modelMapper.map(produtoRepository.findById(id), ProdutoResponseDto.class);
	}

	@Override
	public List<ProdutoResponseDto> consultarProdutos() {
		return produtoRepository.findAll().stream().map(produto -> modelMapper.map(produto, ProdutoResponseDto.class))
				.toList();
	}

}
