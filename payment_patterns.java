package main;

import java.util.*;

public class Exchange {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		/**
		 * 問題設定
		 * たくさんの硬貨
		 * 入力金額の支払い方法のパターン数
		 */
		int shiharai = sc.nextInt();
		int[] coins = new int[] {1,100,500};
		StringBuilder dp_panel = new StringBuilder();
		StringBuilder maisuu_result = new StringBuilder();
		sc.close();

		/*
		 * 例:5円
		 * 枚数\金額:0,1,2,3,4,5
		 *        0+t|f|f|f|f|f|
		 *        1|f|t|f|f|f|t|
		 *        2|f|f|t|f|f|f|
		 *        3|f|f|f|t|f|f|
		 *        4|f|f|f|f|t|f|
		 *        5|f|f|f|f|f|t|
		 *
		 * dp[m][n]について、dp[m-1][*]に[n-1][n-5][n-10][n-50][n-100][n-500]のいずれかがあればtrue
		 * 最終的にdp[*][n] = trueであるものを数える
		 */
		 
		 //最小のコインで割り切れない場合、支払えない
		 if(shiharai%coins[0] != 0){
		     System.out.println("支払えません");
		     return;
		 }
		//支払方法のパターン
		int sizeOfDP = shiharai/coins[0]+1;
		boolean dp[][] = new boolean[sizeOfDP][sizeOfDP];//[枚数][金額]

		//初期化
		dp[0][0] = true;
		
		/*
		for(int i = 0; i < sizeOfDP; i++) {
			dp_panel.append(i*coins[0]+"|");
		}
		dp_panel.append("\r\n");
		//*/

		int pattern = 0;

		for(int maisuu = 0; maisuu < sizeOfDP; maisuu++) {
			for(int kingaku = 0; kingaku < sizeOfDP; kingaku++) {
				for(int coin : coins) {
					if(dp[maisuu][kingaku]&& (kingaku*coins[0])+coin <= shiharai) {
						dp[maisuu+1][kingaku+coin/coins[0]] = true;
					}
				}
			}

			if(dp[maisuu][sizeOfDP-1]) {
				pattern++;
				maisuu_result.append("枚数:" + maisuu);
				maisuu_result.append("\r\n");
			}
		}

		System.out.println("支払方法:" + pattern + "通り");
		System.out.println(maisuu_result);
		
		//*
		for(boolean[] dp_outer : dp) {
			for(boolean dp_inner : dp_outer) {
				dp_panel.append(dp_inner ? "O|" : " |");
			}
			dp_panel.append("\r\n");
		}
		//*/

		System.out.println(dp_panel);
	}
}
