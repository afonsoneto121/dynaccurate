package com.br.dynaccurate.dynaccuratesolution.repository;

import com.br.dynaccurate.dynaccuratesolution.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    @Query(" {'nickname' : ?0 }")
    Optional<User> findByNickname(String nickname);
}
