package com.levelup;

import com.google.common.base.Function;


public class Address {

	String address;
	String city;
	State state;
	String zip;

	public Address(String address, String city, State state, String zip) {
		super();
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	static Function<Address, State> onState = new Function<Address, State>() {
		@Override
		public State apply(Address input) {
			return input.state;
		}
	};

	
	@Override
	public String toString() {
		return com.google.common.base.Objects.toStringHelper(this)
				.add("address", address).add("city", city).add("state", state)
				.add("zip", zip).toString();
	}
	
	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public State getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public static Function<Address, State> getOnState() {
		return onState;
	}
	
}
