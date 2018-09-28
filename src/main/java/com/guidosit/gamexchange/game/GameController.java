package com.guidosit.gamexchange.game;

import com.guidosit.gamexchange.user.User;
import com.guidosit.gamexchange.user.UserNotFoundException;
import com.guidosit.gamexchange.user.UserResponse;
import com.guidosit.gamexchange.usergame.UserGameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/game")

public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameResponse> getGames(){
        List<GameResponse> gameResponseList = new ArrayList<>();
        Optional<List<Game>> games = gameService.getGames();
        List<Game> lstGames = games.orElse(new ArrayList<>());
        for (Game g: lstGames) {
            gameResponseList.add(GameResponse.returnGame(g));
        }
        return gameResponseList;
    }

    @GetMapping("/{id}")
    public GameResponse getGame(@PathVariable Integer id) throws GameNotFoundException {
        return GameResponse.returnGame(gameService.getGame(id).orElseThrow(() -> new GameNotFoundException()));
    }

    @GetMapping("/{id}/users")
    public List<UserGameResponse> getUsersForGame(@PathVariable Integer id) throws GameNotFoundException {
        return UserGameResponse.returnUsersForGame(gameService.getUsersForGame(id).orElse(new ArrayList<>()));
    }

    @PostMapping
    public Game createGame(@RequestBody Game game){return gameService.save(game);}

}
