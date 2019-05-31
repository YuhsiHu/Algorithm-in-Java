package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0
 * ？找出所有满足条件且不重复的三元组。 注意：答案中不可以包含重复的三元组。
 * 
 * @author Hu Yuxi
 *
 */
public class _015_ThreeSum {

	/**
	 * 
	 * @param nums
	 *            array
	 * @return 三元组的List
	 */
	public List<List<Integer>> threeSum(int[] nums) {

		final List<List<Integer>> res = new ArrayList<>();

		if (nums.length == 0)
			return res;
		// sort
		Arrays.sort(nums);

		if (nums[0] <= 0 && nums[nums.length - 1] >= 0) {
			for (int i = 0; i < nums.length - 2;) {
				if (nums[i] > 0)
					break;
				int l = i + 1;
				int r = nums.length - 1;

				do {
					int sum = nums[i] + nums[l] + nums[r];
					if (sum == 0) {
						res.add(Arrays.asList(nums[i], nums[l], nums[r]));
					}
					if (sum >= 0) {
						while (nums[r] == nums[--r] && r > l)
							;
					} else {
						while (nums[l] == nums[++l] && r > l)
							;
					}
				} while (r > l);
				while (nums[i] == nums[++i] && i < nums.length - 2)
					;
			}
		}
		return res;
	}
}
