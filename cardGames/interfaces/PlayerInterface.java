package interfaces;

import java.util.*;

import card.*;

/*
 * プレイヤーを表す抽象クラス
 * インターフェースに変えてみた
 */
public interface  PlayerInterface {

	abstract void selectMove();
	abstract void putCard(Card card);
	abstract void drawCard();
	abstract void showCard();
	abstract String getName();
	abstract void sortCard();

	abstract List<Card> giveHand();


}
