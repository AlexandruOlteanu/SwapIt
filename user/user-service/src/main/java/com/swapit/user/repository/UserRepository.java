package com.swapit.user.repository;

import com.swapit.user.domain.User;
import com.swapit.user.utils.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByOauth2UserId(String oauth2UserId);
    @Query("select u.username from User u where u.username like :prefix%")
    Optional<List<String>> getUsernameStartingWith(String prefix);
    Optional<List<User>> findAllByStatus(UserStatus status);
}
