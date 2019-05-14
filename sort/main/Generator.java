package main;

import java.util.*;
import java.util.stream.*;

import enums.*;

public class Generator {

	public Object generate(int size, SortSubject subject) {
		if(SortSubject.ARRAY.equals(subject)) {
			return generateArray(size);
		}
		if(SortSubject.LIST.equals(subject)) {
			return generateList(size);
		}
		return null;
	}


	private List<Integer> generateList(int size) {
		System.out.println("generate List of " + size + "...");

		List<Integer> list = new ArrayList<>();

		IntStream.generate(() -> (int)(Math.random()*size)).limit(size).forEach(list::add);

		return list;
	}

	private int[] generateArray(int size) {
		System.out.println("generate array of " + size + "...");

		int[] result = new int[size];

		IntStream.range(0, size).forEach(i -> result[i] = (int) (Math.random()*size));

		return result;
	}


}
