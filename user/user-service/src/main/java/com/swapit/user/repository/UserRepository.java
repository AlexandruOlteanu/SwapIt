package com.swapit.user.repository;

import com.swapit.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUserId(Integer userId);
    Optional<User> findUserByUsername(String username);
    @Query("select u.username from User u where u.userId = :userId")
    Optional<String> findUsernameByUserId(Integer userId);
    @Query("select u.name from User u where u.userId = :userId")
    Optional<String> findNameByUserId(Integer userId);
    @Query("select u.surname from User u where u.userId = :userId")
    Optional<String> findSurnameByUserId(Integer userId);
}
