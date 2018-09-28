package com.guidosit.gamexchange.usergame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserGameResponse {

    private Integer userId;
    private Integer gameId;
    private LocalDate insertDate;

    public static List<UserGameResponse> returnUsersForGame(List<UserGame> userGames) {
        List<UserGameResponse> lst = new ArrayList<>();
        for (UserGame ug :
                userGames) {
            lst.add(new UserGameResponse(ug.getId().getUserId(), ug.getId().getGameId(), ug.getInsertDate()));
        }
        return lst;
    }
}
