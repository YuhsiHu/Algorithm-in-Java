package Sorts;

import static Sorts.SortUtils.less;
import static Sorts.SortUtils.print;

/**
 * @author Huyuxi
 * @date 2019-3-21
 *
 * @see SortAlgorithm
 */
class InsertionSort implements SortAlgorithm {
	/**
	 * This method implements the Generic Insertion Sort
	 * 
	 * @param array
	 *            The array to be sorted
	 * 
	 *            Sorts the array in increasing order
	 *
	 **/
	@Override
	public <T extends Comparable<T>> T[] sort(T[] array) {
		for (int j = 1; j < array.length; j++) {
			// Picking up the key(Card)
			T key = array[j];
			int i = j - 1;

			while (i >= 0 && less(key, array[i])) {
				// 当key比它前面的还小的时候，把前面的往后面挪
				array[i + 1] = array[i];
				i--;
			}
			// Put the key at its correct position in the sorted sub array
			array[i + 1] = key;
		}
		return array;
	}

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Integer Input
		Integer[] integers = { 4, 24, 8, 78, 1, 64, 256, 9, 16 };
		InsertionSort sort = new InsertionSort();
		sort.sort(integers);
		// Output => 1 4 8 9 16 24 64 78 256
		print(integers);

		// String Input
		String[] strings = { "c", "a", "e", "b", "d" };
		sort.sort(strings);
		// Output => a b c d e
		print(strings);
	}
}
