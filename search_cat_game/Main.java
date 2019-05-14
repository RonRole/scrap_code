package cat;

/**
 * @author ronrole
 * 量子猫のクイズ
 * 最初の箱のどこかに猫が入る
 * ->毎回1つとなりに移動する
 * 猫を見つければ当たり
 */

public class Main {

	public static void main(String args[]) {

		//箱の数
		int boxes = 5;

		//手数
		int hands = Math.max((boxes-2)*2,boxes);

		//スタートメッセージ
		System.out.println(String.format("%d回以内に猫を見つけよう", hands));
		Play play = new Play(boxes, hands);

		play.play();
	}
}
