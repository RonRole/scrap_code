package interfaces;

import java.util.*;

import card.*;

public interface JudgerInterface {
	/*
	 * 判定
	 * 戻り値:判定結果(オブジェクト)
	 */
	public abstract JudgeResult judge(List<Card> hand);


	/*
	 * 順位付け
	 * 戻り値:順位づけされたプレイヤーたち
	 */
	public abstract List<PlayerInterface> getRankedPlayers(List<PlayerInterface> players);
}
