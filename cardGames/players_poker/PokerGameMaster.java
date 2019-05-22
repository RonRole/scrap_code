package players_poker;

import java.util.stream.*;

import card.*;
import interfaces.*;
import players_general.*;

/*
 * ゲームの進行をつかさどるクラス
 */
public class PokerGameMaster extends GameMaster{

	public static GameMasterInterface getPokerGameMaster() {
		if(gameMaster == null) {
			gameMaster = new PokerGameMaster();
		}
		return gameMaster;
	}

	//インスタンス
	private PokerGameMaster() {
		judger = PokerJudger.getJudger();
		playerFirstHandSize = 5; //プレイヤーの手札枚数
		playerNums = 3;		//プレイヤー人数(You以外)
		you = PokerYou.getYou("You");
		CardList.getCardList().controler().addJoker();//ジョーカー追加
		CardList.getCardList().controler().shuffle();
	}

	@Override
	public void judge() {
		judgeFase();
	}

	//ゲーム終了処理
	@Override
	public void endGame() {
		super.endGame();
	}




	//判定フェイズ
	private void judgeFase() {
		players.forEach(i -> {
			i.showCard();
			System.out.println(((PokerJudgeResult) judger.judge(i.giveHand())).getResult());
			System.out.println("");
		});

		PlayerInterface winner = judger.getRankedPlayers(players).get(players.size()-1);
		System.out.println(winner.getName() + "の勝ち!\r\n");

	}

	@Override
	protected void addPlayer() {
		// TODO 自動生成されたメソッド・スタブ
		players.add(you);
		IntStream.iterate(1, i -> i+1).limit(playerNums).forEach(i -> players.add(new PokerCom("COM"+i)));

	}
}
