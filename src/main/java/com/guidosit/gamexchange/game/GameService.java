package com.guidosit.gamexchange.game;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game save(Game game){

        return gameRepository.save(game);

    }

    public Game getGame(Integer id){

        return gameRepository.findById(id).get();
    }

    public List<Game> getGames() {
        return gameRepository.findAll();
    }
}
