package main;

public class Main5 {
	public static void main(String[] args) {
		//ナップサック問題
		Item[] items = new Item[] {
			Item.makeItem(0,0),Item.makeItem(2, 3),Item.makeItem(1, 2),Item.makeItem(3, 6),
			Item.makeItem(2, 1),Item.makeItem(1, 3),Item.makeItem(5, 85)
		};

		int limit = 8;

		/*
		 *    \重量  0   100   200   300    400   500   600  700
		 * item.value,value,value,
		 *    0.    0      0     0     0      0     0     0     0
		 *    1.    0      0     0   500    500   500   500   500
		 *    2.    0      0     0   500    800   800   800  1300
		 *    3.    0      0     0   500    800   800   800  1300
		 *     .
		 *     .
		 *     .
		 *     .
		 */

		int slice = 1;//数字刻みの値

		int[][] dp_field = new int[items.length][limit+1];

		for(int item_index = 1; item_index < items.length; item_index++) {
			for(int weight_sliced = 0; weight_sliced <= limit; weight_sliced+=slice) {
				//容量不足時
				if(weight_sliced < items[item_index].weight) {
					dp_field[item_index][weight_sliced] = dp_field[item_index-1][weight_sliced];
				}

				//容量のある時
				if(weight_sliced >= items[item_index].weight) {
					dp_field[item_index][weight_sliced] =
							Math.max(
								/*アイテムを取った場合*/
								items[item_index].value + dp_field[item_index-1][weight_sliced-items[item_index].weight],

								/*アイテムを取らなかった場合*/
								dp_field[item_index-1][weight_sliced]
							);
				}
			}
		}

		for(int i = 0; i < dp_field.length; i++) {
			for(int j = 0; j < dp_field[i].length; j++) {
				System.out.print(String.format("%03d ",dp_field[i][j]));
			}
			System.out.println("");
		}
	}
}

class Item {
	public final int weight,value;

	private Item(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}

	public static Item makeItem(int weight, int value) {
		return new Item(weight,value);
	}
}
