package com.guidosit.gamexchange.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = " select u.* from user u " +
            " inner join user_game ug on ug.user_id = u.id " +
            " where ug.game_id = :id ", nativeQuery = true)
    Optional<List<User>> getUsersForGame(Integer id);

}
