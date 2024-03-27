package com.github.ioloolo.zephyr.domain.user.data;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "User")
public class User {

	@Id
	@MongoId
	@JsonIgnore
	private ObjectId id;

	@Field("SteamId")
	private long steamId;

	@Field("Name")
	private String name;

	@Field("Discord")
	private long discord;

	@Field("Ban")
	private boolean ban;
}
