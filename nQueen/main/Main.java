package main;

import java.util.*;
import java.util.stream.*;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int fieldSize = sc.nextInt();
		sc.close();

		int[][] field = new int[fieldSize][fieldSize];

		Arrays.stream(field).forEach(inner -> {
			Arrays.stream(inner).map(i -> 0);
		});

		Queen queen = new Queen(field);
		queen.play(0, 0, 0, 0);

		if(queen.getResult().size() == 0) {
			System.out.println("むり");
		}

		queen.getResult().forEach(outerArray -> {
			Arrays.stream(outerArray).forEach(innerArray -> {
				Arrays.stream(innerArray).mapToObj(i -> i == -1 ? "Q" : ".").forEach(System.out::print);
				System.out.println("");
			});
			System.out.println("");
		});
	}
}

class Queen {

	int[][] field;
	int queenCount;
	List<int[][]> result = new ArrayList<>();


	public Queen(int[][] field) {
		this.field = field;
	}

	public void play(int startRow, int startColumn, int presetRow, int presetColumn) {
		//列、行がはみ出す
		if(startRow == field.length || startColumn == field.length) {
			if(queenCount == field.length) {

				int[][] clearBoard = new int[field.length][field.length];

				for(int i = 0; i < field.length; i++) {
					for(int j = 0; j < field.length; j++) {
						clearBoard[i][j] = field[i][j];
					}
				}

				result.add(clearBoard);

				setClean(presetRow, presetColumn);
				queenCount--;
				return;
			}
			setClean(presetRow, presetColumn);
			queenCount--;
			return;
		}

		//クイーンの置き場がない
		if(searchSetableCell(startRow, startColumn).size() == 0) {
			setClean(presetRow, presetColumn);
			queenCount--;
			return;
		}
		//*
		searchSetableCell(startRow, startColumn).forEach(cell -> {
			setQueen(cell[0], cell[1]);
			setEnable(cell[0], cell[1]);
			play(cell[0], startColumn+1, cell[0], cell[1]);
			//return;
		});
		//*/
		setClean(presetRow, presetColumn);
		queenCount--;

	}


	public List<int[]> searchSetableCell(int row, int column) {

		List<int[]> result = new ArrayList<>();

		result = IntStream
			.range(0,field.length)
			.filter(i -> field[i][column] == 0)
			.mapToObj(i -> new int[]{i,column})
			.collect(Collectors.toList());

		return result;
	}

	public void setQueen(int row, int column) {
		queenCount++;
		field[row][column] = -1;
	}

	/*
	 * 引数：クイーンの配置位置
	 */
	public void setEnable(int row, int column) {

		//クイーン配置行を使用不可に
		//Arrays.stream(field[row]).map(cell -> cell == -1 ? cell*-1 : cell++);

		//クイーン配置列を使用不可に
		//Arrays.stream(field).map(tempRow -> tempRow[column] == -1 ? tempRow[column]*-1 : tempRow[column]++);

		//*

		for(int i = 0; i < field.length; i++) {
			if(i != column) {
				field[row][i] = field[row][i] < 0 ? field[row][i]*-1 : field[row][i]+1;
			}

			if(i != row) {
				field[i][column] = field[i][column] < 0 ? field[i][column]*-1 : field[i][column]+1;
			}

			if(row >= i && column >= i && i != 0) {
				field[row-i][column-i] = field[row-i][column-i] < 0 ? field[row-i][column-i]*-1 : field[row-i][column-i]+1;
			}

			if(row + i < field.length && column + i < field.length && i != 0) {
				field[row+i][column+i] = field[row+i][column+i] < 0 ? field[row+i][column+i] : field[row+i][column+i]+1;
			}

			if(row >= i && column + i < field.length && i != 0) {
				field[row-i][column+i] = field[row-i][column+i] < 0 ? field[row-i][column+i]*-1 : field[row-i][column+i]+1;
			}

			if(row + i < field.length && column >= i && i != 0) {
				field[row+i][column-i] = field[row+i][column-i] < 0 ? field[row+i][column-i]*-1 : field[row+i][column-i]+1;
			}
		}
		//*/
	}

	public void setClean(int row, int column) {
		for(int i = 0; i < field.length; i++) {

			field[row][column] = 0;

			if(field[row][i] > 0) {
				field[row][i]--;
			}
			if(field[i][column] > 0) {
				field[i][column]--;
			}

			if(row >= i && column >= i && field[row-i][column-i] > 0) {
				field[row-i][column-i]--;
			}
			if(row + i < field.length && column + i < field.length && field[row+i][column+i] > 0) {
				field[row+i][column+i]--;
			}
			if(row >= i && column + i < field.length && field[row-i][column+i] > 0) {
				field[row-i][column+i]--;
			}
			if(row + i < field.length && column >= i && field[row+i][column-i] > 0) {
				field[row+i][column-i]--;
			}
		}
	}

	public List<int[][]> getResult(){
		return result;
	}
}