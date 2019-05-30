package LeetCode;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,
 * 231 − 1]。 请根据这个假设，如果反转后整数溢出那么就返回 0。
 * 
 * @author Hu Yuxi
 *
 */
public class _007_IntegerReverse {

	/**
	 * 
	 * @param x integer
	 * @return integer after reversion
	 */
	public int reverse(int x) {
		int rev = 0;
		while (x != 0) {
			int newRev = rev * 10 + x % 10;
			if ((newRev - x % 10) / 10 != rev) {
				//溢出
				return 0;
			}
			rev = newRev;
			x = x / 10;
		}
		return rev;
	}

}
