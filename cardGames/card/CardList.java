package card;

import java.util.*;
import java.util.stream.*;

import interfaces.*;
import iterators.*;

/*
 * 山札など、カードの集合を表すクラスです
 */
public class CardList implements CardsAggregate, CardListControled, GameMasterListener{
	private static List<Card> cards;

	//シングルトン
	private static CardList cardList;

	//インスタンス取得
	public static CardList getCardList() {
		if(cardList == null) {
			cardList = new CardList();
			cards = new ArrayList<>();
			IntStream.rangeClosed(0, 3).forEach(i -> {
				IntStream.rangeClosed(1, 13).forEach(j -> cards.add(new Card(i,j)));
			});
		}
		return cardList;
	}

	/*

	//カードを追加
	public void addCard(Card card) {
		cards.add(card);
	}

	//カードを取得
	public Card getCard() {
		if(cards.size() == 0) {
			//System.out.println("山札が空です");
			return null;
		}
		Card tempCard = cards.get(0);
		cards.remove(0);
		return tempCard;
	}

	//カードの集合を取得
	public List<Card> getCards(){
		return cards;
	}

	//シャッフル
	public void shuffle() {
		Collections.shuffle(cards);
	}
	//*/

	//イテレータ
	public CardListIterator iterator() {
		return new CardListIterator(cards);
	}

	@Override
	public CardListControler controler() {
		// TODO 自動生成されたメソッド・スタブ
		return new CardListControler(cards);
	}

	@Override
	public void update(EndSubject subject) {
		// TODO 自動生成されたメソッド・スタブ
		initialize();
	}

	//初期化
	protected void initialize() {
		cards = null;
		cardList = null;
	}

}
