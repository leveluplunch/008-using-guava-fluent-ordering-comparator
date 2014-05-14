package com.levelup;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class UsingGuavaOrdering2 {

	List<Address> addresses;

	@Before
	public void setUp() {

		addresses = Lists.newArrayList();

		addresses.add(new Address("16 Emerald Impasse", "Mozart",
				State.Washington, "05873-2086"));
		addresses.add(new Address("7966 Iron Panda Freeway", "Sour Dough Flat",
				State.Wisconsin, "82254-8067"));
		addresses.add(new Address("7037 Green Butterfly Swale", "Ritzville",
				State.California, "82918-7511"));
		addresses.add(new Address("8180 Middle Campus", "Winklepleck Grove",
				State.Florida, "82648-0809"));
		addresses.add(new Address("369 Pleasant Bear Heights", "Goobertown",
				State.Alabama, "05427-6375"));
	}

	@Test
	public void by_field() {

		// sort a list of object by a field/attribute
		Ordering<Address> byCity = Ordering.natural().onResultOf(
				new Function<Address, String>() {
					public String apply(Address address) {
						return address.city;
					}
				});
		List<Address> orderedAddress = byCity.sortedCopy(addresses);

		for (Address address : orderedAddress) {
			System.out.println(address);
		}
	}

	@Test
	public void comparing_string() {

		// sort address by address line
		// case insensitive order
		List<Address> sortedList = Ordering.from(String.CASE_INSENSITIVE_ORDER)
				.onResultOf(new Function<Address, String>() {
					public String apply(Address address) {
						return address.address;
					}
				}).sortedCopy(addresses);

		for (Address address : sortedList) {
			System.out.println(address.getAddress());
		}

	}

	@Test
	public void explicit_order_or_order_enum() {

		// Order states, an enum, in an explicit order
		Ordering<State> byMidwestState = Ordering.explicit(State.Illinois,
				State.Indiana, State.Iowa, State.Michigan, State.Minnesota,
				State.Ohio, State.Wisconsin);

		List<State> states = Lists.newArrayList(State.Iowa, State.Indiana,
				State.Wisconsin, State.Minnesota, State.Ohio, State.Illinois);

		Collections.sort(states, byMidwestState);

		System.out.println(states);
	}

	@Test
	public void test() {

		// sort address by state enum in a specific order
		List<Address> addressesSorted = State.stateOrder.onResultOf(
				Address.onState).sortedCopy(addresses);

		System.out.println(addressesSorted);
	}

	Comparator<String> byLength = new Comparator<String>() {
		public int compare(String left, String right) {
			return Integer.compare(left.length(), right.length());
		}
	};

	
	@Test
	public void chain_comparators() {

		//	When reading a chain of Ordering calls, work "backward" from right to left. 
		
		List<String> random = Lists.newArrayList("WELCOME1", "welcome", "to",
				"leveluplunch");

		List<String> sorted = Ordering.from(String.CASE_INSENSITIVE_ORDER)
				.compound(byLength).sortedCopy(random);
		
		System.out.println(sorted);
	}

}
