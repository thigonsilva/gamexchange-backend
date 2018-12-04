package com.guidosit.gamexchange.exchangeproposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/exchange")
public class ExchangeProposalController {

    @Autowired
    private ExchangeProposalService exchangeProposalService;

    @PostMapping("/{proposalId}/accept")
    public ExchangeProposalResponse acceptExchange(@PathVariable Integer proposalId, @RequestBody ExchangeProposalRequest request, Authentication auth){
        return ExchangeProposalResponse.returnExchange(exchangeProposalService
                .acceptProposal(proposalId, request.getRequesterGameId()));
    }

}
