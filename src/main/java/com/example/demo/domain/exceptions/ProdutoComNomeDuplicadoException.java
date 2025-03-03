package com.example.demo.domain.exceptions;

public class ProdutoComNomeDuplicadoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ProdutoComNomeDuplicadoException(String nome) {
        super("Já existe um produto cadastrado com o nome: " + nome);
    }
}
