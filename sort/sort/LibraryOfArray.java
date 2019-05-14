package sort;

import java.util.*;

import enums.*;

public class LibraryOfArray implements Sorter {

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
		// TODO 自動生成されたメソッド・スタブ
		Arrays.sort(intArray);
	}

	private void sortList(List<Integer> intList) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("リストには対応していません");
	}
}
