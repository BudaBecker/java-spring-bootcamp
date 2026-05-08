package com.study.libraryapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.study.libraryapi.model.GeneroLivro;
import com.study.libraryapi.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    @Query("""
                select l
                from Livro l
                where l.id = :id
            """)
    Livro findByIdUsingJpql(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query("""
            delete from Livro
            where genero = :genero
            """)
    void deleteByGenero(@Param("genero") GeneroLivro genero);
}
