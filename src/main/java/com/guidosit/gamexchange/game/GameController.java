package com.guidosit.gamexchange.game;

import com.guidosit.gamexchange.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/game")

public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameResponse> getGames(){
        List<GameResponse> gameResponseList = new ArrayList<>();
        List<Game> games = gameService.getGames();
        for (Game g: games) {
            gameResponseList.add(GameResponse.returnGame(g));
        }
        return gameResponseList;
    }

    @GetMapping("/{id}")
    public GameResponse getGame(@PathVariable Integer id) throws UserNotFoundException {
        return GameResponse.returnGame(gameService.getGame(id).orElseThrow(() -> new UserNotFoundException()));
    }

    @PostMapping
    public Game createGame(@RequestBody Game game){return gameService.save(game);}



}
