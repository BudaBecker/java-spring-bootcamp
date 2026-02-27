package com.example.springarchitecture.todos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_todo")
public class TodoEntity {

    /*
     * Entity (Domain Model)
     * Represents a real-world concept (ex: odo) with identity + state.
     * Mapped to DB tables via JPA annotations (@Entity, @Id, etc.).
     * Encapsulates basic invariants/validation, but no persistence logic.
     * Used throughout services, repositories, and DTO mappings.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indicates that the persistence provider must assign primary
                                                        // keys for the entity using a database identity column.
                                                        // (Auto_Increment)
    private Integer id;

    @Column(name = "desc")
    private String description;

    @Column(name = "fl_concluded")
    private Boolean concluded;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getConcluded() {
        return concluded;
    }

    public void setConcluded(Boolean concluded) {
        this.concluded = concluded;
    }

}
