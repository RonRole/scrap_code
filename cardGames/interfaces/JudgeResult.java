package interfaces;

import java.util.*;

import card.*;

/*
 * 勝負の判定結果を表すクラス
 */
public abstract class JudgeResult {
	
	public abstract String getResult();
	public abstract void setResult(List<Card> hand);
}
