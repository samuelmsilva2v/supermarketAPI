package com.example.demo.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaRequestDto {

	@NotBlank(message = "O nome da categoria é obrigatório.")
	@Size(max = 100, message = "O nome do produto deve ter no máximo 100 caracteres.")
	private String nome;
}
