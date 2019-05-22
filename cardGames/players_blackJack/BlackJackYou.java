package players_blackJack;

import main.*;
import players_general.*;

class BlackJackYou extends Player{

	private static BlackJackYou you;
	private final static BlackJackHandSumCalculator calc = new BlackJackHandSumCalculator();

	private BlackJackYou(String name) {
		super(name);
	}

	public static BlackJackYou getYou(String name) {
		if(you == null) {
			you = new BlackJackYou(name);
		}
		return you;
	}

	public static BlackJackYou getYou() {
		if(you == null) {
			System.out.println("class You is null");
		}
		return you;
	}

	@Override
	public void showCard() {
		System.out.println(name + "の手札:" + calc.handResult(hand));
		hand.forEach(System.out::print);
		System.out.println("");
	}

	@Override
	public void selectMove() {
		// TODO 自動生成されたメソッド・スタブ
		blackJackMoveSelect();
	}

	private void blackJackMoveSelect() {

		boolean gameEnd = false;

		do {
			System.out.println("***手札***");
			showCard();

			System.out.println("SUM:" + hand.stream().mapToInt(card -> card.getNumber()).sum() + "\r\n" +
								"0:HIT\r\n" +
								"1:STAY");

			int selected = InputUtility.getUtility().inputInt(0,1);
			switch(selected) {
			case 0:
				drawCard();
				break;
			case 1:
				break;
			}

			gameEnd = selected == 1 || handIsBusted();
		}
		while(!gameEnd);
	}

	private boolean handIsBusted() {
		int handNum = calc.calcHandSum(hand);
		if(handNum > 21) {
			System.out.println("***手札***");
			showCard();
			System.out.println("Busted!");
			return true;
		}
		return false;
	}

	@Override
	protected void initialize() {
		you = null;
	}

}
