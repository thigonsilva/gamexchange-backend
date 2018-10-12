package com.guidosit.gamexchange.exchangeproposal;

import com.guidosit.gamexchange.user.User;
import com.guidosit.gamexchange.usergame.UserGame;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ExchangeProposal {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate requestDate;
    private LocalDate exchangeDate;
    private Boolean accepted;
    private Boolean canceled;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User requesterId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "requester_user_id", referencedColumnName = "user_id"),
            @JoinColumn(name = "given_game_id", referencedColumnName = "game_id")})
    private UserGame requester;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "target_user_id", referencedColumnName = "user_id"),
            @JoinColumn(name = "requested_game_id", referencedColumnName = "game_id")})
    private UserGame target;

    public ExchangeProposal(User requesterId, UserGame target) {
        this.requestDate = LocalDate.now();
        this.requesterId = requesterId;
        this.target = target;
    }
}
