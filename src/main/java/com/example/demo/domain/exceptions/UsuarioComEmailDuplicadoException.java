package com.example.demo.domain.exceptions;

public class UsuarioComEmailDuplicadoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UsuarioComEmailDuplicadoException() {
		super("O e-mail informado já está cadastrado, tente outro.");
	}
}
