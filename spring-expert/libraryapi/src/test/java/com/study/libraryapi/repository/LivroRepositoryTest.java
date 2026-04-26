package com.study.libraryapi.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.libraryapi.model.Autor;
import com.study.libraryapi.model.GeneroLivro;
import com.study.libraryapi.model.Livro;

import jakarta.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Testes do Repositório de Livro")
public class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    private Autor criarAutorPadrao() {
        Autor autor = new Autor();
        autor.setNome("Machado de Assis");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1839, 6, 21));
        return autorRepository.save(autor);
    }

    // CREATE TESTS
    @Test
    @DisplayName("Deve salvar um livro com autor existente")
    @Transactional
    public void testSalvarLivroComAutorExistente() {
        // Arrange
        repository.deleteAll();
        autorRepository.deleteAll();
        Autor autor = criarAutorPadrao();

        Livro livro = new Livro();
        livro.setIsbn("1234-5679-0012");
        livro.setTitulo("Dom Casmurro");
        livro.setDataPublicacao(LocalDate.of(1899, 1, 1));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setPreco(BigDecimal.valueOf(45.99));
        livro.setAutor(autor);

        // Act
        Livro livroSalvo = repository.save(livro);

        // Assert
        assertNotNull(livroSalvo.getId());
        assertEquals("Dom Casmurro", livroSalvo.getTitulo());
        assertEquals(autor.getId(), livroSalvo.getAutor().getId());
    }

    @Test
    @DisplayName("Deve salvar um livro com novo autor em cascata")
    @Transactional
    public void testSalvarLivroComNovoAutor() {
        // Arrange
        repository.deleteAll();
        autorRepository.deleteAll();

        Livro livro = new Livro();
        livro.setIsbn("4234-5349-0012");
        livro.setTitulo("Amor");
        livro.setDataPublicacao(LocalDate.of(2025, 12, 20));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setPreco(BigDecimal.valueOf(49.99));

        Autor novoAutor = new Autor();
        novoAutor.setNome("Luisa");
        novoAutor.setNacionalidade("Canada");
        novoAutor.setDataNascimento(LocalDate.of(2006, 4, 14));
        livro.setAutor(novoAutor);

        // Act
        Livro livroSalvo = repository.save(livro);

        // Assert
        assertNotNull(livroSalvo.getId());
        assertNotNull(livroSalvo.getAutor().getId());
        assertEquals("Luisa", livroSalvo.getAutor().getNome());
    }

    @Test
    @DisplayName("Deve salvar múltiplos livros")
    @Transactional
    public void testSalvarMultiplosLivros() {
        // Arrange
        repository.deleteAll();
        autorRepository.deleteAll();
        Autor autor = criarAutorPadrao();

        Livro livro1 = new Livro();
        livro1.setIsbn("1111-1111-1111");
        livro1.setTitulo("Quincas Borba");
        livro1.setDataPublicacao(LocalDate.of(1891, 1, 1));
        livro1.setGenero(GeneroLivro.ROMANCE);
        livro1.setPreco(BigDecimal.valueOf(45.00));
        livro1.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("2222-2222-2222");
        livro2.setTitulo("Memórias Póstumas de Brás Cubas");
        livro2.setDataPublicacao(LocalDate.of(1881, 6, 15));
        livro2.setGenero(GeneroLivro.ROMANCE);
        livro2.setPreco(BigDecimal.valueOf(55.00));
        livro2.setAutor(autor);

        // Act
        repository.save(livro1);
        repository.save(livro2);

        // Assert
        assertTrue(repository.count() >= 2);
    }

    // READ TESTS
    @Test
    @DisplayName("Deve encontrar um livro por ID")
    @Transactional
    public void testBuscarLivroPorId() {
        // Arrange
        repository.deleteAll();
        autorRepository.deleteAll();
        Autor autor = criarAutorPadrao();

        Livro livro = new Livro();
        livro.setIsbn("3333-3333-3333");
        livro.setTitulo("Esaú e Jacó");
        livro.setDataPublicacao(LocalDate.of(1904, 1, 1));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setPreco(BigDecimal.valueOf(60.00));
        livro.setAutor(autor);
        Livro livroSalvo = repository.save(livro);

        // Act
        Optional<Livro> livroEncontrado = repository.findById(livroSalvo.getId());

        // Assert
        assertTrue(livroEncontrado.isPresent());
        assertEquals("Esaú e Jacó", livroEncontrado.get().getTitulo());
    }

    @Test
    @DisplayName("Deve listar todos os livros")
    @Transactional
    public void testListarTodosLivros() {
        // Arrange
        repository.deleteAll();
        autorRepository.deleteAll();
        Autor autor = criarAutorPadrao();

        Livro livro1 = new Livro();
        livro1.setIsbn("4444-4444-4444");
        livro1.setTitulo("Ressurreição");
        livro1.setDataPublicacao(LocalDate.of(1872, 1, 1));
        livro1.setGenero(GeneroLivro.ROMANCE);
        livro1.setPreco(BigDecimal.valueOf(40.00));
        livro1.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("5555-5555-5555");
        livro2.setTitulo("Claro Enigma");
        livro2.setDataPublicacao(LocalDate.of(1951, 1, 1));
        livro2.setGenero(GeneroLivro.ROMANCE);
        livro2.setPreco(BigDecimal.valueOf(50.00));
        livro2.setAutor(autor);

        repository.save(livro1);
        repository.save(livro2);

        // Act
        var livros = repository.findAll();

        // Assert
        assertTrue(livros.size() >= 2);
    }

    @Test
    @DisplayName("Deve retornar vazio ao buscar ID inexistente")
    @Transactional
    public void testBuscarLivroInexistente() {
        // Act
        Optional<Livro> livro = repository.findById(UUID.randomUUID());

        // Assert
        assertFalse(livro.isPresent());
    }

    @Test
    @DisplayName("Deve contar livros no banco")
    @Transactional
    public void testContarLivros() {
        // Arrange
        repository.deleteAll();
        autorRepository.deleteAll();
        Autor autor = criarAutorPadrao();

        Livro livro = new Livro();
        livro.setIsbn("6666-6666-6666");
        livro.setTitulo("Hábitos");
        livro.setDataPublicacao(LocalDate.of(2020, 1, 1));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setPreco(BigDecimal.valueOf(35.00));
        livro.setAutor(autor);
        repository.save(livro);

        // Act
        long contagem = repository.count();

        // Assert
        assertTrue(contagem > 0);
    }

    // UPDATE TESTS
    @Test
    @DisplayName("Deve atualizar dados de um livro")
    @Transactional
    public void testAtualizarLivro() {
        // Arrange
        repository.deleteAll();
        autorRepository.deleteAll();
        Autor autor = criarAutorPadrao();

        Livro livro = new Livro();
        livro.setIsbn("7777-7777-7777");
        livro.setTitulo("Livro Original");
        livro.setDataPublicacao(LocalDate.of(2000, 1, 1));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setPreco(BigDecimal.valueOf(30.00));
        livro.setAutor(autor);
        Livro livroSalvo = repository.save(livro);

        // Act
        livroSalvo.setPreco(BigDecimal.valueOf(45.00));
        livroSalvo.setGenero(GeneroLivro.FICCAO);
        Livro livroAtualizado = repository.save(livroSalvo);

        // Assert
        assertEquals(BigDecimal.valueOf(45.00), livroAtualizado.getPreco());
        assertEquals(GeneroLivro.FICCAO, livroAtualizado.getGenero());
    }

    @Test
    @DisplayName("Deve trocar o autor de um livro")
    @Transactional
    public void testTrocarAutorDoLivro() {
        // Arrange
        repository.deleteAll();
        autorRepository.deleteAll();

        Autor autor1 = new Autor();
        autor1.setNome("Autor Um");
        autor1.setNacionalidade("Brasileira");
        autor1.setDataNascimento(LocalDate.of(1900, 1, 1));
        autor1 = autorRepository.save(autor1);

        Livro livro = new Livro();
        livro.setIsbn("8888-8888-8888");
        livro.setTitulo("Livro");
        livro.setDataPublicacao(LocalDate.of(2010, 1, 1));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setPreco(BigDecimal.valueOf(50.00));
        livro.setAutor(autor1);
        Livro livroSalvo = repository.save(livro);

        Autor autor2 = new Autor();
        autor2.setNome("Autor Dois");
        autor2.setNacionalidade("Brasileira");
        autor2.setDataNascimento(LocalDate.of(1950, 1, 1));
        autor2 = autorRepository.save(autor2);

        // Act
        livroSalvo.setAutor(autor2);
        Livro livroAtualizado = repository.save(livroSalvo);

        // Assert
        assertEquals(autor2.getId(), livroAtualizado.getAutor().getId());
    }

    // DELETE TESTS
    @Test
    @DisplayName("Deve deletar um livro com sucesso")
    @Transactional
    public void testDeletarLivro() {
        // Arrange
        repository.deleteAll();
        autorRepository.deleteAll();
        Autor autor = criarAutorPadrao();

        Livro livro = new Livro();
        livro.setIsbn("9999-9999-9999");
        livro.setTitulo("Grande Sertão");
        livro.setDataPublicacao(LocalDate.of(1956, 1, 1));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setPreco(BigDecimal.valueOf(65.00));
        livro.setAutor(autor);
        Livro livroSalvo = repository.save(livro);
        UUID livroId = livroSalvo.getId();

        // Act
        repository.delete(livroSalvo);

        // Assert
        assertFalse(repository.findById(livroId).isPresent());
    }

    @Test
    @DisplayName("Deve verificar que deletar livro não deleta o autor")
    @Transactional
    public void testDeletarLivroNaoDeletaAutor() {
        // Arrange
        repository.deleteAll();
        autorRepository.deleteAll();
        Autor autor = criarAutorPadrao();
        UUID autorId = autor.getId();

        Livro livro = new Livro();
        livro.setIsbn("0000-0000-0001");
        livro.setTitulo("Capitães da Areia");
        livro.setDataPublicacao(LocalDate.of(1937, 1, 1));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setPreco(BigDecimal.valueOf(70.00));
        livro.setAutor(autor);
        Livro livroSalvo = repository.save(livro);

        // Act
        repository.delete(livroSalvo);

        // Assert
        assertFalse(repository.findById(livroSalvo.getId()).isPresent());
        assertTrue(autorRepository.findById(autorId).isPresent());
    }

    @Test
    @DisplayName("Deve lidar com deleção de livro que não existe")
    @Transactional
    public void testDeletarLivroInexistente() {
        // Arrange
        Livro livroInexistente = new Livro();
        livroInexistente.setId(UUID.randomUUID());

        // Act & Assert
        assertDoesNotThrow(() -> repository.delete(livroInexistente));
    }
}
