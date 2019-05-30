package LeetCode;

/**
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。 
 * 说明: 
 * 初始化 nums1和 nums2 的元素数量分别为 m 和 n。 
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 
 * @author Hu Yuxi
 *
 */
public class _088_IntegerArrayMerge {

	/**
	 * 倒序开始归并,只需要扫描一遍
	 * @param nums1
	 *            array1
	 * @param m
	 *            length of array1
	 * @param nums2
	 *            array2
	 * @param n
	 *            length of array2
	 */
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		for (int k = m + n - 1, i = m - 1, j = n - 1; k >= 0; k--) {
			if (i < 0) {
				//nums1都已经进入了
				nums1[k] = nums2[j--];
				continue;
			}
			if (j < 0) {
				//nums2都已经进入了
				nums1[k] = nums1[i--];
				continue;
			}
			//nums1和nums2都有剩余
			if (nums1[i] >= nums2[j])
				nums1[k] = nums1[i--];
			else
				nums1[k] = nums2[j--];
		}
	}
}
