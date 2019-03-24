package Searches;

import java.util.Random;
import java.util.stream.Stream;

/**
 * This algorithm just compares all elements of an array to find a value
 *
 * Worst-case performance O(n) 
 * Best-case performance O(1) 
 * Average performance O(n) 
 * Worst-case space complexity
 *
 *
 * @author Huyuxi
 * @date 2019-3-24
 *
 * @see SearchAlgorithm
 */
public class LinearSearch implements SearchAlgorithm {

	/**
	 * Generic Linear search method
	 *
	 * @param array
	 *            List to be searched
	 * @param value
	 *            Key being searched for
	 * @return Location of the key
	 */
	@Override
	public <T extends Comparable<T>> int find(T[] array, T value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].compareTo(value) == 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//generate data
		Random r = new Random();
		int size = 200;
		int maxElement = 100;
		//这种写法参考于Java 8 lambda表达式
		Integer[] integers = Stream.generate(() -> r.nextInt(maxElement)).limit(size).toArray(Integer[]::new);

		//the element that should be found
		Integer shouldBeFound = integers[r.nextInt(size - 1)];

		LinearSearch search = new LinearSearch();
		int atIndex = search.find(integers, shouldBeFound);

		System.out.println(String.format("Should be found: %d. Found %d at index %d. An array length %d", shouldBeFound,
				integers[atIndex], atIndex, size));
	}

}
