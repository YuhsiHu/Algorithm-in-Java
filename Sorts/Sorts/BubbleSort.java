package Sorts;

import static Sorts.SortUtils.*;

/**
 * @author Huyuxi
 * @date 2019-3-21
 *
 * @see SortAlgorithm
 */
class BubbleSort implements SortAlgorithm {
	/**
	 * This method implements the Generic Bubble Sort
	 *
	 * @param array
	 *            The array to be sorted 
	 * Sorts the array in increasing order
	 **/
	@Override
	public <T extends Comparable<T>> T[] sort(T array[]) {
		int last = array.length;

		boolean swap;
		do {
			swap = false;
			for (int count = 0; count < last - 1; count++) {
				// index从0到n-1
				if (!less(array[count], array[count + 1])) {
					// 如果前一个比后一个大就交换位置
					swap = swap(array, count, count + 1);
				}
			}
			last--;
		} while (swap);
		return array;
	}

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Integer Input
		Integer[] integers = { 4, 23, 6, 78, 1, 54, 231, 9, 12 };
		BubbleSort bubbleSort = new BubbleSort();
		bubbleSort.sort(integers);

		// Output => 231, 78, 54, 23, 12, 9, 6, 4, 1
		print(integers);

		// String Input
		String[] strings = { "c", "a", "e", "b", "d" };
		// Output => e, d, c, b, a
		bubbleSort.sort(strings);
		print(strings);

	}
}
