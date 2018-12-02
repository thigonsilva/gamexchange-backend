package com.guidosit.gamexchange.user;

import com.guidosit.gamexchange.game.GameResponse;
import com.guidosit.gamexchange.usergame.UserGame;
import com.guidosit.gamexchange.usergame.UserGameResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data @Setter
public class UserResponse {

    private Integer id;
    private String name, nickname, email, ddd, cellphone;
    private List<GameResponse> games;

    public UserResponse(Integer id, String name, String nickname, String email) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }

    public static UserResponse returnUser(User user) {
        List<GameResponse> games = new ArrayList<>();
        for (UserGame userGame: user.getGames()){
            games.add(new GameResponse(userGame.getId(), userGame.getGame().getName(),
                    userGame.getGame().getDescription(), userGame.getGame().getPlatform(), userGame.getInsertDate(), userGame.getTradeDate(), userGame.getIsAvailable()));
        }
        return new UserResponse(user.getId(), user.getName(), user.getNickname(), user.getEmail(), user.getDdd(), user.getCellphone(), games);
    }

    public static List<UserResponse> returnUsers(List<User> users) {
        List<UserResponse> lst = new ArrayList<>();
        for (User u :
                users) {
            lst.add(new UserResponse(u.getId(), u.getName(), u.getNickname(), u.getEmail()));
        }

        return null;
    }

    public static List<UserGameResponse> returnUserGames(User user) {
        return UserGameResponse.returnUsersForGame(user.getGames());
    }
}
