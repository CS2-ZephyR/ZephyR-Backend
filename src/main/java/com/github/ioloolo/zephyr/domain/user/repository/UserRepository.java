package com.github.ioloolo.zephyr.domain.user.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.ioloolo.zephyr.domain.user.data.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findBySteamId(long SteamId);
}
