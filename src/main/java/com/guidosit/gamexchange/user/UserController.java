package com.guidosit.gamexchange.user;

import com.guidosit.gamexchange.game.GameNotFoundException;
import com.guidosit.gamexchange.game.GameRequest;
import com.guidosit.gamexchange.proposal.ProposalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
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

    @PostMapping("/{id}")
    public void proposeExchange(@PathVariable Integer id, @RequestBody ProposalRequest request){

    }

}
