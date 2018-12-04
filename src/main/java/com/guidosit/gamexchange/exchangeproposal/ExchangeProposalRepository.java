package com.guidosit.gamexchange.exchangeproposal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeProposalRepository extends JpaRepository<ExchangeProposal, Integer> {

    @Query(value = " select ep.* from exchange_proposal ep " +
            " where (ep.target_user_id = :userId or ep.requester_user_id = :userId) " +
            " and ep.accepted = true ", nativeQuery = true)
    Optional<List<ExchangeProposal>> getAllFinishedProposalsForUser(@Param("userId") Integer userId);

    @Query(value = " select ep.* from exchange_proposal ep " +
            " where target_user_id = :userId " +
            " and ep.accepted is null and canceled is null ", nativeQuery = true)
    Optional<List<ExchangeProposal>> getAllProposalsForUser(@Param("userId") Integer userId);
}
