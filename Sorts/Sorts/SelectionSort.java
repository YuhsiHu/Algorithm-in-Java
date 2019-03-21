package Sorts;

import static Sorts.SortUtils.*;

/**
 * @author Huyuxi
 * @date 2019-3-21
 * @see SortAlgorithm
 *
 */
public class SelectionSort implements SortAlgorithm {
	/**
	 * This method implements the Generic Selection Sort
	 *
	 * @param arr
	 *            The array to be sorted 
	 * Sorts the array in increasing order
	 **/
	@Override
	public <T extends Comparable<T>> T[] sort(T[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			// Initial index of min
			int min = i;

			for (int j = i + 1; j < n; j++) {
				if (less(arr[j], arr[min])) {
					min = j;
				}
			}
			// Swap if index of min is changed
			if (min != i) {
				swap(arr, i, min);
			}
		}
		return arr;
	}

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Integer[] arr = { 4, 24, 6, 72, 1, 54, 256, 9, 12 };

		SelectionSort selectionSort = new SelectionSort();

		Integer[] sorted = selectionSort.sort(arr);

		// Output => 1 4 6 9 12 24 54 72 256
		SortUtils.print(sorted);

		// String Input
		String[] strings = { "c", "a", "e", "b", "d" };
		String[] sortedStrings = selectionSort.sort(strings);

		// Output =>a b c d e
		SortUtils.print(sortedStrings);
	}
}
