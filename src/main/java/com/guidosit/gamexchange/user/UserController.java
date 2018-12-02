package com.guidosit.gamexchange.user;

import com.guidosit.gamexchange.exchangeproposal.ExchangeProposalResponse;
import com.guidosit.gamexchange.game.GameNotFoundException;
import com.guidosit.gamexchange.game.GameRequest;
import com.guidosit.gamexchange.game.GameResponse;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public UserResponse getUser(Authentication auth) {
        return UserResponse.returnUser(userService.getUser(auth.getName()));
    }

    @GetMapping("/games")
    public List<UserGameResponse> getGamesFromUser(Authentication auth) throws UserNotFoundException {
        User userByEmail = userService.getUser(auth.getName());
        return UserGameResponse.returnGamesFromUserGames(userByEmail.getGames());
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Integer id){
        return UserResponse.returnUser(userService.getUser(id));
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest user){
        return UserResponse.returnUser(userService.createUser(user.getName(), Optional.ofNullable(user.getNickname()), user.getEmail(),
                user.getPassword(), Optional.ofNullable(user.getUsername()), user.getDdd(), user.getCellphone()));
    }

    @PostMapping("/game")
    public void addGame(@RequestBody GameRequest game, Authentication auth)
            throws UserNotFoundException, GameNotFoundException {
        userService.addGame(userService.getUser(auth.getName()), game.getGameId());
    }

    @GetMapping("/proposals")
    public List<ExchangeProposalResponse> getProposals(Authentication auth) throws UserNotFoundException {
        return ExchangeProposalResponse.returnListOfProposals(userService.getProposalsForUser(auth.getName()).orElse(new ArrayList<>()));
    }

}
