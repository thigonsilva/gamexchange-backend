package com.guidosit.gamexchange.login;

import com.guidosit.gamexchange.user.User;
import com.guidosit.gamexchange.user.UserNotFoundException;
import com.guidosit.gamexchange.user.UserResponse;
import com.guidosit.gamexchange.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserResponse login(Authentication auth) throws UserNotFoundException {

        System.out.println("auth.getName() = " + auth.getName());

        String username = auth.getName();
        User user;
        if (username.indexOf("@") != -1)
            user = userService.getUserByEmail(auth.getName());
        else{
            user = userService.getUser(auth.getName());
        }

        return UserResponse.returnUser(user);
    }

}
