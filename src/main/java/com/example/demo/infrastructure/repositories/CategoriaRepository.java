package com.example.demo.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.models.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
	
	boolean existsByNome(String nome);
}
