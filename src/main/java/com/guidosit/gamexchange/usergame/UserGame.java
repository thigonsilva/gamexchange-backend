package com.guidosit.gamexchange.usergame;

import com.guidosit.gamexchange.game.Game;
import com.guidosit.gamexchange.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data @Setter
@Entity
public class UserGame {

    @EmbeddedId
    private UserGameId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("gameId")
    private Game game;

    private LocalDate insertDate, tradeDate;
    private Boolean isAvailable;

    public UserGame(User user, Game game) {
        this.user = user;
        this.game = game;
        this.insertDate = LocalDate.now();
        this.isAvailable = Boolean.TRUE;
        this.id = new UserGameId(user.getId(), game.getId());
    }
}
