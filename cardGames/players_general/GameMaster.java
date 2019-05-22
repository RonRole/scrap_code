package players_general;

import java.util.*;
import java.util.stream.*;

import card.*;
import interfaces.*;
import main.*;

public abstract class GameMaster extends EndSubject implements GameMasterInterface {

	/*使用するインスタンス*/
	//protected CardList cardList = CardList.getCardList();
	//protected Deck deck = Deck.returnDeck();
	//protected Cemetary cemetary = Cemetary.getCemetary();

	//コントローラーの生成
	//protected CardListControler cardListControler = cardList.controler();
	//protected CardListControler deckControler = deck.controler();
	//protected CardListControler cemetaryControler = cemetary.controler();

	protected PlayerInterface you;
	protected List<PlayerInterface> players;
	protected JudgerInterface judger;


	protected int playerFirstHandSize;
	protected int playerNums;

	protected static GameMasterInterface gameMaster;

	private List<GameMasterListener> listeners;


	//*ゲームの初期状態を作ります
	public void startGame() {
		//山札の作成
		//cardListControler.getCards().forEach(deckControler::addCard);
		CardList.getCardList()
					.controler()
					.getCards()
					.forEach(card -> Deck.returnDeck()
											.controler()
											.addCard(card));

		//山札のシャッフル
		//deckControler.shuffle();

		Deck.returnDeck().controler().shuffle();

		/*
		 * プレイヤーの作成
		 * You + playerNums
		 */
		players = new ArrayList<>();
		addPlayer();

		//プレイヤーにカードを引かせる
		tellPlayersToDrawCard();

		//リスナー追加
		listeners = new ArrayList<>();
		addListener(CardList.getCardList());
		addListener(Deck.returnDeck());
		addListener(Cemetary.getCemetary());
		addListener((GameMasterListener) judger);
		players.forEach(player -> addListener((GameMasterListener) player));
	}

	//プレイヤーにカードを引かせます
	protected void tellPlayersToDrawCard() {
		//プレイヤーそれぞれにplayerHandSizeぶんカードを引かせる
		players.forEach(player -> {
			IntStream.range(0, playerFirstHandSize).forEach(i -> player.drawCard());
		});
	}

	//プレイヤーリストにプレイヤーを追加します
	protected abstract void addPlayer();

	//ゲームの進行を行います
	public void play() {
		//you.selectMove();
		//you.showCard();
		players.forEach(i -> {
			i.selectMove();
		});
	}

	public abstract void judge();

	//ゲーム終了処理
	public void endGame() {
		//resetGame();
		continueSelect();
		notifyListeners();
		gameMaster = null;
	}

	//続行確認
	private void continueSelect() {
		System.out.println("続行しますか?\r\n" + "[0]:はい [1]:いいえ");
		int input = InputUtility.getUtility().inputInt(0,1);

		if(input == 1) {
			System.out.println("終了します");
			System.exit(0);
		}
	}

	public void addListener(GameMasterListener listener) {
		listeners.add(listener);
	}

	public void notifyListeners() {
		listeners.stream().forEach(i -> i.update(this));
	}

	protected void threadSleep(long timemills) {
		try {
			Thread.sleep(timemills);
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
