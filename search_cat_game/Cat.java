package cat;

import java.util.*;

public class Cat {
	private int boxes;
	private int position;

	private List<Integer> positionLog = new ArrayList<>();

	public Cat(int boxes) {
		this.boxes = boxes;
		position = (int) (Math.random()*boxes)+1;
		System.out.println("cat moves....");
		positionLog.add(position);
	}

	public void move() {
		System.out.println("cat moves....");
		if(position == 1) {
			position++;
			positionLog.add(position);
			return;
		}

		if(position == boxes) {
			position--;
			positionLog.add(position);
			return;
		}

		if((int)(Math.random()*10)<5) {
			position++;
			positionLog.add(position);
			return;
		}
		position--;
		positionLog.add(position);
		return;
	}

	public int catsPosition() {
		return position;
	}

	public String getLog() {
		StringBuilder sb = new StringBuilder();
		positionLog.stream()
					.limit(positionLog.size()-1)
					.map(loggedPosition -> loggedPosition + "->")
					.forEach(sb::append);
		sb.append(position);
		return sb.toString();
	}
}
