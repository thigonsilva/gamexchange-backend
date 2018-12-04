package com.guidosit.gamexchange.user;

import com.guidosit.gamexchange.exchangeproposal.ExchangeProposal;
import com.guidosit.gamexchange.exchangeproposal.ExchangeProposalResponse;
import com.guidosit.gamexchange.exchangeproposal.ExchangeProposalService;
import com.guidosit.gamexchange.game.Game;
import com.guidosit.gamexchange.game.GameNotFoundException;
import com.guidosit.gamexchange.game.GameService;
import com.guidosit.gamexchange.usergame.UserGameService;
import com.guidosit.gamexchange.utils.EncryptPasswordUtils;
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

    public Optional<User> getUser(String username) {
        if(username.indexOf("@") != -1)
            return userRepository.findUserByEmail(username);
        else
            return userRepository.findByUsername(username);
    }

    public void addGame(User user, Integer gameId) {
        user.addGame(gameService.getGame(gameId).orElseThrow(() -> new GameNotFoundException("Jogo não encontrado")));
        userRepository.save(user);
    }

    public Optional<List<User>> getUsersForGame(Integer id) {
        return userRepository.getUsersForGame(id);
    }

    public Optional<List<ExchangeProposal>> getProposalsForUser(String username) throws UserNotFoundException {
        return exchangeProposalService.getProposals(getUser(username).orElseThrow(() -> new UserNotFoundException()));
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException());
    }

    public User createUser(String name, Optional<String> nickname, String email, String password, Optional<String> username,
                           String ddd, String cellphone) {

        User user = new User(name, nickname.orElse(name.split(" ")[0]), email,
                EncryptPasswordUtils.encrytePassword(password), username.orElse(email.split("@")[0]),
                ddd, cellphone);

        if(userRepository.findUserByEmail(email).isPresent()) throw new UserEmailIsAlreadyInUseException();

        return userRepository.save(user);
    }

    public Optional<List<ExchangeProposal>> getTrades(String username) {
        return exchangeProposalService.getFinishedProposals(getUser(username).orElseThrow(() -> new UserNotFoundException()));
    }
}
