package com.levelup;

import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class UsingGuavaOrdering1 {

	List<Integer> numbers;

	@Before
	public void setUp() {
		numbers = Lists.newArrayList(6, 4, 8, 6, 2, 3);
 	}

	@Test
	public void order_natural() {

		Collections.sort(numbers, Ordering.natural());

		System.out.println(numbers); // [2, 3, 4, 6, 6, 8]
	}

	@Test
	public void reverse() {

		Collections.sort(numbers, Ordering.natural().reversed());
		
		System.out.println(numbers); // [8, 6, 6, 4, 3, 2]
	}

	
	@Test
	public void min_max() {

		Integer maxValue = Ordering.natural().max(numbers);

		Integer minValue = Ordering.natural().min(numbers);

		System.out.println(maxValue); // 8
		System.out.println(minValue); // 2
	}

	Comparator<String> byLength = new Comparator<String>() {
		public int compare(String left, String right) {
			return Integer.compare(left.length(), right.length());
		}
	};

	@Test
	public void from_existing_comparator() {

		List<String> random = Lists.newArrayList("welcome", "to",
				"leveluplunch");

		Comparator<String> reversedByLength = Ordering.from(byLength).reversed();
		Collections.sort(random, reversedByLength);

		System.out.println(random); // [leveluplunch, welcome, to]
		
		//check if a list is ordered
		assertTrue(Ordering.from(reversedByLength).isOrdered(random));
	}

	@Test
	public void nulls_last_least_of() {

		List<String> random = Lists.newArrayList(null, "welcome", "to",
				"leveluplunch", null, null);

		System.out.println(Ordering.from(byLength).nullsLast().sortedCopy(random));
		
		List<String> firstTwo = Ordering.from(byLength).nullsLast()
				.leastOf(random, 2);

		System.out.println(firstTwo); //[to, welcome]
	}
	

}
