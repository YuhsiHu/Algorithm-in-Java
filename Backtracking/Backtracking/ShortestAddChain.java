package Backtracking;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * 
 * 最优求幂问题:给定一个正整数n和一个实数x，如何用最少的乘法次数计算出x的n次方。 例如，可以用6次乘法逐步计算 x23如下:
 * x，x2，x3，x5，x10，x20，x23。 可以证明 计算x23最少需要 6次乘法。计算 x23的幂序列中各幂次
 * 1，2，3，5，10，20，23组成了一 个关于整数 23 的加法链。 在一般情况下，计算 xn的幂序列中各幂次组成正整数n的一个加法链：
 * 1=a0<a1<a2<...<ar=n ai=aj+ak,k≤j<i,i=1,2,...,r 上述最优求幂问题相应于正整数 n 的最短加法链问题，即求 n
 * 的一个加法链使其长度 r 达到最小。正整数n的最短加法链长度记为l(n)。
 * 
 * @author Hu Yuxi
 * @date 2018-12-18
 *
 */
public class ShortestAddChain {

	private static int MAXN = 10000;
	private static int n;

	private static int[] chain;// 加法链
	private static int best;// 最优值

	private static boolean found;// 是否找到
	private static int lb, ub, t;

	private static int[] a, parent;

	/**
	 * 主函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("请输入一个正整数");
		Scanner input = new Scanner(System.in);

		while (true) {
			best = MAXN;
			n = input.nextInt();
			chain = new int[MAXN];
			a = new int[MAXN];
			parent = new int[MAXN + 1];
			if (n <= MAXN) {
				search();
				output();
			} else
				System.out.println("Number too large!");
		}
	}

	/**
	 * 搜索
	 */
	private static void search() {
		lb = lowerb(n);
		ub = powertree(n);
		t = gett(n);
		if (lb < ub) {
			found = false;
			while (!found) {
				System.out.println("lb=" + lb);
				a[1] = 1;
				backtrack(1);// 调用回溯法
				lb++;
				if (lb == ub)
					found = true;
			}
		}
	}

	private static int powertree(int n) {
		found = false;
		ub = 1;
		for (int i = 1; i <= MAXN; i++)
			parent[i] = 0;
		while (!found) {
			a[1] = 1;
			find(1);
			ub++;
		}

		return best;
	}

	private static void find(int step) {
		int i, k;
		if (!found)
			if (a[step] == n) {
				best = step;
				for (i = 1; i <= best; i++)
					chain[i] = a[i];
				found = true;

				return;
			} else if (step <= ub)
				for (i = 1; i <= step; i++) {
					k = a[step] + a[i];
					if (k <= n) {
						a[step + 1] = k;
						if (parent[k] == 0)
							parent[k] = a[step];
						if (parent[k] == a[step])
							find(step + 1);
					}
				}
	}

	/**
	 * 回溯法求解
	 * 
	 * @param step
	 */
	private static void backtrack(int step) {
		if (!found)
			if (a[step] == n) {
				best = step;
				for (int i = 1; i <= best; i++)
					chain[i] = a[i];
				found = true;

				return;
			} else if (step < lb)
				for (int i = step; i >= 1; i--)
					if (2 * a[i] > a[step])
						for (int j = i; j >= 1; j--) {
							int k = a[i] + a[j];
							a[step + 1] = k;
							if (k > a[step] && k <= n)
								if (!pruned(step + 1))
									backtrack(step + 1);
						}
	}

	private static boolean pruned(int step) {
		if (step < lb - t - 1)
			return (h(3 * a[step]) + step + 2 > lb);
		else
			return (h(a[step]) + step > lb);
	}

	private static int h(int num) {
		int i = 0;
		while (num < n) {
			num = num << 1;
			i++;
		}

		return i;
	}

	private static int lowerb(int m) {
		int i = 0, j = 1;
		while (m > 1) {
			i++;
			if (odd(m))
				j++;
			m = m >> 1;
		}
		i += log2(j) + 1;

		return i;
	}

	private static int log2(int m) {
		int i = 0, j = 1;
		while (m > 1) {
			i++;
			if (odd(m))
				j++;
			m = m >> 1;
		}
		if (j > 1)
			i++;

		return i;
	}

	private static int gett(int num) {
		int i = 0;
		while (!odd(num)) {
			num = num >> 1;
			i++;
		}

		return i - 1;
	}

	private static void output() {
		System.out.println(best - 1);
		for (int i = 1; i <= best; i++)
			System.out.print(chain[i] + " ");
	}

	private static boolean odd(int num) {
		return num % 2 == 1;
	}

}
