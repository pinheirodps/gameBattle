package com.challenge.moviesbattle.domain.jwt.reporsitories;

import com.challenge.moviesbattle.domain.jwt.entities.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDao, Integer> {

    Optional<UserDao> findByUsername(String username);
}
