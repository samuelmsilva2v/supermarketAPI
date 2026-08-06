package com.example.demo.domain.exceptions;

public class CredenciaisInvalidasException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CredenciaisInvalidasException() {
		super("Acesso negado. Usuário não encontrado.");
	}
}
