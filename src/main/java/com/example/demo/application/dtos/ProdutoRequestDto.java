package com.example.demo.application.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class ProdutoRequestDto {

	private String nome;
	private BigDecimal preco;
	private Integer quantidade;
	private UUID categoriaId;
}
