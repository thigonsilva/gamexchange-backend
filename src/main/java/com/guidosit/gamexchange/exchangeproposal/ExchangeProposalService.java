package com.guidosit.gamexchange.exchangeproposal;

import com.guidosit.gamexchange.game.GameNotFoundException;
import com.guidosit.gamexchange.user.User;
import com.guidosit.gamexchange.user.UserService;
import com.guidosit.gamexchange.usergame.UserGame;
import com.guidosit.gamexchange.usergame.UserGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeProposalService {

    @Autowired
    private ExchangeProposalRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserGameService userGameService;

    public void makeProposal(UserGame userGame, User requesterUserId) {
        repository.save(new ExchangeProposal(requesterUserId, userGame));
    }

    public Optional<List<ExchangeProposal>> getProposals(User user) {
        return repository.getAllProposalsForUser(user.getId());
    }

    public ExchangeProposal acceptProposal(Integer proposalId, Integer requesterGameId) {
        ExchangeProposal exchangeProposal =
                repository.findById(proposalId).orElseThrow(() -> new ProposalNotFoundException());

        UserGame requesterUserGame = userGameService.getUserGame(exchangeProposal.getRequesterId().getId(), requesterGameId)
                .orElseThrow(() -> new GameNotFoundException());

        exchangeProposal.setAccepted(Boolean.TRUE);
        exchangeProposal.setExchangeDate(LocalDate.now());
        exchangeProposal.setRequester(requesterUserGame);

        userGameService.flagGamesExchanged(requesterUserGame, exchangeProposal.getTarget());

        return repository.save(exchangeProposal);
    }

    public Optional<List<ExchangeProposal>> getFinishedProposals(User user) {
        return repository.getAllFinishedProposalsForUser(user.getId());
    }
}
