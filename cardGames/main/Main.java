package main;

import interfaces.*;
import players_blackJack.*;
import players_poker.*;

//ゲーム全体の制御を行います
public class Main {
	public static void main(String args[]) {

		do {
			GameMasterInterface gmInterface = null;



			System.out.println("ゲームを選択してください\r\n" +
									"[0]:ポーカー\r\n" +
									"[1]:ブラックジャック");

			int selectedGame = InputUtility.getUtility().inputInt(0,1);

			switch(selectedGame) {
			case 0:
				gmInterface = PokerGameMaster.getPokerGameMaster();
				break;
			case 1:
				gmInterface = BlackJackGameMaster.getBlackJackGameMaster();
				break;
			}

			//初期状態
			gmInterface.startGame();
			//ゲーム進行
			gmInterface.play();
			//判定
			gmInterface.judge();
			//ゲーム終了
			gmInterface.endGame();
		}
		while(true);
	}
}
