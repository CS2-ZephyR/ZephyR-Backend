package com.github.ioloolo.zephyr.domain.skin.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.ioloolo.zephyr.domain.skin.controller.payload.UpdateGloveRequest;
import com.github.ioloolo.zephyr.domain.skin.controller.payload.UpdateKnifeRequest;
import com.github.ioloolo.zephyr.domain.skin.controller.payload.UpdateModelRequest;
import com.github.ioloolo.zephyr.domain.skin.controller.payload.UpdateMusicRequest;
import com.github.ioloolo.zephyr.domain.skin.controller.payload.UpdateSkinRequest;
import com.github.ioloolo.zephyr.domain.skin.controller.payload.UpdateSmokeRequest;
import com.github.ioloolo.zephyr.domain.skin.data.Skin;
import com.github.ioloolo.zephyr.domain.skin.repository.SkinRepository;
import com.github.ioloolo.zephyr.domain.user.data.User;
import com.github.ioloolo.zephyr.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/skin")
@RequiredArgsConstructor
public class SkinController {

	private final SkinRepository repository;
	private final UserRepository userRepository;

	@PostMapping
	public ResponseEntity<?> skinInfo(@RequestHeader("Authorization") long steamId) {

		Optional<User> userOptional = userRepository.findBySteamId(steamId);
		if (userOptional.isEmpty() || userOptional.get().isBan()) {
			return ResponseEntity.status(401).build();
		}

		Optional<Skin> skinOptional = repository.findBySteamId(steamId);
		if (skinOptional.isEmpty()) {
			Skin skin = new Skin();
			skin.setSteamId(steamId);

			repository.save(skin);

			skinOptional = Optional.of(skin);
		}
		Skin skin = skinOptional.orElseThrow();

		return ResponseEntity.ok(skin);
	}

	@PutMapping("/detail")
	@Transactional
	public ResponseEntity<?> updateSkinDetail(
			@RequestHeader("Authorization") long steamId, @RequestBody UpdateSkinRequest request
	) {

		Optional<User> userOptional = userRepository.findBySteamId(steamId);
		if (userOptional.isEmpty() || userOptional.get().isBan()) {
			return ResponseEntity.status(401).build();
		}

		int weapon = request.getWeapon();
		int paint = request.getPaint();
		int seed = request.getSeed();
		double wear = request.getWear();
		String name = request.getName();

		Skin skin = repository.findBySteamId(steamId).orElseThrow();
		Map<Integer, Skin.SkinDetail> detail = skin.getDetail();

		if (paint != 0) {
			if (!detail.containsKey(weapon)) {
				detail.put(weapon, new Skin.SkinDetail());
			}

			Skin.SkinDetail skinDetail = detail.get(weapon);
			skinDetail.setPaint(paint);
			skinDetail.setSeed(seed);
			skinDetail.setWear(wear);
			skinDetail.setName(name);
		} else {
			detail.remove(weapon);
		}

		repository.save(skin);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/model")
	public ResponseEntity<?> updateModel(
			@RequestHeader("Authorization") long steamId, @RequestBody UpdateModelRequest request
	) {

		Optional<User> userOptional = userRepository.findBySteamId(steamId);
		if (userOptional.isEmpty() || userOptional.get().isBan()) {
			return ResponseEntity.status(401).build();
		}

		String team = request.getTeam();
		String model = request.getModel();

		Skin skin = repository.findBySteamId(steamId).orElseThrow();
		if (team.equalsIgnoreCase("t")) {
			skin.getAgent().setT(model);
		} else {
			skin.getAgent().setCt(model);
		}

		repository.save(skin);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/music")
	public ResponseEntity<?> updateMusic(
			@RequestHeader("Authorization") long steamId, @RequestBody UpdateMusicRequest request
	) {

		Optional<User> userOptional = userRepository.findBySteamId(steamId);
		if (userOptional.isEmpty() || userOptional.get().isBan()) {
			return ResponseEntity.status(401).build();
		}

		int music = request.getMusic();

		Skin skin = repository.findBySteamId(steamId).orElseThrow();
		skin.setMusic(music);

		repository.save(skin);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/glove")
	public ResponseEntity<?> updateGlove(
			@RequestHeader("Authorization") long steamId, @RequestBody UpdateGloveRequest request
	) {

		Optional<User> userOptional = userRepository.findBySteamId(steamId);
		if (userOptional.isEmpty() || userOptional.get().isBan()) {
			return ResponseEntity.status(401).build();
		}

		int glove = request.getGlove();

		Skin skin = repository.findBySteamId(steamId).orElseThrow();
		skin.setGlove(glove);

		repository.save(skin);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/knife")
	public ResponseEntity<?> updateKnife(
			@RequestHeader("Authorization") long steamId, @RequestBody UpdateKnifeRequest request
	) {

		Optional<User> userOptional = userRepository.findBySteamId(steamId);
		if (userOptional.isEmpty() || userOptional.get().isBan()) {
			return ResponseEntity.status(401).build();
		}

		String knife = request.getKnife();

		Skin skin = repository.findBySteamId(steamId).orElseThrow();
		skin.setKnife(knife);

		repository.save(skin);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/smoke")
	public ResponseEntity<?> updateSmokeColor(
			@RequestHeader("Authorization") long steamId, @RequestBody UpdateSmokeRequest request
	) {

		Optional<User> userOptional = userRepository.findBySteamId(steamId);
		if (userOptional.isEmpty() || userOptional.get().isBan()) {
			return ResponseEntity.status(401).build();
		}

		int r = request.getR();
		int g = request.getG();
		int b = request.getB();

		Skin skin = repository.findBySteamId(steamId).orElseThrow();
		skin.setSmoke(Map.ofEntries(Map.entry("R", r), Map.entry("G", g), Map.entry("B", b)));

		repository.save(skin);

		return ResponseEntity.ok().build();
	}
}
