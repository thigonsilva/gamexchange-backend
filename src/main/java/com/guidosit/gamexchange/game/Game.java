package com.guidosit.gamexchange.game;

import com.guidosit.gamexchange.category.Category;
import com.guidosit.gamexchange.usergame.UserGame;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data @Setter
@Entity
public class Game {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String platform;

    @OneToMany(mappedBy = "game", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserGame> users = new ArrayList<>();
}

