package main;

import java.util.*;

public class Main6 {
	public static void main(String[] args) {
		int goal_total = 12;
		int[] numbers = new int[] {0,2,3,5,8,9};


		/**
		 *   0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24
		 * 0 o x x x x x x x x x  x  x  x  x  x  x  x  x  x  x  x  x  x  x  x
		 * 7 o x x x x x x o x x  x  x  x  x  x  x  x  x  x  x  x  x  x  x  x
 		 * 5 o x x x x o x o x x  x  x  o  x  x  x  x  x  x  x  x  x  x  x  x
		 * 3 o x x o x o x o o x  o  x  o  x  x  o  x  x  x  x  x  x  x  x  x
		 * 1 o o x o o o o o o o  o  o  o  o  x  o  o  x  x  x  x  x  x  x  x
		 * 8 o o x o o o o o o o  o  o  o  o  o  o  o  o  o  o  o  o  x  o  o
		 */
		/*以下ロジック*/
		int possibly_total = 0;

		for(int num : numbers) {
			possibly_total += num;
		}

		int[][] dp_field = new int[numbers.length][possibly_total+1];
		dp_field[0][0] = 1;//初期化

		for(int num_i = 1; num_i < dp_field.length; num_i++) {
			for(int p_total = 0; p_total <= possibly_total; p_total++) {
				//p_totalが現在の参照number未満の時
				if(p_total < numbers[num_i]) {
					dp_field[num_i][p_total] = dp_field[num_i-1][p_total];
					continue;
				}

				//p_totalが現在の参照number以上の時
				if(p_total >= numbers[num_i]) {
					//現在の数字を使ってp_totalを構成できるとき
					if(dp_field[num_i-1][p_total - numbers[num_i]]>0) {
						dp_field[num_i][p_total] = dp_field[num_i-1][p_total]+1;
					}
					//無理な時
					else {
						dp_field[num_i][p_total] = dp_field[num_i-1][p_total];
					}
				}
			}
		}

		for(int i = 0; i < dp_field.length; i++) {
			for(int j = 0; j < dp_field[i].length; j++) {
				System.out.print(dp_field[i][j] + " ");
			}
			System.out.println("");
		}

		Scanner sc = new Scanner(System.in);

		int totalOf = sc.nextInt();

		System.out.println(dp_field[dp_field.length-1][totalOf]);
		sc.close();
	}
}
