package com.guidosit.gamexchange.game;


import com.guidosit.gamexchange.exchangeproposal.ExchangeProposalService;
import com.guidosit.gamexchange.user.UserService;
import com.guidosit.gamexchange.usergame.GameNotFoundForThisUser;
import com.guidosit.gamexchange.usergame.UserGame;
import com.guidosit.gamexchange.usergame.UserGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserGameService userGameService;
    @Autowired
    private ExchangeProposalService exchangeProposalService;
    @Autowired
    private UserService userService;

    public Game save(Game game){
        return gameRepository.save(game);
    }

    public Optional<Game> getGame(Integer id){
        return gameRepository.findById(id);
    }

    public Optional<List<Game>> getGames() {
        return gameRepository.getAvailableGames();
    }

    public Optional<Game> getGame(Game game) {
        return gameRepository.findOne(Example.of(game));
    }

    public Optional<List<UserGame>> getUsersForGame(Integer id) throws GameNotFoundException {
        return userGameService.getUsersForGame(gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException()));
    }

    public void proposeExchange(Integer gameId, String username) throws GameNotFoundForThisUser {
        exchangeProposalService.makeProposal(
                userGameService.getUserGame(gameId).orElseThrow(() -> new GameNotFoundForThisUser()), userService.getUser(username).getId());
    }

    public Optional<List<Game>> getGamesFromOtherUsers(String username) {
        return gameRepository.getAvailableGames(userService.getUser(username).getId());
    }
}
