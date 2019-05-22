package players_poker;

import java.util.*;
import java.util.stream.*;

import card.*;
import players_general.*;

class PokerCom extends Player{

	public PokerCom(String name) {
		super(name);
	}

	@Override
	public void selectMove() {
		int handType = ((PokerJudgeResult) ((PokerJudger) PokerJudger.getJudger()).judgeHand(hand)).getHandNum();

		//ノーペアの時は全とっかえ
		if(handType == 0) {
			int firstHandLength = hand.size();
			putAllCard();
			IntStream.range(0, firstHandLength - hand.size())
						.forEach(i -> drawCard());
		}

		//5枚での組ができているときはそのまま
		else if(handType == PokerJudger.FLUSH
				|| handType == PokerJudger.FULL_HOUSE
				|| handType == PokerJudger.ROYAL_STRAIGHT_FLUSH
				|| handType == PokerJudger.STRAIGHT
				|| handType == PokerJudger.STRAIGHT_FLUSH
				) {
		}
		//ペア系の時は、ペアになっていないカードをとっかえ
		else if(handType == PokerJudger.FOUR_OF_A_KIND
					|| handType == PokerJudger.ONE_PAIR
					|| handType == PokerJudger.THREE_OF_A_KIND
					|| handType == PokerJudger.TWO_PAIR
					) {
			int firstHandLength = hand.size();

			Map<Object, List<Card>> groupedHand = hand.stream().collect(Collectors.groupingBy(Card::getNumber));
			groupedHand.values().stream().filter(value -> value.size() < 2).forEach(value -> {
				value.stream().forEach(card -> putCard(card));
			});

			IntStream.range(0, firstHandLength - hand.size())
			.forEach(i -> drawCard());
		}
		sortCard();
	}

	private void putAllCard(){
		List<Card> tempHand = new ArrayList<>();
		tempHand.addAll(hand);
		tempHand.stream().forEach(card -> putCard(card));
	}

	@Override
	public void sortCard() {
		//super.sortCard();
		List<Card> sort1 = hand.stream()
								.filter(i -> i.getNumber() != Card.ACE)
								.sorted(Comparator.comparing(Card::getNumber).thenComparing(Comparator.comparing(Card::getMark)))
								.collect(Collectors.toList());
		List<Card> sort2 = hand.stream()
								.filter(i -> i.getNumber() == Card.ACE)
								.sorted(Comparator.comparing(Card::getMark))
								.collect(Collectors.toList());
		hand.clear();
		hand.addAll(sort1);
		hand.addAll(sort2);
	}

}
