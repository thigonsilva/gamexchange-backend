package com.guidosit.gamexchange.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data @Setter
public class GameRequest {
    private Integer gameId, categoryId;
    private String name, description, platform;
}
