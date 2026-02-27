package com.java.springbootcamp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.springbootcamp.entities.Game;
import com.java.springbootcamp.projections.GameMinProjection;

public interface GameRepository extends JpaRepository<Game, Long> {
    
    @Query(nativeQuery = true, value = """
		SELECT 
            game.id,
            game.title,
            game.game_year AS gameYear,
            game.img_url AS imgUrl,
            game.short_description AS shortDescription,
            belonging.position
        FROM game
        JOIN belonging ON game.id = belonging.game_id
        WHERE belonging.list_id = :listId
        ORDER BY belonging.position
			""")
    List<GameMinProjection> searchByList(Long listId);
}