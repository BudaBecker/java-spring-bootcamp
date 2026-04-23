package com.study.libraryapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.libraryapi.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

}
