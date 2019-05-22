package players_blackJack;

import players_general.*;

class BlackJackCom extends Player{

	private final static BlackJackHandSumCalculator calc = new BlackJackHandSumCalculator();

	public BlackJackCom(String name) {
		super(name);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void showCard() {
		System.out.println(name + "の手札:" + calc.handResult(hand));
		hand.forEach(System.out::print);
		System.out.println("");
	}

	@Override
	public void selectMove() {

		boolean gameEnd = false;
		// TODO 自動生成されたメソッド・スタブ
		do {
			if(calc.calcHandSum(hand) < 16) {
				super.drawCard();
			}
			else {
				gameEnd = true;
			}
		}
		while(!gameEnd);
	}

}
