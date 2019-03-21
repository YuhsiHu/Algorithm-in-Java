package Sorts;

import static Sorts.SortUtils.*;

/**
 * @author Huyuxi
 * @date 2019-3-21
 * @see SortAlgorithm
 *
 */
public class BinaryTreeSort implements SortAlgorithm {

	/**
	 * 
	 * @author Huyuxi
	 *
	 * @param <T>
	 */
	interface TreeVisitor<T extends Comparable<T>> {
		void visit(Node<T> node);
	}

	/**
	 * 
	 * @author Huyuxi
	 *
	 * @param <T>
	 */
	private static class SortVisitor<T extends Comparable<T>> implements TreeVisitor<T> {

		private final T[] array;
		private int counter;

		SortVisitor(T[] array) {
			this.array = array;
		}

		@Override
		public void visit(Node<T> node) {
			array[counter++] = node.value;
		}
	}

	/**
	 * This class defines node
	 * 
	 * @author Huyuxi
	 *
	 * @param <T>
	 */
	private static class Node<T extends Comparable<T>> {
		private T value;
		private Node<T> left;
		private Node<T> right;

		/**
		 * Constructor
		 * 
		 * @param value
		 *            value
		 */
		Node(T value) {
			this.value = value;
		}

		/**
		 * Insert a new node
		 * 
		 * @param node
		 *            node
		 */
		void insert(Node<T> node) {
			if (less(node.value, value)) {
				if (left != null)
					left.insert(node);
				else
					left = node;
			} else {
				if (right != null)
					right.insert(node);
				else
					right = node;
			}
		}

		/**
		 * 
		 * @param visitor
		 */
		void traverse(TreeVisitor<T> visitor) {
			if (left != null)
				left.traverse(visitor);

			visitor.visit(this);

			if (right != null)
				right.traverse(visitor);
		}

	}

	/**
	 * This method implements the Generic Binary Tree Sort
	 *
	 * @param array
	 *            The array to be sorted 
	 * Sorts the array in increasing order
	 **/
	@Override
	public <T extends Comparable<T>> T[] sort(T[] array) {
		Node<T> root = new Node<>(array[0]);
		for (int i = 1; i < array.length; i++) {
			root.insert(new Node<>(array[i]));
		}
		root.traverse(new SortVisitor<>(array));
		return array;
	}

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		// integer input
		Integer[] intArray = { 12, 8, 8, 3, 18, 74, 7, 31, 23, 54, 27, 81, 12 };
		BinaryTreeSort treeSort = new BinaryTreeSort();
		Integer[] sorted = treeSort.sort(intArray);
		print(sorted);

		// decimal input
		Double[] decimalArray = { 8.2, 1.5, 3.1415926, 9.3, 5.1, 4.8, 2.6 };
		print(treeSort.sort(decimalArray));

		// alphabet input
		String[] stringArray = { "c", "a", "e", "b", "d", "dd", "da", "zz", "AA", "aa", "aB", "Hb", "Z" };
		print(treeSort.sort(stringArray));
	}

}