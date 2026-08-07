package com.example.demo.application.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AutenticarUsuarioRequestDto {

	@NotEmpty(message = "Por favor, informe o username de acesso.")
	private String username;

	@Size(min = 8, message = "Por favor, informe a senha de acesso com pelo menos 8 caracteres.")
	@NotEmpty(message = "Por favor, informe a senha de acesso.")
	private String senha;
}
