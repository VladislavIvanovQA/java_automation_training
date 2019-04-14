package com.sosck.api.response.customers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerItem{
	private String firstName;
	private String lastName;
	private Links _links;
	private String id;
	private String username;

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerItem other = (CustomerItem) obj;
		if (!getId().equals(other.getId()))
			return false;
		if (!getFirstName().equals(other.getFirstName()))
			return false;
		if (!getLastName().equals(other.getLastName()))
			return false;
		if (!getUsername().equals(other.getUsername()))
			return false;
		return true;
	}
}
