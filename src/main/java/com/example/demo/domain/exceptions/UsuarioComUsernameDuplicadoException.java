package com.example.demo.domain.exceptions;

public class UsuarioComUsernameDuplicadoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UsuarioComUsernameDuplicadoException() {
		super("O username informado já está cadastrado, tente outro.");
	}
}
