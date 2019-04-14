package com.sosck.api.request.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.annotation.Generated;

@Accessors(fluent = true)
@Getter
@Setter
@Generated("com.robohorse.robopojogenerator")
public class RegisterUser {

	@JsonProperty("password")
	private String password;

	@JsonProperty("email")
	private String email;

	@JsonProperty("username")
	private String username;
}