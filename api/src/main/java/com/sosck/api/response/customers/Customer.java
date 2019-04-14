package com.sosck.api.response.customers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer{
	private Embedded _embedded;
	private String href;
}
