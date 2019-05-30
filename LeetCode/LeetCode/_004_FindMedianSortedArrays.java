package LeetCode;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。 
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m +n))。 
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 
 * @author Hu Yuxi
 *
 */
public class _004_FindMedianSortedArrays {

	/**
	 * 使用两个指针一共移动(m+n+1)/2次即可
	 * @param nums1
	 *            array1
	 * @param nums2
	 *            array2
	 * @return median num
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int n1 = nums1.length;
		int n2 = nums2.length;
		if (n1 > n2)
			return findMedianSortedArrays(nums2, nums1);
		
		int k = (n1 + n2 + 1) / 2;//总共需要移动的位置个数
		int left = 0;
		int right = n1;//取短的数组的左右边界
		
		while (left < right) {
			int m1 = left + (right - left) / 2;//二分法
			int m2 = k - m1;
			if (nums1[m1] < nums2[m2 - 1])
				//第一次进入,各切一半的时候nums1[m1]还小于nums2[m2-1],证明m1取小了
				left = m1 + 1;
			else
				right = m1;
		}
		
		int m1 = left;
		int m2 = k - left;
		//取大的那个数做中位数
		int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1], m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);
		if ((n1 + n2) % 2 == 1)
			return c1;
		//偶数个的时候
		int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1], m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);
		return (c1 + c2) * 0.5;

	}
}
