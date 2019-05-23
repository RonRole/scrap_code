package main;

import java.util.*;
import java.util.stream.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] inputWords = sc.nextLine().split(" ");
		sc.close();

		Map<String,List<String>> map = Arrays.stream(inputWords).collect(Collectors.groupingBy(word -> word, LinkedHashMap::new, Collectors.toList()));
		map.forEach((key, value) -> System.out.println(key + " " + value.size()));
	}
}
