package com.guidosit.gamexchange.game;

import com.guidosit.gamexchange.usergame.GameNotFoundForThisUser;
import com.guidosit.gamexchange.usergame.UserGameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameResponse> getGames(Authentication auth){
        List<GameResponse> gameResponseList = new ArrayList<>();
        Optional<List<Game>> games;

        if (null == auth) games = gameService.getGames();
        else games = gameService.getGamesFromOtherUsers(auth.getName());

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
    public Game createGame(@RequestBody GameRequest game){
        return gameService.save(new Game(game.getName(), game.getDescription(),
                game.getPlatform(), game.getCategoryId()));
    }

    @PostMapping("/{id}/propose")
    public void proposeExchange(@PathVariable Integer id, Authentication auth) throws GameNotFoundForThisUser {
        gameService.proposeExchange(id, auth.getName());
    }

}
