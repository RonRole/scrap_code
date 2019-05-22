package players_blackJack;

import java.util.*;
import java.util.stream.*;

import card.*;
import interfaces.*;
import players_general.*;

class BlackJackJudger extends Judger {

	public static JudgerInterface getJudger() {
		if(judger == null) {
			judger = new BlackJackJudger();
		}
		return judger;
	}

	@Override
	public JudgeResult judge(List<Card> hand) {
		// TODO 自動生成されたメソッド・スタブ
		return new BlackJackJudgedResult(hand);
	}

	@Override
	public List<PlayerInterface> getRankedPlayers(List<PlayerInterface> players) {

		PlayerInterface dealer = players.stream()
									.filter(player -> player instanceof BlackJackDealer)
									.collect(Collectors.toList())
									.get(0);

		int dealerHandNum = new BlackJackJudgedResult(dealer.giveHand()).getHandNumForSort();

		List<PlayerInterface> winners =
				players.stream().filter(player ->
									new BlackJackJudgedResult(player.giveHand()).getHandNumForSort() >
									dealerHandNum)
				.collect(Collectors.toList());

		if(winners.size() == 0) {
			winners = new ArrayList<>();
			winners.add(dealer);
		}
		return winners;
	}
}

class BlackJackJudgedResult extends JudgeResult {

	private static BlackJackHandSumCalculator calc = new BlackJackHandSumCalculator();
	private int handNum;
	private String result;

	BlackJackJudgedResult(List<Card> hand) {
		handNum =calc.calcHandSum(hand);
		setResult(hand);
	}

	public int getHandNum() {
		return handNum;
	}

	public int getHandNumForSort() {
		if(result == "BlackJack") {
			return 22;
		}
		if(handNum > 21) {
			return -1;
		}
		return handNum;
	}

	public String getResult() {
		return result;
	}

	public void setResult(List<Card> hand) {
		if(handNum == 21 && hand.size() == 2) {
			result = "BlackJack";
		}
		else if(handNum > 21) {
			result = "Busted!";
		}
		else {
			result = String.valueOf(handNum);
		}

	}

}
