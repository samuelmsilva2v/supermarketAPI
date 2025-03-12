package com.example.demo.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.dtos.DashboardResponseDto;
import com.example.demo.domain.services.interfaces.CategoriaDomainService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
	
	@Autowired
	private CategoriaDomainService categoriaDomainService;
	
	@Operation(summary = "Serviço para consultar o total de produtos por categoria.")
	@GetMapping("produtos-categoria")
	public List<DashboardResponseDto> produtosCategoria() {
		return categoriaDomainService.buscarQuantidadePorCategoria();
	}
}
