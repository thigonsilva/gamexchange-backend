package com.guidosit.gamexchange.exchangeproposal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Proposal was not found!")
public class ProposalNotFoundException extends RuntimeException {
}
