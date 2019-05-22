package main;

import java.util.*;

/*
 * 入力のユーティリティクラス
 * シングルトン
 */
public class InputUtility {

	private static InputUtility inputUtility = null;

	public static InputUtility getUtility() {
		if(inputUtility == null) {
			inputUtility = new InputUtility();
		}
		return inputUtility;
	}

	public int inputInt() {
		int input = 0;

		while(true) {
			Scanner sc = new Scanner(System.in);
			try {
				input = sc.nextInt();
				break;
			}
			catch(InputMismatchException missInput) {
				System.out.println("入力が間違っています");
			}
		}
		return input;
	}


	public static void main(String args[]) {

		String[] strings = new String[]{"aa","bb","cc"};
		System.out.println(Arrays.asList(strings).contains("bb"));
	}
	//*
	public int inputInt(Integer... nums) {
		int input = -1;

		while(true) {
			Scanner sc = new Scanner(System.in);
			try {
				input = sc.nextInt();

				if(Arrays.asList(nums).contains(input)) {
					break;
				}
			}
			catch(InputMismatchException missInput) {
			}
			System.out.println("入力が間違っています");
		}
		return input;
	}
	//*/

}
