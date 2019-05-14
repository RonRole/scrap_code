package sort;

import enums.*;

public interface Sorter {
	/**
	 * @param sortObject
	 * 引数にソート対象(配列・リストなど)が与えられます。
	 * 与えられた引数をソートするメソッドです・
	 */
	void sort(Object sortObject);

	/**
	 * @return SortSubject
	 * このソートクラスが対応しているSortSubjectを返します。
	 */
	SortSubject getWhatSubjectIsUseable();
}
