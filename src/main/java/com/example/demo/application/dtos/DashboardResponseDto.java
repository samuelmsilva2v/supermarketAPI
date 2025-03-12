package com.example.demo.application.dtos;

import lombok.Data;

@Data
public class DashboardResponseDto {

	private String nomeCategoria;
	private Long qtdProdutos;
	
	public DashboardResponseDto(String nomeCategoria, Long qtdProdutos) {
        this.nomeCategoria = nomeCategoria;
        this.qtdProdutos = qtdProdutos;
    }
}
