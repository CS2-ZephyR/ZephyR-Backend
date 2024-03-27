package com.github.ioloolo.zephyr.domain.user.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.ioloolo.zephyr.domain.user.dto.SteamResponseDto;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component
public class ImplUserUtil implements UserUtil {

	private static final RestTemplate restTemplate = new RestTemplate();

	private static final String BASE_URL = "https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=%s&steamids=%s";

	@Value("${app.steam}")
	private String steamKey;

	public Optional<SteamResponseDto> getSteamInfo(long steamId) {

		String rawJson = restTemplate.getForEntity(BASE_URL.formatted(steamKey, steamId), String.class).getBody();
		JsonObject json = new Gson().fromJson(rawJson, JsonObject.class);

		JsonArray players = json.getAsJsonObject("response").getAsJsonArray("players");

		if (players.isEmpty()) {
			return Optional.empty();
		}

		JsonObject steamUser = players.get(0).getAsJsonObject();

		return Optional.of(SteamResponseDto.builder()
								   .steamId(steamId)
								   .name(steamUser.get("personaname").getAsString())
								   .avatar(steamUser.get("avatarfull").getAsString())
								   .build());
	}
}
