package main;

import sort.*;

/**
 * @author yamasaki
 * ソートの実験・速度評価
 * 対応(2019年4月)
 * 配列　：バブルソート、クイックソート、ライブラリのソート
 * リスト：ライブラリのソート
 */

public class Sort_Main {
	public static void main(String args[]) {

		long times = 0;
		final long roopNum = 1;
		Generator generator = new Generator();
		SortFactory factory = new SortFactory();
		WaitThread waitThread = new WaitThread();

		//ソート役の作成
		Sorter sorter = factory.create();

		for(int i = 0; i < roopNum; i++) {

			/////////////////////////////////////
			//
			// GENERATE!!!
			//
			//////////////////////////////////////
			int size = (int) (Math.pow(10, 8));

			long genStart = System.nanoTime();

			waitThread.start();
			Object sortObject = generator.generate(size, sorter.getWhatSubjectIsUseable());
			waitThread.stopThread();

			long genEnd = System.nanoTime();

			System.out.println("generated");

			System.out.println("generate:" + (genEnd-genStart)*Math.pow(10, -9)+"s");

			times += genEnd-genStart;

			/////////////////////////////////////
			//
			// SORT!!!
			//
			//////////////////////////////////////

			System.out.println("start sorting...");
			long start = System.nanoTime();

			waitThread.reStartThread();
			sorter.sort(sortObject);
			waitThread.stopThread();

			long end = System.nanoTime();
			System.out.println("end sorting");

			System.out.println("sort:" + (end-start)*Math.pow(10, -9)+"s");

			times += (end-start);//*Math.pow(10, -9);
			System.out.println("sum:" + (genEnd-genStart+end-start)*Math.pow(10, -9)+"s");
		}

		System.out.println("average:" + (times/roopNum)*Math.pow(10, -9) + "s");
	}

	public void printNums(int[] nums) {
		StringBuilder sb2 = new StringBuilder();
		sb2.append("[");

		for(int i = 0; i < nums.length; i++) {
			sb2.append(nums[i]);
			sb2.append(",");
		}
		sb2.append("]");

		System.out.println(sb2);
	}

	public static boolean checkNums(int[] nums) {
		for(int i = 0; i < nums.length-1; i++) {
			if(nums[i] > nums[i+1]) {
				return false;
			}
		}
		return true;
	}
}
