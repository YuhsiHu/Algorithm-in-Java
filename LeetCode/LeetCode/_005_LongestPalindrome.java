package LeetCode;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 
 * @author Hu Yuxi
 *
 */
public class _005_LongestPalindrome {
	/**
	 * 
	 * @param s
	 *            string
	 * @return longest palindrome
	 */
	public String longestPalindrome(String s) {
		if (s == null || s.length() < 1)
			return "";
		int start = 0, end = 0;// 记录最大回文子串起止index

		for (int i = 0; i < s.length(); i++) {
			// 以一个中心为起始
			int len1 = expandAroundCenter(s, i, i);
			// 以两个为起始
			int len2 = expandAroundCenter(s, i, i + 1);
			// 取长度更大的
			int len = Math.max(len1, len2);
			// 从中心展开获得索引,注意计算
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}

	/**
	 * 
	 * @param s
	 *            string
	 * @param left
	 *            left index
	 * @param right
	 *            right index
	 * @return length
	 */
	private int expandAroundCenter(String s, int left, int right) {
		int l = left, r = right;
		while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
			l--;
			r++;
		}
		return r - l - 1;
	}
}
