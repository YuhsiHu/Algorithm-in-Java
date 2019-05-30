package LeetCode;

/**
 * 
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * 注意：
 * 假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回0。
 * 说明：
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231, 231 − 1]。如果数值超过这个范围，返回
 * INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 * 
 * @author Hu Yuxi
 *
 */
public class _008_MyAtoi {
	/**
	 * 
	 * @param str
	 *            string
	 * @return integer
	 */
	public int myAtoi(String str) {
		// 去除掉前后的空格
		String str1 = str.trim();
		// 存储最终过滤出来的字符串
		String str2 = null;
		// 字符串不为空时并且字符串不全是空白字符串时才转换
		if (str1 != null && str1.isEmpty() == false) {
			char f = str1.charAt(0);
			// 判断字符串中的第一个非空格字符是不是一个有效整数字符
			if (f >= '0' && f <= '9' || f == '+' || f == '-') {
				str2 = str1.substring(0, 1); // 把第一位放进去(只能是数字、正负号)
				// 这时候循环只要数字，因为正负号只能出现在第一位
				for (int i = 1; i < str1.length(); i++) {
					if (str1.charAt(i) >= '0' && str1.charAt(i) <= '9') {
						str2 = str1.substring(0, i + 1);
					}
					// 这是遇到不符合要求的字符，直接忽略剩余元素
					else {
						break;
					}
				}
			}
		}
		// 判断最终字符串是否为空或则只有一个正负号
		if (str2 == null || str2.equals("+") || str2.equals("-"))
			// 此时str2是String对象，如果使用==比较则比较的时内存地址
			return 0;
		// 最终转换成的数字
		int num = 0;
		// 使用异常机制打印结果
		try {
			num = Integer.parseInt(str2);
		} catch (Exception e) {
			if (str2.charAt(0) == '-')
				return Integer.MIN_VALUE;
			return Integer.MAX_VALUE;
		}
		return num;
	}
}
