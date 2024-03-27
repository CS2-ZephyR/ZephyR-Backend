package com.github.ioloolo.zephyr.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SteamResponseDto {

	long steamId;

	String name;
	String avatar;
}
