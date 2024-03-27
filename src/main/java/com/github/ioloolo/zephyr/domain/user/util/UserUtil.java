package com.github.ioloolo.zephyr.domain.user.util;

import java.util.Optional;

import com.github.ioloolo.zephyr.domain.user.dto.SteamResponseDto;

public interface UserUtil {

	Optional<SteamResponseDto> getSteamInfo(long steamId);
}
