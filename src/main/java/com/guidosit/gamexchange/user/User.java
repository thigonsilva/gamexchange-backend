package com.guidosit.gamexchange.user;

import com.guidosit.gamexchange.game.Game;
import com.guidosit.gamexchange.usergame.UserGame;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data @Setter
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String nickname;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserGame> games = new ArrayList<>();


    public void addGame(Game game) {
        UserGame userGame = new UserGame(this, game);
        this.games.add(userGame);
        game.getUsers().add(userGame);
    }
}
