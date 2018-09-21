package com.guidosit.gamexchange.game;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.guidosit.gamexchange.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne
    private Category category;
}

