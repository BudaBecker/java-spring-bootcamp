package com.java.springbootcamp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.springbootcamp.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long> {
    
    
}
