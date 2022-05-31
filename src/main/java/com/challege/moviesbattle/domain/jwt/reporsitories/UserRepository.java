package com.challege.moviesbattle.domain.jwt.reporsitories;

import com.challege.moviesbattle.domain.jwt.entities.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDao, Integer> {

    Optional<UserDao> findByUsername(String username);
}
