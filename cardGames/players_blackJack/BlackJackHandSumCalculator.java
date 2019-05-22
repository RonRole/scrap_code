package players_blackJack;

import java.util.*;

import card.*;

class BlackJackHandSumCalculator {
	final int calcHandSum(List<Card> hand) {
		int handNum = hand.stream().mapToInt(card -> card.getNumber()).sum();

		while(handNum > 21 && hand.stream().anyMatch(card -> card.getNumber() == 11)) {
			hand.stream().filter(card -> card.getNumber() == 11).limit(1).forEach(card -> card.setTempNumber(1));
			handNum = hand.stream().mapToInt(card -> card.getNumber()).sum();
		}

		return handNum;
	}

	final String handResult(List<Card> hand) {

		String result = "";
		int handNum = calcHandSum(hand);

		if(handNum == 21 && hand.size() == 2) {
			result = "BlackJack";
			return result;
		}
		if(handNum > 21) {
			result = "Busted!";
			return result;
		}

		return String.valueOf(handNum);
	}
}
