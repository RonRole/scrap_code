package kuku;

import java.util.*;
import java.util.stream.*;

/**
 *
 * @author yamasaki
 * nマス掛け算
 * 値が大きすぎると固まるので注意
 */

public class Main {
	public static void main(String[] args) {
		Procedure proc = new Procedure();
		String result = proc.execute();
		System.out.println(result);
	}
}

class Procedure {

	private int size;
	private String calcuratedLog;

	/**
	 * @return 計算結果
	 */
	public String execute() {
		inputSize();
		calcurate();
		return calcuratedLog;
	}

	private void inputSize() {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int size = 1;

		System.out.println("input size of mass...");
		try {
			size = sc.nextInt();
		}
		catch(InputMismatchException ime) {
			System.out.println("your input is illegal\r\nplease try again");
			inputSize();
			return;
		}

		if(size < 1) {
			System.out.println("please try again...");
			inputSize();
			return;
		}

		this.size = size;
	}

	private void calcurate() {

		StringBuilder sb = new StringBuilder();
		int ketasuu = String.valueOf(size*size).length();
		String format = ("%0" + ketasuu + "d") + " ";

		IntStream.rangeClosed(1, size).forEach(first -> {
			IntStream.rangeClosed(1, size)
						.mapToObj(second -> String.format(format, first*second))
						.forEach(sb::append);
			sb.append("\r\n");
		});

		calcuratedLog = sb.toString();
	}
}
