package com.java.springbootcamp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.springbootcamp.dto.GameListDTO;
import com.java.springbootcamp.entities.GameList;
import com.java.springbootcamp.repositories.GameListRepository;

@Service
public class GameListService {
    
    @Autowired
    private GameListRepository gameListRepository;

    public List<GameListDTO> findAll() {
        List<GameList> result = gameListRepository.findAll();
        return result.stream().map(x -> new GameListDTO(x)).toList();
    }
}