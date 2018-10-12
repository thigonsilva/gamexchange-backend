package com.guidosit.gamexchange.exchangeproposal;

import com.guidosit.gamexchange.user.User;
import com.guidosit.gamexchange.user.UserService;
import com.guidosit.gamexchange.usergame.UserGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExchangeProposalService {

    @Autowired
    private ExchangeProposalRepository repository;
    @Autowired
    private UserService userService;

    public void makeProposal(UserGame userGame, Integer requesterUserId) {
        repository.save(new ExchangeProposal(userService.getUser(requesterUserId), userGame));
    }

    public Optional<List<ExchangeProposal>> getProposals(User user) {
        return repository.getAllProposalsForUser(user.getId());
    }
}
