package players_blackJack;

import java.util.stream.*;

import players_general.*;

class BlackJackDealer extends Player{

	private int moveCount;
	private static BlackJackDealer dealer;
	private final static BlackJackHandSumCalculator calc = new BlackJackHandSumCalculator();

	private BlackJackDealer(String name) {
		super(name);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static BlackJackDealer getBlackJackDealer() {
		if(dealer == null) {
			dealer = new BlackJackDealer("Dealer");
		}
		return dealer;
	}

	@Override
	public void selectMove() {
		// TODO 自動生成されたメソッド・スタブ
		if(moveCount == 0) {
			showHand_hide();
			moveCount++;
		}
		else if(moveCount > 0) {
			System.out.println(name + "の手札を公開します\r\n");
			threadSleep(500);
			while(calc.calcHandSum(hand) < 16) {
				showCard();
				threadSleep(500);
				drawCard();
				System.out.println("");
			}
			threadSleep(500);
			showCard();
			System.out.println("");
		}
	}

	private void showHand_hide() {
		System.out.println(name + "の手札");
		IntStream.range(0, hand.size())
					.forEach(i -> System.out.print(i == 0 ? "[■]" : hand.get(i).toString()));
		System.out.println("");
	}

	@Override
	public void showCard() {
		System.out.println(name + "の手札:" + calc.calcHandSum(hand));
		hand.forEach(System.out::print);
		System.out.println("");
	}

	@Override
	public void initialize() {
		super.initialize();
		moveCount = 0;
		dealer = null;
	}

}
