package com.guidosit.gamexchange.login;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Wrong password.")
public class LoginFailureException extends Exception {
}
