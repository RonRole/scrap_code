package interfaces;

public interface GameMasterInterface {
	public abstract void startGame(); //ゲームの初期状態作成
	public abstract void play();		 //ゲームの進行
	public abstract void judge();		 //勝負判定
	public abstract void endGame();   //ゲームの終了処理
}
