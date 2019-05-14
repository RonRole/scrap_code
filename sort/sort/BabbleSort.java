package sort;

import java.util.*;

import enums.*;

public class BabbleSort implements Sorter {

	@SuppressWarnings("unchecked")
	@Override
	public void sort(Object sortObject) {
		if(sortObject instanceof int[]) {
			sortArray((int[])sortObject);
		}
		if(sortObject instanceof List) {
			sortList((List<Integer>) sortObject);
		}
	}

	@Override
	public SortSubject getWhatSubjectIsUseable() {
		// TODO 自動生成されたメソッド・スタブ
		return SortSubject.ARRAY;
	}

	private void sortArray(int[] intArray) {
		//BabbleSort
		boolean continueFlg = false;
		do {
			for(int i = 0; i < intArray.length-1; i++) {
				if(i == 0) {
					continueFlg = false;
				}
				int left = intArray[i];
				int right = intArray[i+1];
				if(left > right) {
					intArray[i] = right;
					intArray[i+1] = left;
					continueFlg = true;
				}
			}
		}
		while(continueFlg);
	}

	private void sortList(List<Integer> intList) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("リストには対応していません");
	}



}
