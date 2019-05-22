package players_general;

import java.util.*;

import card.*;
import interfaces.*;

public abstract class Player implements PlayerInterface, GameMasterListener {
	protected String name;
	protected List<Card> hand;

	public Player(String name) {
		this.name = name;
		hand = new ArrayList<>();
	}
	@Override
	public void drawCard() {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println(name + "がカードを引きます");
		hand.add(Deck.returnDeck().controler().getCard());
	}

	@Override
	public void showCard() {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println(name + "の手札");
		hand.forEach(System.out::print);
		System.out.println("");

	}

	@Override
	public abstract void selectMove();

	@Override
	public void putCard(Card card) {
		hand.remove(card);
		Cemetary.getCemetary().controler().addCard(card);
	}

	@Override
	public List<Card> giveHand() {
		return hand;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void sortCard() {
		hand.sort(Comparator.comparing(Card::getNumber)
					.thenComparing(Comparator.comparing(Card::getMark)));
	}

	@Override
	public void update(EndSubject subject) {
		// TODO 自動生成されたメソッド・スタブ
		initialize();
	}

	protected void initialize() {
		name = null;
		hand = null;
	}

	public void threadSleep(long timemills) {
		try {
			Thread.sleep(timemills);
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}



}
