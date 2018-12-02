package com.guidosit.gamexchange.usergame;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserGameResponse {

    private String name;

    private Integer userId;
    private Integer gameId;
    private String description;
    private String platform;
    private LocalDate insertDate;
    private Boolean isAvailable;

    public UserGameResponse(Integer userId, Integer gameId, LocalDate insertDate) {
        this.userId = userId;
        this.gameId = gameId;
        this.insertDate = insertDate;
    }

    public UserGameResponse(Integer gameId, String name, String description, String platform, LocalDate insertDate, Boolean isAvailable) {
        this.name = name;
        this.gameId = gameId;
        this.description = description;
        this.platform = platform;
        this.insertDate = insertDate;
        this.isAvailable = isAvailable;
    }

    public static List<UserGameResponse> returnUsersForGame(List<UserGame> userGames) {
        List<UserGameResponse> lst = new ArrayList<>();
        for (UserGame ug :
                userGames) {
            lst.add(new UserGameResponse(ug.getId().getUserId(), ug.getId().getGameId(), ug.getInsertDate()));
        }
        return lst;
    }

    public static List<UserGameResponse> returnGamesFromUserGames(List<UserGame> userGames) {
        List<UserGameResponse> lst = new ArrayList<>();
        for (UserGame ug :
                userGames) {
            lst.add(new UserGameResponse(ug.getId().getGameId(), ug.getGame().getName(),
                    ug.getGame().getDescription(), ug.getGame().getPlatform(), ug.getInsertDate(),
                    ug.getIsAvailable()));
        }
        return lst;
    }
}
