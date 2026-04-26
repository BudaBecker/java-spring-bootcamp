package com.study.libraryapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "livro")
@Getter
@Setter
@ToString(exclude = "autor")
public class Livro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "ISBN não pode ser vazio")
    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    @NotBlank(message = "Título não pode ser vazio")
    @Column(name = "titulo", length = 50, nullable = false)
    private String titulo;

    @NotNull(message = "Data de publicação é obrigatória")
    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @NotNull(message = "Gênero é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 30, nullable = false)
    private GeneroLivro genero;

    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    @Column(name = "preco", precision = 18, scale = 2)
    private BigDecimal preco;
    // private Double preco;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Livro))
            return false;
        return id != null && id.equals(((Livro) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
