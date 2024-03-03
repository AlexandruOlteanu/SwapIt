package com.swapit.commons.repository;

import com.swapit.commons.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);

    @Query("select u.userId from User u where u.username=:username")
    Optional<Integer> getUserIdByUsername(String username);

}
