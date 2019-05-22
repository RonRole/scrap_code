package players_poker;

import java.util.*;
import java.util.stream.*;

import card.*;
import main.*;
import players_general.*;

class PokerYou extends Player{

	//シングルトン
	private static PokerYou you;
	//private static Card[] hand;

	private PokerYou(String name) {
		// TODO 自動生成されたコンストラクター・スタブ
		super(name);
	}

	public static PokerYou getYou(String name) {
		if(you == null) {
			you = new PokerYou(name);
		}
		return you;
	}

	public static PokerYou getYou() {
		if(you == null) {
			System.out.println("class You is null");
		}
		return you;
	}

	@Override
	public void selectMove() {
		pokerMoveSelect();
	}

	@Override
	public void sortCard() {
		pokerSortCard();
	}

	private void pokerMoveSelect() {
		System.out.println("[山札]:" + Deck.returnDeck().controler().getCards().size());

		System.out.println("***手札***");
		showCard();

		System.out.println("0:交換\r\n" +
							"1:勝負\r\n" +
							"2:ソート");

		int selected = InputUtility.getUtility().inputInt(0,1,2);

		switch(selected) {
		case 0:
			int firstHandLength = hand.size();
			pokerPutCard();
			IntStream.range(0, firstHandLength - hand.size())
						.forEach(i -> drawCard());
			sortCard();
			break;
		case 1:
			break;
		case 2:
			sortCard();
			selectMove();
			break;
		}
	}

	private void pokerPutCard() {
		Map<Card,String> checkedMap = new HashMap<Card,String>();
		hand.stream().forEach(card -> checkedMap.put(card, card.toString()));
		String check = "*";

		do {
			int ALL = hand.size();
			int ENTER = hand.size()+1;

			System.out.println("捨てるカードを選んでください");
			IntStream.range(0, hand.size()).forEach(i -> System.out.printf("%d:%s\r\n",i,checkedMap.get(hand.get(i))));
			System.out.println(ALL + ":全部");
			System.out.println(ENTER + ":決定");

			int input = InputUtility.getUtility().inputInt();

			if(input < ALL) {
				Card selected = hand.get(input);
				if(checkedMap.get(selected).contains(check)) {
					checkedMap.put(selected, selected.toString());
				}
				else {
					checkedMap.put(selected, selected.toString()+check);
				}
			}
			else if(input == ALL){
				if(checkedMap.values().stream().allMatch(value -> value.contains(check))) {
					hand.stream().forEach(card -> checkedMap.put(card, card.toString()));
				}
				else {
					checkedMap.keySet()
								.stream()
								.filter(key -> !checkedMap.get(key).contains(check))
								.forEach(key -> checkedMap.put(key, checkedMap.get(key)+check));
				}
			}
			else if(input == ENTER) {
				checkedMap.keySet().stream().filter(key -> checkedMap.get(key).contains(check)).forEach(key -> putCard(key));
				break;
			}

		}
		while(true);
	}

	private void pokerSortCard() {
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

	@Override
	protected void initialize() {
		you = null;
	}

}
