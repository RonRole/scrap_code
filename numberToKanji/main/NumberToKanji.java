package main;

import java.util.*;

public class NumberToKanji {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("数字で入力！");
		int number = sc.nextInt();
		sc.close();
		String kanji = KanjiMapping.changeToKanji(number);
		System.out.println(kanji);
	}
}
