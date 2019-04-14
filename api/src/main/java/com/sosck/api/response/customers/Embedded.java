package com.sosck.api.response.customers;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Embedded{
	private List<CustomerItem> customer;
}