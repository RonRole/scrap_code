package kuku;

import java.util.stream.*;

public class Part2 {
	public static void main(String ...args) {
		IntStream.rangeClosed(1, 9)
					.mapToObj(num1 -> IntStream.rangeClosed(1, 9)
												.mapToObj(num2 -> String.format("%02d", num1*num2))
												.collect(Collectors.joining(" ")))
					.forEach(System.out::println);
	}
}
