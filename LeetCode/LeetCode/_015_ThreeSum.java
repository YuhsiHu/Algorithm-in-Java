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

		int length=nums.length;
		if (length == 0)
			return res;
		// sort
		Arrays.sort(nums);

		if (nums[0] <= 0 && nums[length - 1] >= 0) {
			//最小的数小于0最大的数大于0,两端开始搜
			for (int i = 0; i < length - 2;) {
				if (nums[i] > 0)
					break;
				int l = i + 1;
				int r = length - 1;

				//对给定的i索引左右两端查找有没有一对让sum=0
				do {
					int sum = nums[i] + nums[l] + nums[r];
					if (sum == 0) {
						//sum=0直接添加
						res.add(Arrays.asList(nums[i], nums[l], nums[r]));
					}
					if (sum >= 0) {
						//中间i不变的情况下sum>=0说明r太大了
						while (nums[r] == nums[--r] && r > l)
							;
					} else {
						//中间i不变的情况下sum<=0说明l太小了
						while (nums[l] == nums[++l] && r > l)
							;
					}
				} while (l<r);
				//如果有和i索引处重复的值就直接跳过了,不会重复添加
				while (nums[i] == nums[++i] && i < length - 2)
					;
			}
		}
		return res;
	}
}
