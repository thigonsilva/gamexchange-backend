package com.guidosit.gamexchange.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data @Setter
public class GameResponse {

    private Game game;

    public static GameResponse returnGame(Game game) {
        game.getCategory().setGames(null);
        return new GameResponse(game);
    }
}
