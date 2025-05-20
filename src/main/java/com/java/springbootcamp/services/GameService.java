package com.java.springbootcamp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.springbootcamp.dto.GameMinDTO;
import com.java.springbootcamp.entities.Game;
import com.java.springbootcamp.repositories.GameRepository;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRepository;

    public List<GameMinDTO> findAll() {
        List<Game> result = gameRepository.findAll();
        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }
}
