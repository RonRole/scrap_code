package main;

import java.util.*;
import java.util.stream.*;

public class Main {
	/*
	 * 概要
	 * size:フィールドの大きさ(正方形)
	 * ["_","/","\"]これらの文字を組み合わせて正方形を埋める
	 * 光の反射の行く末
	 */
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		int size = 4;
		Field field = new Field(size);

		IntStream.range(0, size).forEach(num -> field.setField(num, sc.nextLine()));

		Light sample = new Light();

		sample.setField(field.getField());
		sample.move(0, 0, 1);

		//*
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				System.out.print(field.getField()[i][j]);
			}
			System.out.println("");
		}
		//*/
	}
}

class Light {
	private int direction;//up:0 right:1 down:2 left:3
	private String[][] field;

	public int getDirection() {
		return this.direction;
	}
	public void changeDirection(int direction) {
		this.direction = direction;
	}

	public void setField(String[][] field) {
		this.field = field;
	}

	public void move(int row, int col, int direction) {
		if(row == field.length) {
			field[row-1][col] = "↓";
			return;
		}
		if(col == field.length) {
			field[row][col-1] = "→";
			return;
		}
		if(row < 0) {
			field[row+1][col] = "↑";
			return;
		}
		if(col < 0) {
			field[row][col+1] = "←";
			return;
		}

		this.direction = changeDirection(row,col,direction);

		if(this.direction == 0) {
			move(row-1,col,this.direction);
			return;
		}
		if(this.direction == 1) {
			move(row,col+1,this.direction);
			return;
		}
		if(this.direction == 2) {
			move(row+1,col,this.direction);
			return;
		}
		if(this.direction == 3) {
			move(row,col-1,this.direction);
			return;
		}
	}

	private int changeDirection(int row, int col, int direction) {
		if(field[row][col].equals("_")) {
			return direction;
		}
		if(field[row][col].equals("\\")) {
			if(direction == 0) {
				return 3;
			}
			if(direction == 1) {
				return 2;
			}
			if(direction == 2) {
				return 1;
			}
			if(direction == 3) {
				return 0;
			}
		}
		if(field[row][col].equals("/")) {
			if(direction == 0) {
				return 1;
			}
			if(direction == 1) {
				return 0;
			}
			if(direction == 2) {
				return 3;
			}
			if(direction == 3) {
				return 2;
			}
		}
		return -1;
	}

}

class Field {
	private final String[][] field;
	public Field(int size) {
		field = new String[size][size];
	}

	public void setField(int row, String line) {
		field[row] = line.split("");

	}
	public String[][] getField(){
		return field;
	}
}
