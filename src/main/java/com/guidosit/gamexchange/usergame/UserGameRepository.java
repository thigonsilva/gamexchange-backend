package com.guidosit.gamexchange.usergame;

import com.guidosit.gamexchange.game.Game;
import com.guidosit.gamexchange.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserGameRepository extends JpaRepository<UserGame, UserGameId> {
    Optional<List<UserGame>> getUserGamesByGameAndIsAvailableTrue(Game game);

    Optional<List<UserGame>> findAllByUserAndIsAvailableIsTrue(User user);

    @Query(value = " select * from user_game ug " +
            " where ug.game_id = :id " +
            " order by insert_date " +
            " limit 1 ", nativeQuery = true)
    Optional<UserGame> getUserGameByGameId(@Param("id") Integer id);
}
