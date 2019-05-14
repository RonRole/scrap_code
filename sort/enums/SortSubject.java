package enums;

public enum SortSubject {
	ARRAY(0,"配列"),
	LIST(1,"リスト"),
	GOLF(2,"ゴルフ");

	private final int id;
	private final String name;

	private SortSubject(final int id, final String name) {
		this.id = id;
		this.name = name;
	}

	public String toString() {
		return String.format("[%s]:%s ", id, name);
	}

	public int getId() {
		return id;
	}

	public SortType[] getRelatedSortType() {
		if(this.equals(ARRAY)) {
			return SortTypeForArray.values();
		}
		if(this.equals(LIST)) {
			return SortTypeForList.values();
		}
		System.out.println("未実装です");
		return null;
	}
}