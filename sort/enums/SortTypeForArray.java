package enums;

import sort.*;

public enum SortTypeForArray implements SortType {
	BABBLE_SORT(0, "バブルソート"),
	MERGE_SORT(1,"マージソート"),
	QUICK_SORT(2, "クイックソート"),
	LIB_ARRAY(3, "ライブラリのソート");


	private final int id;
	private final String name;

	private SortTypeForArray(final int id, final String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return String.format("[%s]:%s ", id, name);
	}

	public Sorter getThisSorter() {
		if(SortTypeForArray.BABBLE_SORT.equals(this)) {
			return new BabbleSort();
		}
		if(SortTypeForArray.QUICK_SORT.equals(this)) {
			return new QuickSort();
		}
		if(SortTypeForArray.LIB_ARRAY.equals(this)) {
			return new LibraryOfArray();
		}
		System.out.println("未実装です");
		return null;
	}
};