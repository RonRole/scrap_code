package main;

import java.util.*;

public class Haiku {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String text1 = sc.nextLine();
		String text2 = sc.nextLine();
		String text3 = sc.nextLine();

		sc.close();

		String[][] texts = new String[][] {text3.split(""), text2.split(""), text1.split("")};

		int max = 0;
		for(String[] text : texts) {
			max = Math.max(text.length, max);
		}

		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < max; i++) {
			for(String[] text : texts) {
				if(i < text.length) {
					sb.append(text[i]);
					continue;
				}
				sb.append("　");
			}
			sb.append("\r\n");
		}
		System.out.println(sb);
	}
}
