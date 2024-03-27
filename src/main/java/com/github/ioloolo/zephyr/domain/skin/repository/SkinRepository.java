package com.github.ioloolo.zephyr.domain.skin.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.ioloolo.zephyr.domain.skin.data.Skin;

@Repository
public interface SkinRepository extends MongoRepository<Skin, String> {

	Optional<Skin> findBySteamId(long SteamId);
}
