package com.guidosit.gamexchange.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {

    private String name, nickname, email, password, username, ddd, cellphone;

}
