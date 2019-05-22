package card;

import java.util.*;

import interfaces.*;
import iterators.*;

public class Cemetary extends CardList {
	private static List<Card> cemetaryCards;

	//シングルトン
	private static Cemetary cemetary;

	//インスタンス取得
	public static Cemetary getCemetary() {
		if(cemetary == null) {
			cemetary = new Cemetary();
			cemetaryCards = new ArrayList<>();
		}
		return cemetary;
	}

	/*
	//オーバーライド
	@Override
	public void addCard(Card card) {
		cemetaryCards.add(card);
	}

	//カードを取得
	@Override
	public Card getCard() {
		if(cemetaryCards.size() == 0) {
			//System.out.println("山札が空です");
			return null;
		}
		Card tempCard = cemetaryCards.get(0);
		cemetaryCards.remove(0);
		return tempCard;
	}

	//カードの集合を取得
	@Override
	public List<Card> getCards(){
		return cemetaryCards;
	}

	//シャッフル
	@Override
	public void shuffle() {
		Collections.shuffle(cemetaryCards);
	}

	//*/

	@Override
	public CardListIterator iterator() {
		// TODO 自動生成されたメソッド・スタブ
		return new CardListIterator(cemetaryCards);
	}

	@Override
	public CardListControler controler() {
		// TODO 自動生成されたメソッド・スタブ
		return new CardListControler(cemetaryCards);
	}

	@Override
	public void update(EndSubject subject) {
		initialize();
		cemetary = null;
		cemetaryCards = null;
	}

}
