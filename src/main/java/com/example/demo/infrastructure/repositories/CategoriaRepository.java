package com.example.demo.infrastructure.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.application.dtos.DashboardResponseDto;
import com.example.demo.domain.models.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {

	boolean existsByNome(String nome);

	@Query(value = """
	        SELECT 
	            c.nome as nomeCategoria, 
	            SUM(p.quantidade) as qtdProdutos
	        FROM produto p
	        INNER JOIN categoria c ON c.id = p.categoria_id
	        GROUP BY c.nome
	    """, nativeQuery = true)
	List<DashboardResponseDto> searchQuantityByCategory();
}
