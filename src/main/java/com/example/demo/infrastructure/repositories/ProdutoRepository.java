package com.example.demo.infrastructure.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.models.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

	boolean existsByCategoriaId(UUID categoriaId);
	
	boolean existsByNome(String nome);
	
	@Query("SELECT p FROM Produto p JOIN FETCH p.categoria c WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%')) ORDER BY p.nome")
    List<Produto> findByName(@Param("nome") String nome);
}
