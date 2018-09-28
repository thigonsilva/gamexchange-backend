package com.guidosit.gamexchange.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game ,Integer> {

    @Query(value = " select g.* from game g " +
            " inner join user_game ug on ug.game_id = g.id " +
            " where ug.is_available = TRUE; ", nativeQuery = true)
    Optional<List<Game>> getAvailableGames();

}
