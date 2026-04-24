package com.study.libraryapi.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.libraryapi.model.Autor;
import com.study.libraryapi.model.GeneroLivro;
import com.study.libraryapi.model.Livro;

@SpringBootTest
public class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarLivroTeste() {
        Livro livro = new Livro();

        livro.setIsbn("4234-5379-0012");
        livro.setTitulo("Astro Fisica Para Malucos");
        livro.setDataPublicacao(LocalDate.of(1996, 04, 14));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setPreco(BigDecimal.valueOf(99.98));

        Autor autor = autorRepository.findById(UUID.fromString("ee3a4be5-0498-49f8-be9a-fd6f4f453e44")).orElse(null);

        livro.setAutor(autor);

        repository.save(livro);
    }
}
