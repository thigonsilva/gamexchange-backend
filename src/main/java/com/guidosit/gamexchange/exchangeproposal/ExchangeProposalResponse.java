package com.guidosit.gamexchange.exchangeproposal;

import com.guidosit.gamexchange.game.Game;
import com.guidosit.gamexchange.game.GameResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExchangeProposalResponse {

    private Integer id, userId;
    private LocalDate requestDate;
    private GameResponse game;

    public static List<ExchangeProposalResponse> returnListOfProposals(List<ExchangeProposal> proposalsForUser) {
        List<ExchangeProposalResponse> lst = new ArrayList<>();
        for (ExchangeProposal ep: proposalsForUser) {
            lst.add(new ExchangeProposalResponse(ep.getId(),
                    ep.getRequesterId().getId(),
                    ep.getRequestDate(),
                    GameResponse.returnGame(ep.getTarget().getGame())));
        }
        return lst;
    }
}
