package com.guidosit.gamexchange.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.guidosit.gamexchange.game.Game;
import com.guidosit.gamexchange.game.GameResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponse {

    private Integer id;
    private String name;
    private List<GameResponse> games;

    public CategoryResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CategoryResponse returnCategory(Category category) {
        List<GameResponse> games = new ArrayList<>();

        for (Game g : category.getGames()) {
//            g.setCategory(null);
            games.add(new GameResponse(g.getId(), g.getName(), g.getDescription(), g.getPlatform()));
        }

        return new CategoryResponse(category.getId(), category.getName(), games);
    }

    public static CategoryResponse returnCategoryWithoutGames(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}
