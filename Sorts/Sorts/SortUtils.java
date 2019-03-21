package Sorts;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * This class includes util method for other classes
 * 
 * @author Huyuxi
 * @date 2019-3-21
 *
 **/
final class SortUtils {
	/**
	 * Swap places in an array
	 * 
	 * @param array
	 *            The array in which elements we want to swap
	 * @param idx
	 *            index of the first element
	 * @param idy
	 *            index of the second element
	 */
	static <T> boolean swap(T[] array, int idx, int idy) {
		T swap = array[idx];
		array[idx] = array[idy];
		array[idy] = swap;
		return true;
	}

	/**
	 * Check if the first element is less then the other element
	 * 
	 * @param first
	 *            first element
	 * @param other
	 *            second element
	 * @return true if the first element is less then the other element
	 */
	static <T extends Comparable<T>> boolean less(T first, T other) {
		return first.compareTo(other) < 0;
	}

	/**
	 * Print list
	 * 
	 * @param toPrint
	 *            The list which should be printed
	 */
	static void print(List<?> toPrint) {
		toPrint.stream().map(Object::toString).map(str -> str + " ").forEach(System.out::print);

		System.out.println();
	}

	/**
	 * Prints an array
	 * 
	 * @param toPrint
	 *            The array which should be printed
	 */
	static void print(Object[] toPrint) {
		System.out.println(Arrays.toString(toPrint));
	}

	/**
	 * Swap all position from {@param left} to @{@param right} for {@param array}
	 * 
	 * @param array
	 *            array
	 * @param left
	 *            is a left flip border of the array
	 * @param right
	 *            is a right flip border of the array
	 */
	static <T extends Comparable<T>> void flip(T[] array, int left, int right) {
		while (left <= right) {
			swap(array, left++, right--);
		}
	}
}