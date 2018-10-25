package com.guidosit.gamexchange.user;

import com.guidosit.gamexchange.exchangeproposal.ExchangeProposal;
import com.guidosit.gamexchange.exchangeproposal.ExchangeProposalService;
import com.guidosit.gamexchange.game.Game;
import com.guidosit.gamexchange.game.GameNotFoundException;
import com.guidosit.gamexchange.game.GameService;
import com.guidosit.gamexchange.usergame.UserGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameService gameService;
    @Autowired
    private ExchangeProposalService exchangeProposalService;
    @Autowired
    private UserGameService userGameService;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

    public void addGame(User user, Integer gameId) throws UserNotFoundException, GameNotFoundException {
        user.addGame(gameService.getGame(gameId).orElseThrow(() ->
                new GameNotFoundException("Jogo não encontrado")));
        userRepository.save(user);
    }

    public Optional<List<User>> getUsersForGame(Integer id) {
        return userRepository.getUsersForGame(id);
    }

    public Optional<List<ExchangeProposal>> getProposalsForUser(Integer id) throws UserNotFoundException {
        return exchangeProposalService.getProposals(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException()));
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException());
    }
}
