package cat;

import java.util.stream.*;

public class Result {
	private int boxes;
	private int playerPosition;
	private int catPosition;

	public Result(int boxes,int player, int cat) {
		this.boxes = boxes;
		playerPosition = player;
		catPosition = cat;
	}

	public void setResult(int player, int cat) {
		playerPosition = player;
		catPosition = cat;
	}

	public boolean get_if_cat_found() {

		if(playerPosition == catPosition) {
			System.out.println("HIT!!");
			return true;
		}
		System.out.println("Missed...");

		return false;
	}

	public String boxesResult() {

		StringBuilder sb = new StringBuilder();

		IntStream.rangeClosed(1,boxes)
					.mapToObj(i -> i == catPosition ? "|cat|" + " "
													: "|" + i + "|" + " ")
					.forEach(sb::append);

		return sb.toString();
	}
}
