package Sorts;

import java.util.Arrays;
import static Sorts.SortUtils.*;
/**
 * @author Huyuxi
 * @date 2019-3-21
 *
 */
class RadixSort {
	/**
	 * Get the max number in the array
	 * @param arr array
	 * @param n length
	 * @return max number
	 */
	private static int getMax(int arr[], int n) {
		int mx = arr[0];
		for (int i = 1; i < n; i++)
			if (arr[i] > mx)
				mx = arr[i];
		return mx;
	}

	/**
	 * 
	 * @param arr
	 * @param n
	 * @param exp
	 */
	private static void countSort(int arr[], int n, int exp) {
		int output[] = new int[n];
		int i;
		int count[] = new int[10];
		//initialize
		Arrays.fill(count, 0);
        //某位为index这个数字的元素个数
		for (i = 0; i < n; i++)
			count[(arr[i] / exp) % 10]++;
		
//		for(int p :count) {
//			System.out.print(p+" ");
//		}
//		System.out.println();
		
        //该位数比当前元素的该位数小的元素个数
		for (i = 1; i < 10; i++)
			count[i] += count[i - 1];
		
//		for(int p :count) {
//			System.out.print(p+" ");
//		}
//		System.out.println();
		
        //这一轮排序这个元素的位置等于比它该位小的元素个数
		for (i = n - 1; i >= 0; i--) {
			output[ count[(arr[i] / exp) % 10] - 1 ] = arr[i];
			count[(arr[i] / exp) % 10]--;
		}
		
		//copy
		for (i = 0; i < n; i++)
			arr[i] = output[i];		
		
//		for(int p :arr) {
//			System.out.print(p+" ");
//		}
//		System.out.println();
		
	}

	/**
	 * 
	 * @param arr The array to be sorted 
	 * @param n length of the array
	 */
	private static void radixsort(int arr[], int n) {
		int max = getMax(arr, n);
		for (int exp = 1; max / exp > 0; exp *= 10)
			//每一轮都针对一位进行排序,按个位、十位、百位......
			countSort(arr, n, exp);
	}

	/**
	 * 
	 * @param arr array to print
	 * @param n length
	 */
	static void print(int arr[], int n) {
		for (int i = 0; i < n; i++)
			System.out.print(arr[i] + " ");
	}

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int array[] = { 170, 45, 75, 90, 802, 24, 2, 66 };
		int n = array.length;
		radixsort(array, n);
		print(array, n);
	}
}
