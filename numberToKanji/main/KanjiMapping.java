package main;

import java.util.*;
import java.util.stream.*;

public class KanjiMapping {
	private static final Map<Integer,String> kanjiMap = new HashMap<Integer, String>();

	static {
		kanjiMap.put(0, "〇");
		kanjiMap.put(1, "一");
		kanjiMap.put(2, "二");
		kanjiMap.put(3, "三");
		kanjiMap.put(4, "四");
		kanjiMap.put(5, "五");
		kanjiMap.put(6, "六");
		kanjiMap.put(7, "七");
		kanjiMap.put(8, "八");
		kanjiMap.put(9, "九");
	}

	public static String changeToKanji(int number) {
		String[] tempStringArray = String.valueOf(number).split("");
		String result = Arrays.stream(tempStringArray)
								.map(num -> kanjiMap.get(Integer.valueOf(num)))
								.collect(Collectors.joining());

		return result;
	}
}
