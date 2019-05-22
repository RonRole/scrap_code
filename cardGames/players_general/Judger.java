package players_general;

import java.util.*;

import card.*;
import interfaces.*;

public abstract class Judger implements JudgerInterface, GameMasterListener{

	//判定する手札です。
	//protected List<Card> handToJudge;

	//インスタンスです
	protected static JudgerInterface judger;


	@Override
	public abstract JudgeResult judge(List<Card> hand);

	@Override
	public abstract List<PlayerInterface> getRankedPlayers(List<PlayerInterface> players);

	@Override
	public void update(EndSubject subject) {
		initialize();
	}

	protected void initialize() {
		judger = null;
	}

	public void threadSleep(long timemills) {
		try {
			Thread.sleep(timemills);
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
