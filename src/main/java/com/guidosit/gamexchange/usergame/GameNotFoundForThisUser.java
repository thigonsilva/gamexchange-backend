package com.guidosit.gamexchange.usergame;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The user does not have this game.")
public class GameNotFoundForThisUser extends Exception {
    public GameNotFoundForThisUser() {
        super();
    }

    public GameNotFoundForThisUser(String message) {
        super(message);
    }
}
