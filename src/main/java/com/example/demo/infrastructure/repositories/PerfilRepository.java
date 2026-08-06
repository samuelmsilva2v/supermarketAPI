package com.example.demo.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.models.entities.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, UUID> {

	Perfil findByNome(String nome);

	boolean existsByNome(String nome);
}
