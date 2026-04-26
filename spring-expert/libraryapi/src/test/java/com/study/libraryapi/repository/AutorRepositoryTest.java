package com.study.libraryapi.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.libraryapi.model.Autor;

import jakarta.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Testes do Repositório de Autor")
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    // CREATE TESTS
    @Test
    @DisplayName("Deve salvar um novo autor com sucesso")
    @Transactional
    public void testSalvarAutorComSucesso() {
        // Arrange
        Autor autor = new Autor();
        autor.setNome("Mario");
        autor.setNacionalidade("Nordestino");
        autor.setDataNascimento(LocalDate.of(1967, 1, 1));

        // Act
        Autor autorSalvo = repository.save(autor);

        // Assert
        assertNotNull(autorSalvo.getId(), "ID do autor não deve ser nulo");
        assertEquals("Mario", autorSalvo.getNome());
        assertEquals("Nordestino", autorSalvo.getNacionalidade());
    }

    @Test
    @DisplayName("Deve salvar múltiplos autores")
    @Transactional
    public void testSalvarMultiplosAutores() {
        // Arrange
        repository.deleteAll();

        Autor autor1 = new Autor();
        autor1.setNome("Clarice");
        autor1.setNacionalidade("Brasileira");
        autor1.setDataNascimento(LocalDate.of(1925, 12, 10));

        Autor autor2 = new Autor();
        autor2.setNome("Paulo");
        autor2.setNacionalidade("Brasileira");
        autor2.setDataNascimento(LocalDate.of(1947, 8, 24));

        // Act
        repository.saveAndFlush(autor1);
        repository.saveAndFlush(autor2);

        // Assert
        assertTrue(repository.count() >= 2, "Deve haver pelo menos 2 autores");
    }

    // READ TESTS
    @Test
    @DisplayName("Deve encontrar um autor por ID")
    @Transactional
    public void testBuscarAutorPorId() {
        // Arrange
        repository.deleteAll();
        Autor autor = new Autor();
        autor.setNome("Aluísio");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1857, 3, 19));
        Autor autorSalvo = repository.save(autor);

        // Act
        Optional<Autor> autorEncontrado = repository.findById(autorSalvo.getId());

        // Assert
        assertTrue(autorEncontrado.isPresent(), "Autor deve existir");
        assertEquals("Aluísio", autorEncontrado.get().getNome());
    }

    @Test
    @DisplayName("Deve listar todos os autores")
    @Transactional
    public void testListarTodosAutores() {
        // Arrange
        repository.deleteAll();
        Autor autor = new Autor();
        autor.setNome("Machado");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1839, 6, 21));
        repository.save(autor);

        // Act
        List<Autor> autores = repository.findAll();

        // Assert
        assertFalse(autores.isEmpty(), "Deve haver autores");
        assertTrue(autores.size() > 0);
    }

    @Test
    @DisplayName("Deve buscar autores por nome")
    @Transactional
    public void testBuscarAutorPorNome() {
        // Arrange
        repository.deleteAll();
        Autor autor = new Autor();
        autor.setNome("Gonçalves");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1812, 3, 27));
        repository.save(autor);

        // Act
        List<Autor> autores = repository.findByNome("Gonçalves");

        // Assert
        assertFalse(autores.isEmpty(), "Deve encontrar autores");
        assertTrue(autores.stream().allMatch(a -> a.getNome().equals("Gonçalves")));
    }

    @Test
    @DisplayName("Deve retornar vazio ao buscar nome inexistente")
    @Transactional
    public void testBuscarPorNomeInexistente() {
        // Arrange
        repository.deleteAll();

        // Act
        List<Autor> autores = repository.findByNome("NomeQueNuncaExistiu");

        // Assert
        assertTrue(autores.isEmpty(), "Não deve encontrar nenhum autor");
    }

    @Test
    @DisplayName("Deve contar autores no banco")
    @Transactional
    public void testContarAutores() {
        // Arrange
        repository.deleteAll();
        Autor autor = new Autor();
        autor.setNome("José");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1880, 5, 5));
        repository.save(autor);

        // Act
        long contagem = repository.count();

        // Assert
        assertTrue(contagem > 0, "Deve haver pelo menos 1 autor");
    }

    // UPDATE TESTS
    @Test
    @DisplayName("Deve atualizar dados de um autor")
    @Transactional
    public void testAtualizarAutor() {
        // Arrange
        repository.deleteAll();
        Autor autor = new Autor();
        autor.setNome("José");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1880, 5, 5));
        Autor autorSalvo = repository.save(autor);

        // Act
        autorSalvo.setNome("José de Alencar");
        Autor autorAtualizado = repository.save(autorSalvo);

        // Assert
        assertEquals("José de Alencar", autorAtualizado.getNome());
        assertEquals(autorSalvo.getId(), autorAtualizado.getId());
    }

    @Test
    @DisplayName("Deve manter o ID ao atualizar")
    @Transactional
    public void testManterIdAoAtualizar() {
        // Arrange
        repository.deleteAll();
        Autor autor = new Autor();
        autor.setNome("Rita");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1844, 3, 12));
        Autor autorSalvo = repository.save(autor);
        UUID idOriginal = autorSalvo.getId();

        // Act
        autorSalvo.setNacionalidade("Portuguesa");
        Autor autorAtualizado = repository.save(autorSalvo);

        // Assert
        assertEquals(idOriginal, autorAtualizado.getId());
    }

    // DELETE TESTS
    @Test
    @DisplayName("Deve deletar um autor com sucesso")
    @Transactional
    public void testDeletarAutor() {
        // Arrange
        repository.deleteAll();
        Autor autor = new Autor();
        autor.setNome("Raquel");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1960, 7, 15));
        Autor autorSalvo = repository.save(autor);
        UUID autorId = autorSalvo.getId();

        // Act
        repository.delete(autorSalvo);

        // Assert
        assertFalse(repository.findById(autorId).isPresent(), "Autor deve ser deletado");
    }

    @Test
    @DisplayName("Deve lidar com deleção de autor que não existe")
    @Transactional
    public void testDeletarAutorInexistente() {
        // Arrange
        Autor autorInexistente = new Autor();
        autorInexistente.setId(UUID.randomUUID());

        // Act & Assert
        assertDoesNotThrow(() -> repository.delete(autorInexistente),
                "Não deve lançar exceção");
    }
}
