package com.guidosit.gamexchange.exchangeproposal;

import com.guidosit.gamexchange.usergame.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeProposalRepository extends JpaRepository<ExchangeProposal, Integer> {

    @Query(value = " select ep.* from exchange_proposal ep " +
            " where target_user_id = :userId", nativeQuery = true)
    Optional<List<ExchangeProposal>> getAllProposalsForUser(Integer userId);
}
