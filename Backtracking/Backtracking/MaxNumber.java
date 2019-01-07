package Backtracking;

import java.math.BigInteger;

/**
 * 一个有趣的高精度数据：构造一个尽可能大的数，使其从高到低满足前一位能被1整除，前2为能被2整除，…, 前n位能被n整除。
 * 这属于回溯法当中的子集树问题
 * 
 * @author Hu Yuxi
 * @date 2018-12-17
 *
 */
public class MaxNumber {

	int[] a = new int[101];// 存储当前值
	int[] b = new int[101];// 存储最优值
	int v = 0;// 记录最大多少位

	/**
	 * Initialize
	 */
	public void MaxNumber() {
		for (int i = 0; i <= 100; i++) {
			a[i] = 0;
			b[i] = 0;
		}
	}

	/**
	 * 试探第i位应该取什么数字
	 * 
	 * @param i
	 *            第i位
	 */
	public void backTracking4MaxNumber(int i) {
		if (biggerThan(a, b)) {
			// 如果当前值比最优值还要大，替换
			assignA2B(a, b);
		}

		int j;
		// 首位从1开始试探
		if (i == 1)
			j = 1;
		else
			j = 0;

		// 对第i位进行试探，OK则去i+1位接着试探，否则换一个数字
		for (; j <= 9; j++) {
			a[i] = j;
			if (OK(a, i)) {
				// 对下一个位试探
				backTracking4MaxNumber(i + 1);
			}
			a[i] = -1;
		}
	}

	/**
	 * 判断两个数组中的数字大小
	 * 
	 * @param a
	 *            a
	 * @param b
	 *            b
	 * @return a>b, return true,else return false
	 */
	public boolean biggerThan(int[] a, int[] b) {
		BigInteger a1, b1, c;
		// 首位赋值
		a1 = BigInteger.valueOf(a[1]);
		b1 = BigInteger.valueOf(b[1]);
		c = BigInteger.valueOf(10);
		for (int i = 2; i <= 100; i++) {
			if (a[i] != -1) {
				a1 = a1.multiply(c);
				a1 = a1.add(BigInteger.valueOf(a[i]));
			} else
				break;
		}

		for (int i = 2; i <= 100; i++) {
			if (b[i] != -1) {
				b1 = b1.multiply(c);
				b1 = b1.add(BigInteger.valueOf(b[i]));
			} else
				break;
		}

		if (a1.compareTo(b1) == 1)
			return true;
		else
			return false;
	}

	/**
	 * 赋值
	 * 
	 * @param a
	 *            a
	 * @param b
	 *            b
	 */
	public void assignA2B(int[] a, int[] b) {
		for (int i = 0; i <= 100; i++) {
			b[i] = a[i];
		}
	}

	/**
	 * 判断是否满足前n位能被n整除要求
	 * 
	 * @param a
	 * @param n
	 * @return
	 */
	public boolean OK(int[] a, int n) {
		int r = 0;
		for (int i = 1; i <= n; i++) {
			r = r * 10 + a[i];
			r = r % n;// 整数倍乘以10没用故只用余数，防止溢出
		}
		if (r == 0) {
			if (n > v) {
				v = n;
			}
			return true;
		}
		return false;
	}

	/**
	 * 返回结果
	 * 
	 * @return
	 */
	public BigInteger returnMaxNumber() {
		BigInteger c = BigInteger.valueOf(10);
		BigInteger result = BigInteger.valueOf(b[1]);
		for (int i = 2; i <= v; i++) {
			result = result.multiply(c);
			result = result.add(BigInteger.valueOf(b[i]));
		}
		return result;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MaxNumber maxNumber = new MaxNumber();
		maxNumber.backTracking4MaxNumber(1);
		BigInteger result = maxNumber.returnMaxNumber();
		System.out.println(result);
	}
}
