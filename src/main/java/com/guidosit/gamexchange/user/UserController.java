package com.guidosit.gamexchange.user;

import com.guidosit.gamexchange.exchangeproposal.ExchangeProposalResponse;
import com.guidosit.gamexchange.game.GameNotFoundException;
import com.guidosit.gamexchange.game.GameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Integer id){
        return UserResponse.returnUser(userService.getUser(id));
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/{id}/game")
    public void addGame(@PathVariable Integer id, @RequestBody GameRequest game)
            throws UserNotFoundException, GameNotFoundException {
        userService.addGame(id, game.getGameId());
    }

    @GetMapping("/proposals")
    public List<ExchangeProposalResponse> getProposals(Principal principal) throws UserNotFoundException {
        Integer userId = new Integer(1);
        if (principal.getName().equalsIgnoreCase("elder@gmail.com")) {
            userId = new Integer(3);
        }
        if (principal.getName().equalsIgnoreCase("hugo@gmail.com")) {
            userId = new Integer(2);
        }

        return ExchangeProposalResponse.returnListOfProposals(userService.getProposalsForUser(userId).orElse(new ArrayList<>()));
    }

}
