package com.example.demo.domain.exceptions;

public class ProdutoComEstoqueException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ProdutoComEstoqueException(String nome, Integer quantidade) {
        super("Não é possível excluir o produto '" + nome + "' porque ainda possui " + quantidade + " unidades em estoque.");
    }
}
