package com.guidosit.gamexchange.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "This email is already in use!")
public class UserEmailIsAlreadyInUseException extends RuntimeException {
}
