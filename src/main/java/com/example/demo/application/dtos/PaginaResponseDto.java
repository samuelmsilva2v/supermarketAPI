package com.example.demo.application.dtos;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class PaginaResponseDto<T> {

	private List<T> conteudo;
	private int paginaAtual;
	private int tamanhoPagina;
	private long totalElementos;
	private int totalPaginas;

	public PaginaResponseDto(Page<T> pagina) {
		this.conteudo = pagina.getContent();
		this.paginaAtual = pagina.getNumber();
		this.tamanhoPagina = pagina.getSize();
		this.totalElementos = pagina.getTotalElements();
		this.totalPaginas = pagina.getTotalPages();
	}
}
