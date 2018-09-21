package com.guidosit.gamexchange.category;

import com.guidosit.gamexchange.game.Game;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data @Setter
public class CategoryResponse {

    private Category category;

    public static CategoryResponse returnCategory(Category category) {
        for (Game g :
                category.getGames()) {
            g.setCategory(null);
        }

        return new CategoryResponse(category);
    }
}
