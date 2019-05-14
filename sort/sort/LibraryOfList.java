package sort;

import java.util.*;

import enums.*;

public class LibraryOfList implements Sorter {

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
		return SortSubject.LIST;
	}

	private void sortArray(int[] intArray) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("配列には対応していません");
	}

	private void sortList(List<Integer> intList) {
		// TODO 自動生成されたメソッド・スタブ
		intList.sort(Comparator.naturalOrder());
	}


}
