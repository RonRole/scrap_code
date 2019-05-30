package main;

public class Main4 {
	public static void main(String args[]) {
		int[] input = {9,7};
		int total = 16;

		/*
		 * \total
		 * use
		 *
		 *   0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
		 *i0 o             o
		 *
		 *i1           o               o
		 *
		 *i2       o         o   o              o
		 */

		int available_max_total = 0;
		for(int i = 0; i < input.length; i++) {
			available_max_total += input[i];
		}

		boolean[][] dp_field = new boolean[input.length][available_max_total+1];
		dp_field[0][0] = true;

		for(int num = 0; num < dp_field.length; num++) {
			for(int t_total = 0; t_total < dp_field[num].length; t_total++) {
				if(num > 0 && t_total >= input[num]) {
					dp_field[num][t_total] = dp_field[num-1][t_total] || dp_field[num-1][t_total-input[num]];
					continue;
				}
				if(num > 0) {
					dp_field[num][t_total] = dp_field[num-1][t_total];
				}
				if(num == 0) {
					dp_field[num][t_total] = dp_field[num][t_total] || input[num] == t_total;
				}
			}
		}

		for(int i = 0; i < dp_field.length; i++) {
			for(int j = 0; j < dp_field[i].length; j++) {
				System.out.print(dp_field[i][j] ? "O" : "X");
			}
			System.out.println("");
		}

		for(int i = 0; i < dp_field.length; i++) {
			if(dp_field[i][total]) {
				System.out.println("YES");
				break;
			}
		}
		System.out.println("NO");
	}
}
