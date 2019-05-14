package main;

public class WaitThread extends Thread {

	private boolean isActive = true;

	public void stopThread() {
		isActive = false;
	}

	public void reStartThread() {
		isActive = true;
	}

	@Override
	public void run() {
		while(isActive) {
			System.out.println("[WaitThread]:please wait...");
			try {
				sleep(2000);
			}
			catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}
}
