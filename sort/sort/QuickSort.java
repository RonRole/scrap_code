package sort;

import java.util.*;

import enums.*;

public class QuickSort implements Sorter {

	@SuppressWarnings("unchecked")
	@Override
	public void sort(Object sortObject) {
		// TODO 自動生成されたメソッド・スタブ
		if(sortObject instanceof int[]) {
			quickSort2((int[])sortObject,0, ((int[]) sortObject).length-1);
			return;
		}
		if(sortObject instanceof List) {
			sortList((List<Integer>) sortObject);
			return;
		}
	}

	@Override
	public SortSubject getWhatSubjectIsUseable() {
		// TODO 自動生成されたメソッド・スタブ
		return SortSubject.ARRAY;
	}

	private void sortList(List<Integer> intList) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("配列には対応していません");
	}



	private void quickSort(int[] nums, int leftPosition, int rightPosition) {

		//左端と右端が等しいとき、終了
		if(leftPosition == rightPosition) {
			//System.out.println("おわり");
			return;
		}


		//軸の数字
		int pivotPosition = leftPosition;
		int pivot = nums[pivotPosition];

		int left = leftPosition;
		int right = rightPosition;

		do {
			for(; left < rightPosition; left++) {
				if(nums[left] > pivot) {
					break;
				}
			}

			for(; right > leftPosition; right--) {
				if(nums[right] < pivot) {
					break;
				}
			}

			if(left < right) {
				int temp = nums[left];
				nums[left] = nums[right];
				nums[right] = temp;
				left++;
				right--;
			}

			//軸と軸未満の数を交換
			if(left >= right) {
				int temp = nums[right];
				nums[pivotPosition] = temp;
				nums[right] = pivot;
			}
		}
		while(left < right);

		quickSort(nums,leftPosition, right);
		quickSort(nums, left, rightPosition);

	}

	private void quickSort2(int[] nums, int left, int right) {

		if(left == right) {
			return;
		}

		int pivot = nums[left];
		int left2 = left;
		int right2 = right;

		while(true) {

			//軸以上の数が見つかるまで、左のカウンタを進める
			while(nums[left2] < pivot) {
				left2++;
			}

			//軸以下の数が見つかるまで、右のカウンタを進める
			while(nums[right2] > pivot) {
				right2--;
			}

			//カウンタがぶつかったとき、ループを抜ける
			if(left2 >= right2) {
				break;
			}

			//左と右のカウンタの位置にある数字を入れ替える
			int temp = nums[left2];
			nums[left2] = nums[right2];
			nums[right2] = temp;
			left2++;
			right2--;
		}

		//無限ループ防止用
		if(left2 == right2) {
			left2++;
		}

		//printNums(nums);
		quickSort2(nums, left, right2);

		//printNums(nums);

		quickSort2(nums, left2, right);
	}

}
