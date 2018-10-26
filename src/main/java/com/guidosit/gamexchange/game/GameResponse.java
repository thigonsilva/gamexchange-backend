package com.guidosit.gamexchange.game;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.guidosit.gamexchange.user.UserResponse;
import com.guidosit.gamexchange.usergame.UserGame;
import com.guidosit.gamexchange.usergame.UserGameId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameResponse {

    private Integer id;
    private String name;
    private String description;
    private String platform;
    private LocalDate insertDate;
    private LocalDate tradeDate;
    private Boolean isAvailable;
    private List<UserResponse> users;

    public GameResponse(Integer id, String name, String description, String platform) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.platform = platform;
    }

    public GameResponse(UserGameId id, String name, String description, String platform, LocalDate insertDate, LocalDate tradeDate, Boolean isAvailable) {
        this.id = id.getGameId();
        this.name = name;
        this.description = description;
        this.platform = platform;
        this.insertDate = insertDate;
        this.tradeDate = tradeDate;
        this.isAvailable = isAvailable;
    }

    public static GameResponse returnGame(Game game) {
        return new GameResponse(game.getId(), game.getName(), game.getDescription(), game.getPlatform());
    }


    public static List<GameResponse> returnGames(List<UserGame> games) {
        List<GameResponse> lst = new ArrayList<>();
        for (UserGame ug :
                games) {
            lst.add(GameResponse.returnGame(ug.getGame()));
        }
        return lst;
    }
}
