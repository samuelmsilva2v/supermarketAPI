package com.example.demo.domain.exceptions;

public class CategoriaComNomeDuplicadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CategoriaComNomeDuplicadoException(String nome) {
		super("Já existe uma categoria cadastrada com o nome: " + nome);
	}
}
