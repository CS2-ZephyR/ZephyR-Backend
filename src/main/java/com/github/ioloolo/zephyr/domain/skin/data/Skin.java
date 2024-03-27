package com.github.ioloolo.zephyr.domain.skin.data;

import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Document(collection = "Skin")
public class Skin {

	@MongoId
	@JsonIgnore
	private ObjectId id;

	@Field("SteamId")
	private long steamId;

	@Field("Knife")
	private String knife = "";

	@Field("Glove")
	private int glove = 0;

	@Field("Agent")
	private Agent agent = new Agent();

	@Field("Music")
	private int music = 0;

	@Field("Detail")
	private Map<Integer, SkinDetail> detail = Map.of();

	@Data
	public static class Agent {

		@Field("Ct")
		private String ct = "";

		@Field("T")
		private String t = "";
	}

	@Data
	public static class SkinDetail {

		@Field("Paint")
		private int paint = 0;

		@Field("Seed")
		private int seed = 0;

		@Field("Wear")
		private double wear = 0.0;

		@Field("Name")
		private String name = "";
	}
}
