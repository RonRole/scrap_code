package main;

import java.util.*;

import enums.*;
import sort.*;



public class SortFactory {

	private SortSubject sort_sub;
	private SortType sort_type;

	public Sorter create() {
		sort_sub = selectSubject();
		Sorter returnSorter = selectSorter(sort_sub);
		return returnSorter;
	}

	private SortSubject selectSubject() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("ソート対象を選んでください");
		Arrays.stream(SortSubject.values()).map(subject -> subject.toString()).forEach(System.out::print);
		System.out.println("");

		int input = sc.nextInt();

		SortSubject result = Arrays.stream(SortSubject.values())
										.filter(subject -> subject.getId() == input)
										.findFirst()
										.orElse(null);

		if(result != null) {
			return result;
		}

		System.out.println("未実装です");
		return selectSubject();
	}

	private Sorter selectSorter(SortSubject sort_sub) {
		SortType[] types = sort_sub.getRelatedSortType();

		if(types == null) {
			return create();
		}

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("ソート方法を選んでください");
		Arrays.stream(types).map(type -> type.toString()).forEach(System.out::print);
		System.out.println("");

		int input = sc.nextInt();

		sort_type = Arrays.stream(types)
						.filter(subject -> subject.getId() == input)
						.findFirst()
						.orElse(null);

		if(sort_type == null) {
			return selectSorter(sort_sub);
		}

		Sorter returnSorter = sort_type.getThisSorter();

		if(returnSorter == null) {
			return selectSorter(sort_sub);
		}

		return returnSorter;
	}
}


