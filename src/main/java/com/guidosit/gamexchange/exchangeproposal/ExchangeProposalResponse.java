package com.guidosit.gamexchange.exchangeproposal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.guidosit.gamexchange.game.GameResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExchangeProposalResponse {

    private Integer id, requesterId, targetUserId;
    private String requesterName, targetUserName;
    private LocalDate requestDate, exchangeDate;
    private GameResponse game, requesterGame;

    public ExchangeProposalResponse(Integer id, Integer requesterId, String requesterName, LocalDate requestDate, GameResponse game) {
        this.id = id;
        this.requesterId = requesterId;
        this.requesterName = requesterName;
        this.requestDate = requestDate;
        this.game = game;
    }

    public static List<ExchangeProposalResponse> returnListOfProposals(List<ExchangeProposal> proposalsForUser) {
        List<ExchangeProposalResponse> lst = new ArrayList<>();
        for (ExchangeProposal ep: proposalsForUser) {
            lst.add(new ExchangeProposalResponse(ep.getId(),
                    ep.getRequesterId().getId(),
                    ep.getRequesterId().getNickname(),
                    ep.getRequestDate(),
                    GameResponse.returnGame(ep.getTarget().getGame())));
        }
        return lst;
    }

    public static List<ExchangeProposalResponse> returnListOfProposalsFull(List<ExchangeProposal> proposalsForUser) {
        List<ExchangeProposalResponse> lst = new ArrayList<>();
        for (ExchangeProposal ep: proposalsForUser) {
            lst.add(new ExchangeProposalResponse(ep.getId(),
                    ep.getRequesterId().getId(),
                    ep.getTarget().getUser().getId(),
                    ep.getRequesterId().getNickname(),
                    ep.getTarget().getUser().getNickname(),
                    ep.getRequestDate(), ep.getExchangeDate(),
                    GameResponse.returnGame(ep.getRequester().getGame()),
                    GameResponse.returnGame(ep.getTarget().getGame())));
        }
        return lst;
    }

    public static ExchangeProposalResponse returnExchange(ExchangeProposal acceptProposal) {
        return new ExchangeProposalResponse(acceptProposal.getId(),
                acceptProposal.getRequesterId().getId(),
                acceptProposal.getTarget().getUser().getId(),
                acceptProposal.getRequesterId().getNickname(),
                acceptProposal.getTarget().getUser().getNickname(),
                acceptProposal.getRequestDate(), acceptProposal.getExchangeDate(),
                GameResponse.returnGame(acceptProposal.getRequester().getGame()),
                GameResponse.returnGame(acceptProposal.getTarget().getGame()));
    }
}
