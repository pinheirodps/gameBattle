package com.challege.moviesbattle.domain.jwt.reporsitories;

import com.challege.moviesbattle.domain.jwt.entities.UserDao;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDao, Integer> {

    UserDao findByUsername(String username);
}
