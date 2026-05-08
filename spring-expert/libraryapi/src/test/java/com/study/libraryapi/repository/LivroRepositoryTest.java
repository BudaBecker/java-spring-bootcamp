package com.study.libraryapi.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.study.libraryapi.model.Autor;
import com.study.libraryapi.model.GeneroLivro;
import com.study.libraryapi.model.Livro;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Testes do LivroRepository")
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    // CREATE
    @Test
    @DisplayName("Salvar livro novo de um Autor.")
    public void createLivro() {

        Autor autor = autorRepository.findById(UUID.fromString("4c66e7ed-52dc-44b2-9f6f-fcd94f14f7bd")).orElse(null);

        Livro livro = new Livro();
        livro.setIsbn("0310-6999");
        livro.setTitulo("O Guia das Cabras");
        livro.setDataPublicacao(LocalDate.of(1978, 06, 26));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setPreco(BigDecimal.valueOf(420.69));
        livro.setAutor(autor);

        System.out.println("Livro salvo: " + livroRepository.save(livro));

    }

    // READ
    @Test
    @DisplayName("Deve buscar o livro com autor.")
    @Transactional
    public void buscarLivroTest() {

        UUID id = UUID.fromString("8eeb1b28-0368-4d67-bdec-b4f1c7d9ad9e");
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutor().getNome());

        // Nota: com o @Transactional e FetchType.LAZY a consulta de autor
        // so sera executada caso for chamada (no caso foi chamada por livro.getAutor())
    }

    @Test
    @DisplayName("Busca de livro por JPQL (by ID).")
    public void buscarLivrosPorIdJpqlTest() {

        UUID id = UUID.fromString("8eeb1b28-0368-4d67-bdec-b4f1c7d9ad9e");
        Livro livro = livroRepository.findByIdUsingJpql(id);
        System.out.println("Livro: " + livro);
    }

    // UPDATE

    // DELETE
    @Test
    @DisplayName("Deletar todos os livros com o genero (rollback automatico apos).")
    void deleteTest(GeneroLivro genero) {
    }
}