package com.github.ioloolo.zephyr.domain.user.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.ioloolo.zephyr.domain.user.data.User;
import com.github.ioloolo.zephyr.domain.user.dto.SteamResponseDto;
import com.github.ioloolo.zephyr.domain.user.repository.UserRepository;
import com.github.ioloolo.zephyr.domain.user.util.UserUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	private final UserRepository repository;

	private final UserUtil util;

	@PostMapping
	public ResponseEntity<?> userInfo(@RequestHeader("Authorization") long steamId) {

		SteamResponseDto steamInfo = util.getSteamInfo(steamId).orElseThrow();

		Optional<User> userOptional = repository.findBySteamId(steamId);
		if (userOptional.isEmpty()) {
			User user = User.builder().steamId(steamId).name(steamInfo.getName()).discord(0).ban(true).build();

			repository.save(user);
			userOptional = Optional.of(user);
		}
		User user = userOptional.get();

		if (user.isBan()) {
			return ResponseEntity.status(401).build();
		}

		return ResponseEntity.ok(Map.ofEntries(
				Map.entry("steamId", steamInfo.getSteamId()),
				Map.entry("name", steamInfo.getName()),
				Map.entry("avatar", steamInfo.getAvatar())
		));
	}
}
