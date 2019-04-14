package com.sosck.api.request.card;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Setter
@Getter
public class Card{
	private String expires;
	private String longNum;
	private String ccv;
}
