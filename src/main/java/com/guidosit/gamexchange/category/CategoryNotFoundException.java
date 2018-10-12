package com.guidosit.gamexchange.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Category doesn't exist!")
public class CategoryNotFoundException extends Exception {
}
