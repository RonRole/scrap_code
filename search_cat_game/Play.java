package cat;

import java.util.*;
import java.util.stream.*;

public class Play {
	final int boxesInput;
	final int firstHands;
	int boxes;
	Player player;
	int hands;
	Cat cat;
	Result result;

	public Play(int boxes, int hands) {
		boxesInput = boxes;
		firstHands = hands;
		this.boxes = boxes;
		this.hands = hands;
		player = createPlayer();
		cat = createCat();
	}

	public void play() {
		showBoxes();
		concretePlay();
		showEndMessage();
		if(checkContinue()) {
			player = createPlayer();
			cat = createCat();
			play();
			return;
		}
		System.out.println("end");
	}

	private Player createPlayer() {
		return new Player(boxesInput,firstHands);
	}

	private Cat createCat() {
		return new Cat(boxesInput);
	}

	private void showBoxes() {
		IntStream.rangeClosed(1, boxes)
			.mapToObj(num -> String.format("|%d|", num) + " ")
			.forEach(System.out::print);
			System.out.println("");
	}

	private void concretePlay() {
		System.out.println("left hands:" + player.getLeftHands());
		player.point();
		result = new Result(boxes,player.pointedPosition(),cat.catsPosition());

		//猫を見つける
		if(result.get_if_cat_found()) {
			return;
		}


		if(player.getLeftHands() == 0) {
			System.out.println("GameOver!!!");
			return;
		}
		cat.move();
		concretePlay();
	}

	private void showEndMessage() {
		System.out.println(result.boxesResult());
		System.out.println("cat moved:" + cat.getLog());
		System.out.println("left hands:" + player.getLeftHands());
	}

	private boolean checkContinue() {
		System.out.println("\r\ncontinue?\r\n[0]:yes [1]:no");
		return selectContinue();
	}

	private boolean selectContinue() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int selected = -1;
		try {
			selected = sc.nextInt();
		}
		catch(InputMismatchException ime) {
			System.out.println("please try again...");
			return checkContinue();
		}

		if(selected == 0) {
			return true;
		}
		if(selected == 1) {
			return false;
		}

		System.out.println("please try again...");
		return selectContinue();
	}
}
