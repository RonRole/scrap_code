package iterators;

import java.util.*;

import card.*;

public class CardListIterator implements Iterator<Card>{

	private List<Card> cards;
	private int index;

	//コンストラクタ
	public CardListIterator(List<Card> cards) {
		this.cards = cards;
		this.index = 0;
	}
	@Override
	public boolean hasNext() {
		// TODO 自動生成されたメソッド・スタブ
		return index < cards.size();
	}

	@Override
	public Card next() {
		// TODO 自動生成されたメソッド・スタブ
		Card card = cards.get(index);
		index++;
		return card;
	}

	public CardListIterator iterator() {
		return new CardListIterator(cards);
	}

}
