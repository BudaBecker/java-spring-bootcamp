package com.example.springarchitecture.todos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {

    /*
     * Repository Layer
     * Abstracts data persistence (CRUD + queries) behind a clean interface.
     * Bridges domain objects to the underlying DB/ORM (JPA, JDBC...)
     * Hides SQL/dialect details so services stay storage-agnostic.
     * Eases testing: can be mocked or run with in-memory DB (h2).
     */

    // Do not need to create a query for this,
    // spring already does ir for us, the
    // existsBy pre-fix tells the spring to
    // make a query like:
    // SELECT *
    // FROM tb_todo
    // WHERE ...
    // limit 1
    // Then the suffix Description, goes into
    // the where function
    boolean existsByDescription(String description);

    // Or could just code all from scratch like:

    // @Query(nativeQuery = true, value = """
    // SELECT *
    // FROM tb_todo
    // WHERE tb_todo.desc = :description
    // LIMIT 1
    // """;)
    // boolean existsByDescription(String description);
}
