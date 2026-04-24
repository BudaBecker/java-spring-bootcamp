package com.study.libraryapi.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.libraryapi.model.Autor;

import jakarta.transaction.Transactional;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTeste() {
        Autor autor = new Autor();
        autor.setNome("Mario");
        autor.setNacionalidade("Nordestino");
        autor.setDataNascimento(LocalDate.of(1967, 1, 1));

        Autor autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    @Transactional
    public void listarTodosTeste() {
        List<Autor> list = repository.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void countTeste() {
        System.out.println("Contagem: " + repository.count());
    }
}
