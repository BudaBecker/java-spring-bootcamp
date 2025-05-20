package com.java.springbootcamp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.springbootcamp.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
    
  
}
