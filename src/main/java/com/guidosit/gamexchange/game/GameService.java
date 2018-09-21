package com.guidosit.gamexchange.game;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game save(Game game){
        return gameRepository.save(game);
    }

    public Optional<Game> getGame(Integer id){
        return gameRepository.findById(id);
    }

    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGame(Game game) {
        return gameRepository.findOne(Example.of(game));
    }
}
