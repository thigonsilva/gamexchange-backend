package com.guidosit.gamexchange.game;

import com.guidosit.gamexchange.usergame.GameNotFoundForThisUser;
import com.guidosit.gamexchange.usergame.UserGameResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/{id}/propose")
    public void proposeExchange(@PathVariable Integer id, Principal principal) throws GameNotFoundForThisUser {
        Integer userId = new Integer(1);
        if (principal.getName().equalsIgnoreCase("elder@gmail.com")) {
            userId = new Integer(3);
        }
        if (principal.getName().equalsIgnoreCase("hugo@gmail.com")) {
            userId = new Integer(2);
        }

        gameService.proposeExchange(id, userId);
    }

}
