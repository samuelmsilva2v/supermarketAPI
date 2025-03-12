package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.dtos.CategoriaRequestDto;
import com.example.demo.application.dtos.CategoriaResponseDto;
import com.example.demo.application.dtos.DashboardResponseDto;
import com.example.demo.domain.exceptions.CategoriaComNomeDuplicadoException;
import com.example.demo.domain.models.entities.Categoria;
import com.example.demo.domain.services.interfaces.CategoriaDomainService;
import com.example.demo.infrastructure.repositories.CategoriaRepository;
import com.example.demo.infrastructure.repositories.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaDomainServiceImpl implements CategoriaDomainService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoriaResponseDto registrarCategoria(CategoriaRequestDto request) {

		if (categoriaRepository.existsByNome(request.getNome()))
			throw new CategoriaComNomeDuplicadoException(request.getNome());

		var categoria = new Categoria();
		categoria.setId(UUID.randomUUID());
		categoria.setNome(request.getNome());

		categoriaRepository.save(categoria);

		return modelMapper.map(categoria, CategoriaResponseDto.class);
	}

	@Override
	public CategoriaResponseDto editarCategoria(UUID id, CategoriaRequestDto request) {

		if (!categoriaRepository.existsById(id))
			throw new EntityNotFoundException("Categoria com ID " + id + " não encontrada.");

		if (categoriaRepository.existsByNome(request.getNome()))
			throw new CategoriaComNomeDuplicadoException(request.getNome());

		var categoria = categoriaRepository.findById(id).get();
		categoria.setNome(request.getNome());

		categoriaRepository.save(categoria);

		return modelMapper.map(categoria, CategoriaResponseDto.class);
	}

	@Override
	public String excluirCategoria(UUID id) {
		
		if (!categoriaRepository.existsById(id))
			throw new EntityNotFoundException("Categoria com ID " + id + " não encontrada.");
		
		if (produtoRepository.existsByCategoriaId(id))
	        throw new RuntimeException("Não é possível excluir a categoria ID " + id + " pois existem produtos associados a ela.");

		categoriaRepository.deleteById(id);

		return "Categoria excluída com sucesso!";
	}

	@Override
	public CategoriaResponseDto consultarCategoriaPorId(UUID id) {
		
		if (!categoriaRepository.existsById(id))
			throw new EntityNotFoundException("Categoria com ID " + id + " não encontrada.");
	    
		return modelMapper.map(categoriaRepository.findById(id).get(), CategoriaResponseDto.class);
	}

	@Override
	public List<CategoriaResponseDto> consultarCategorias() {

		return categoriaRepository.findAll().stream()
				.map(categoria -> modelMapper.map(categoria, CategoriaResponseDto.class)).toList();
	}

	@Override
	public List<DashboardResponseDto> buscarQuantidadePorCategoria() {
		return categoriaRepository.searchQuantityByCategory()
                .stream()
                .map(dto -> new DashboardResponseDto(dto.getNomeCategoria(), dto.getQtdProdutos()))
                .collect(Collectors.toList());
	}

}
