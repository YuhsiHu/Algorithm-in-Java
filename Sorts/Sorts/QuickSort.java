package Sorts;

import static Sorts.SortUtils.*;

/**
 * @author Huyuxi
 * @date 2019-3-21
 * @see SortAlgorithm
 *
 */
class QuickSort implements SortAlgorithm {
	/**
	 * This method implements the Generic Quick Sort
	 *
	 * @param array
	 *            The array to be sorted 
	 * Sorts the array in increasing order
	 **/
	@Override
	public <T extends Comparable<T>> T[] sort(T[] array) {
		doSort(array, 0, array.length - 1);
		return array;
	}

	/**
	 * The sorting process
	 *
	 * @param left
	 *            The first index of an array
	 * @param right
	 *            The last index of an array
	 * @param array
	 *            The array to be sorted
	 *
	 **/
	private static <T extends Comparable<T>> void doSort(T[] array, int left, int right) {
		if (left < right) {
			// 递归切分
			int pivot = partition(array, left, right);
			doSort(array, left, pivot - 1);
			doSort(array, pivot, right);
		}
	}

	/**
	 * This method finds the partition index for an array
	 *
	 * @param array
	 *            The array to be sorted
	 * @param left
	 *            The first index of an array
	 * @param right
	 *            The last index of an array
	 * 
	 **/
	private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
		int mid = (left + right) / 2;
		T pivot = array[mid];

		while (left <= right) {
			// index从小到大找pivot左边第一个比它大的
			while (less(array[left], pivot)) {
				// 左值小于哨兵
				left++;
			}
			// index从大到小找pivot右边第一个比它小的
			while (less(pivot, array[right])) {
				// 右值大于哨兵
				right--;
			}
			// 如果在对应分区找到了两边位置不对的,交换它们
			if (left <= right) {
				swap(array, left, right);
				left++;
				right--;
			}
		}
		return left;
	}

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// For integer input
		Integer[] array = { 3, 4, 1, 32, 0, 5, 12, 2, 5, 7, 8, 9, 2, 45, 126, 5 };
		QuickSort quickSort = new QuickSort();
		quickSort.sort(array);
		// Output => 0 1 2 2 3 4 5 5 5 7 8 9 12 32 45 126
		print(array);

		// For alphabet input
		String[] stringArray = { "c", "a", "e", "b", "d" };
		quickSort.sort(stringArray);
		// Output => a b c d e
		print(stringArray);
	}
}
