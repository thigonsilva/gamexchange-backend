package com.guidosit.gamexchange.user;

import com.guidosit.gamexchange.usergame.UserGame;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data @Setter
public class UserResponse {
    private User user;

    public static UserResponse returnUser(User user) {
        for (UserGame userGame: user.getGames()){
            userGame.setUser(null);
            userGame.getGame().setUsers(null);
            userGame.getGame().getCategory().setGames(null);
        }
        return new UserResponse(user);
    }
}
