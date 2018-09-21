package com.guidosit.gamexchange.usergame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data @Getter
@Embeddable
public class UserGameId implements Serializable {
    @Column(name = "id_user")
    private Integer userId;
    @Column(name = "id_game")
    private Integer gameId;
}
