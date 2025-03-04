package com.example.demo.application.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.dtos.CategoriaRequestDto;
import com.example.demo.application.dtos.CategoriaResponseDto;
import com.example.demo.domain.services.interfaces.CategoriaDomainService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaDomainService categoriaDomainService;
	
	@Operation(summary = "Serviço para registrar uma categoria.")
	@PostMapping
	public CategoriaResponseDto post(@RequestBody @Valid CategoriaRequestDto request) {
		return categoriaDomainService.registrarCategoria(request);
	}
	
	@Operation(summary = "Serviço para atualizar uma categoria.")
	@PutMapping("/{id}")
	public CategoriaResponseDto put(@PathVariable UUID id, @RequestBody @Valid CategoriaRequestDto request) {
		return categoriaDomainService.editarCategoria(id, request);
	}
	
	@Operation(summary = "Serviço para excluir uma categoria.")
	@DeleteMapping("/{id}")
	public String delete(@PathVariable UUID id) {
		return categoriaDomainService.excluirCategoria(id);
	}
	
	@Operation(summary = "Serviço para consultar uma categoria por ID.")
	@GetMapping("/{id}")
	public CategoriaResponseDto getById(@PathVariable UUID id) {
		return categoriaDomainService.consultarCategoriaPorId(id);
	}
	
	@Operation(summary = "Serviço para consultar todas as categorias.")
	@GetMapping
	public List<CategoriaResponseDto> getAll() {
		return categoriaDomainService.consultarCategorias();
	}
}
