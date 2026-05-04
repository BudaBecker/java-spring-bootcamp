package com.study.libraryapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.libraryapi.model.Autor;
import com.study.libraryapi.model.GeneroLivro;
import com.study.libraryapi.model.Livro;

import jakarta.transaction.Transactional;

@SpringBootTest
@DisplayName("Testes do AutorRepository")
class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    // CREATE
    @Test
    @DisplayName("Deve salvar um novo autor com sucesso")
    public void salvarAutorTest() {

        Autor autor = new Autor();
        autor.setNome("Gabriel Becker");
        autor.setDataNascimento(LocalDate.of(2002, 10, 03));
        autor.setNacionalidade("Brasileiro");

        Autor autorSalvo = autorRepository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);

        assertNotNull(autorSalvo.getId());
    }

    @Test
    @DisplayName("Deve salvar um novo Autor com os Livros Novos.")
    public void salvarAutorComLivroTest() {

        Autor autor = new Autor();
        autor.setNome("Luisa Gois");
        autor.setDataNascimento(LocalDate.of(2006, 04, 14));
        autor.setNacionalidade("Canadense");

        Livro livro1 = new Livro();
        livro1.setIsbn("1234-4321");
        livro1.setTitulo("A princesa");
        livro1.setDataPublicacao(LocalDate.of(2025, 12, 20));
        livro1.setGenero(GeneroLivro.ROMANCE);
        livro1.setPreco(BigDecimal.valueOf(204.50));
        livro1.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("5678-8765");
        livro2.setTitulo("Uma historia legal");
        livro2.setDataPublicacao(LocalDate.of(2026, 02, 13));
        livro2.setGenero(GeneroLivro.FICCAO);
        livro2.setPreco(BigDecimal.valueOf(99.99));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro1);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);
        livroRepository.saveAll(autor.getLivros());
    }

    // READ
    @Test
    @DisplayName("Deve ler um autor por Id.")
    public void buscarAutorPorIdTest() {
        UUID autorId = UUID.fromString("5ce35d20-03b3-4f19-b56b-31f73d50275c");
        Autor autor = autorRepository.findById(autorId).orElse(null);

        assertNotNull(autor);

        System.out.println("Autor encontrado: " + autor);
    }

    @Test
    @DisplayName("Deve ler um autor por Nome.")
    public void busacarAutorPorNomeTest() {
        Autor autor = autorRepository.findByNome("Gabriel Becker")
                .stream()
                .findFirst()
                .orElse(null);

        assertNotNull(autor);

        System.out.println("Autor encontrado: " + autor);
    }

    @Test
    @Transactional
    @DisplayName("Deve ler um autor e seus Livros.")
    public void buscarAutorELivrosTest() {
        Autor autor = autorRepository.findByNome("Luisa Gois")
                .stream()
                .findFirst()
                .orElse(null);

        assertNotNull(autor);

        System.out.println("Autor encontrado: " + autor);
        System.out.println("Livros do Autor: " + autor.getLivros());
    }

    // UPDATE
    @Test
    @DisplayName("Deve mudar a nacionalidade do Autor.")
    public void mudarGeneroAutorTest() {

        UUID idAutor = UUID.fromString("5ce35d20-03b3-4f19-b56b-31f73d50275c");
        Autor autor = autorRepository.findById(idAutor).orElse(null);

        assertNotNull(autor);

        autor.setNacionalidade("Italiano");
        autorRepository.save(autor);

        assertEquals("Italiano", autor.getNacionalidade());
    }

    // DELETE
    @Test
    @DisplayName("Deletar autor pelo ID.")
    public void deletarAutorPorIdTest() {

        UUID id = UUID.fromString("1f4a1ef0-2e67-415a-9a63-be01a42aeea1");
        autorRepository.deleteById(id);

    }
}
