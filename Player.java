package cat;

import java.util.*;

public class Player {
	private int boxes;
	private int pointed;
	private int hands;
	List<Integer> pointedLog = new ArrayList<>();

	public Player(int boxes, int hands) {
		this.boxes = boxes;
		this.hands = hands;
	}

	public void point() {
		@SuppressWarnings("resource")

		Scanner sc = new Scanner(System.in);

		int position;

		System.out.println("select number...");

		try {
			position = sc.nextInt();
		}
		catch(InputMismatchException ime) {
			position = -99;
		}
		//sc.close();


		if(position <= 0 || position > boxes) {
			System.out.println("数字が間違っています");
			point();
			return;
		}
		pointed = position;
		hands--;
		pointedLog.add(position);
	}

	public int pointedPosition() {
		return pointed;
	}

	public String getPointedLog() {
		StringBuilder sb = new StringBuilder();

		pointedLog.stream()
					.limit(pointedLog.size()-1)
					.map(pointed -> pointed + "->")
					.forEach(sb::append);

		sb.append(pointed);
		return sb.toString();
	}

	public int getLeftHands() {
		return hands;
	}

	public int getHands() {
		return pointedLog.size();
	}


}
