package com.guidosit.gamexchange.usergame;

import com.guidosit.gamexchange.game.Game;
import com.guidosit.gamexchange.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserGameService {

    @Autowired
    private UserGameRepository repository;

    public Optional<List<UserGame>> getUsersForGame(Game game) {
        return repository.getUserGamesByGameAndIsAvailableTrue(game);
    }

    public Optional<UserGame> getUserGame(Integer gameId) {
        return repository.getUserGameByGameId(gameId);
    }

    public Optional<List<UserGame>> getUserGamesForUser(User user) {
        return repository.findAllByUserAndIsAvailableIsTrue(user);
    }
}
