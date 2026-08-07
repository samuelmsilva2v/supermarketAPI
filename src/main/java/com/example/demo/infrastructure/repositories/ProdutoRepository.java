package com.example.demo.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.models.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

	boolean existsByCategoriaId(UUID categoriaId);

	boolean existsByNome(String nome);

	boolean existsByNomeAndIdNot(String nome, UUID id);

	@Query(value = "SELECT p FROM Produto p JOIN FETCH p.categoria c WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))",
			countQuery = "SELECT COUNT(p) FROM Produto p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Page<Produto> findByNomeContaining(@Param("nome") String nome, Pageable pageable);
}
