package com.guidosit.gamexchange.user;

import com.guidosit.gamexchange.game.Game;
import com.guidosit.gamexchange.game.GameNotFoundException;
import com.guidosit.gamexchange.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameService gameService;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

    public void addGame(Integer id, Integer gameId) throws UserNotFoundException, GameNotFoundException {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();
            user.addGame(gameService.getGame(gameId).orElseThrow(() ->
                    new GameNotFoundException("Jogo não encontrado")));
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("Usuario de ID "+ id +" não encontrado.");
        }
    }
}
