package card;

public class Card {
	private int mark;//{0,1,2,3}
	private int number;//{1~13}
	private int cardStrength;//ゲーム毎にカードに異なる値を持たせる(例:ブラックジャックの絵札)

	public static final int SPADE = 3;
	public static final int HEART = 2;
	public static final int DIAMOND = 1;
	public static final int CLUB = 0;
	public static final int JOKER_MARK = -1;

	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;
	public static final int ACE = 1;
	public static final int JOKER_NUMBER = -1;

	//コンストラクタ
	public Card(int mark, int number) {
		if(mark == JOKER_MARK && number == JOKER_NUMBER) {
			System.out.println("Jokerが加わります");
		}
		else if(mark < 0 || mark > 3) {
			System.out.println("markが無効です");
			return;
		}
		else if(number < 1 || number > 13) {
			System.out.println("numberが無効です");
			return;
		}
		this.mark = mark;
		this.number = number;
		this.cardStrength = number;
	}

	//カードの情報を取得
	public String toString() {
		String markInfo = mark == SPADE ? "♠"
						: mark == HEART ? "♥"
						: mark == DIAMOND ? "♦"
						: mark == CLUB ? "♣"
						: mark == JOKER_MARK ? ""
						: "markにエラーがあります";

		String numberInfo = number == JACK ? "J"
							: number == QUEEN ? "Q"
							: number == KING ? "K"
							: number == ACE ? "A"
							: number == JOKER_NUMBER ? "JOKER"
							: String.valueOf(number);

		return String.format("[%s%s]", markInfo, numberInfo);
	}

	//マークを取得
	public int getMark() {
		return mark;
	}

	//番号を取得
	public int getNumber() {
		return cardStrength;
	}

	/*
	 * !使用注意
	 * 番号を設定
	 */
	public void setTempNumber(int cardStrength) {
		this.cardStrength = cardStrength;
	}

	//
}

