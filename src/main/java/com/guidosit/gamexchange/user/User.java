package com.guidosit.gamexchange.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guidosit.gamexchange.game.Game;
import com.guidosit.gamexchange.role.Role;
import com.guidosit.gamexchange.usergame.UserGame;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name, nickname, email, password, username, ddd, cellphone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<UserGame> games = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    private List<Role> roles = new ArrayList<>();

    public User(String name, String nickname, String email, String password, String username, String ddd, String cellphone) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.username = username;
        this.ddd = ddd;
        this.cellphone = cellphone;
    }

    public void addGame(Game game) {
        UserGame userGame = new UserGame(this, game);
        this.games.add(userGame);
        game.getUsers().add(userGame);
    }

}
