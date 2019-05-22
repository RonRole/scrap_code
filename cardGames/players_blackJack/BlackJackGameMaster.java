package players_blackJack;

import java.util.*;
import java.util.stream.*;

import card.*;
import interfaces.*;
import players_general.*;

public class BlackJackGameMaster extends GameMaster{
	public static GameMasterInterface getBlackJackGameMaster() {
		if(gameMaster == null) {
			gameMaster = new BlackJackGameMaster();
		}
		return gameMaster;
	}

	private BlackJackGameMaster() {
		judger = BlackJackJudger.getJudger();
		you = BlackJackYou.getYou("You");
		playerFirstHandSize = 2;
		playerNums = 3;
	}



	@Override
	public void startGame() {
		CardList.getCardList()
					.controler()
					.getCards()
					.stream()
					.filter(card -> card.getNumber() > 10)
					.forEach(card -> card.setTempNumber(10));
		CardList.getCardList()
					.controler()
					.getCards()
					.stream()
					.filter(card -> card.getNumber() == Card.ACE)
					.forEach(card -> card.setTempNumber(11));
		super.startGame();
	}

	@Override
	protected void addPlayer() {
		// TODO 自動生成されたメソッド・スタブ
		players.add(BlackJackDealer.getBlackJackDealer());
		players.add(you);
		IntStream.iterate(1, i -> i+1).limit(playerNums).forEach(i -> players.add(new BlackJackCom("COM"+i)));
	}

	@Override
	public void play() {
		players.forEach(player -> player.selectMove());
		players.stream()
				.filter(player -> player instanceof BlackJackDealer)
				.forEach(player -> player.selectMove());
	}

	@Override
	public void judge() {

		System.out.println("***判定***");
		players.forEach(player -> {
			player.showCard();
			System.out.println("");
		});
			List<PlayerInterface> winners = judger.getRankedPlayers(players);
			winners.forEach(winner -> System.out.println(winner.getName() + "の勝ち!"));
			System.out.print("\r\n");
	}
}
