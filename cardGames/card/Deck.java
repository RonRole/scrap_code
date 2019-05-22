package card;

import java.util.*;

import interfaces.*;
import iterators.*;

public class Deck extends CardList{
	private static List<Card> deckCards;

	//シングルトン
	private static Deck deck;

	//インスタンス取得
	public static Deck returnDeck() {
		if(deck == null) {
			deck = new Deck();
			deckCards = new ArrayList<Card>();
		}
		return deck;
	}

	/*
	//オーバーライド
	@Override
	public void addCard(Card card) {
		deckCards.add(card);
	}

	@Override
	public Card getCard() {
		if(deckCards.size() == 0) {
			System.out.println("山札が空です");
			return null;
		}
		Card tempCard = deckCards.get(0);
		deckCards.remove(0);
		return tempCard;
	}

	@Override
	//シャッフル
	public void shuffle() {
		Collections.shuffle(deckCards);
	}
	//*/

	@Override
	public CardListIterator iterator() {
		// TODO 自動生成されたメソッド・スタブ
		return new CardListIterator(deckCards);
	}

	@Override
	public CardListControler controler() {
		// TODO 自動生成されたメソッド・スタブ
		return new CardListControler(deckCards);
	}

	@Override
	public void update(EndSubject subject) {
		initialize();
		deck = null;
		deckCards = null;
	}
}
