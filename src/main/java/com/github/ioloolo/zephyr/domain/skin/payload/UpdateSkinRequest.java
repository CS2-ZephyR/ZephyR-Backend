package com.github.ioloolo.zephyr.domain.skin.payload;

import lombok.Data;

@Data
public class UpdateSkinRequest {

	private int    weapon;
	private int    paint;
	private int    seed;
	private double wear;
	private String name;
}
