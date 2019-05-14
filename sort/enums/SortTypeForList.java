package enums;

import sort.*;

public enum SortTypeForList implements SortType{
	LIB_LIST(0,"ライブラリのソート");

	private final int id;
	private final String name;

	private SortTypeForList(final int id, final String name) {
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
		//リスト
		if(SortTypeForList.LIB_LIST.equals(this)) {
			return new LibraryOfList();
		}
		System.out.println("未実装です");
		return null;
	}
}