package com.guidosit.gamexchange.login;

import com.guidosit.gamexchange.user.User;
import com.guidosit.gamexchange.user.UserNotFoundException;
import com.guidosit.gamexchange.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity login(Authentication auth) throws UserNotFoundException {

        System.out.println("auth.getName() = " + auth.getName());

        User userByEmail = userService.getUserByEmail(auth.getName());
//        if (!userByEmail.getPassword().equals(auth.getCredentials())) throw new LoginFailureException();

        //httpSession.setAttribute("user", userByEmail);

        return ResponseEntity.ok().build();
    }

}
