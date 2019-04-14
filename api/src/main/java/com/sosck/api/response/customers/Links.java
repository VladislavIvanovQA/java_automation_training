package com.sosck.api.response.customers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Links{
	private Addresses addresses;
	private Cards cards;
	private Self self;
	private Customer customer;
}
