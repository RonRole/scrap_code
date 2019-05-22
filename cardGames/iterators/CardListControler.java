package iterators;

import java.util.*;

import card.*;

public class CardListControler {

	private List<Card> cards;

	public CardListControler(List<Card> cards) {
		this.cards = cards;
	}

	/*カードリストの操作*/
	//カードを追加
	public void addCard(Card card) {
		cards.add(card);
	}

	//ジョーカーを追加
	public void addJoker() {
		cards.add(new Card(Card.JOKER_MARK,Card.JOKER_NUMBER));
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


}
