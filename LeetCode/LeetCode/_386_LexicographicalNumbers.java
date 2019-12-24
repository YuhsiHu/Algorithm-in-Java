package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数 n, 返回从 1 到 n 的字典顺序。 例如，给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
 * 请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。
 * 
 * @author Hu Yuxi
 *
 */
public class _386_LexicographicalNumbers {
	
	/**
	 * 
	 * @param n n
	 * @return lexical order
	 */
	public List<Integer> lexicalOrder(int n) {
		List<Integer> list = new ArrayList<>();
		//遍历1-9作为首字母的数字
		for (int i = 1; i < 10; i++) {
			if (i <= n) {
				list.add(i);
				add(list, n, i);
			} else {
				break;
			}
		}
		return list;
	}

	/**
	 * 
	 * @param list lexical order
	 * @param n 最大数字
	 * @param startNum 起始数字
	 */
	private void add(List<Integer> list, int n, int startNum) {
		startNum *= 10;
		for (int i = 0; i < 10; i++, startNum++) {
			if (startNum <= n) {
				list.add(startNum);
				add(list, n, startNum);
			} else {
				return;
			}
		}
	}
}
