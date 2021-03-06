package DynamicProgramming;

/**
 * Egg dropping problem
 * 
 * @author Hu Yuxi
 *
 */
public class EggDropping {
	/**
	 * m层需要多少个鸡蛋来测试
	 * @param n
	 *            n eggs
	 * @param m
	 *            m floors
	 * @return min trials
	 */
	private static int minTrials(int n, int m) {

		int[][] eggFloor = new int[n + 1][m + 1];
		int result, x;

		for (int i = 1; i <= n; i++) {
			eggFloor[i][0] = 0; // zero trial for zero floor.
			eggFloor[i][1] = 1; // one trial for one floor
		}

		// j trials for only 1 egg
		for (int j = 1; j <= m; j++)
			eggFloor[1][j] = j;

		// Using bottom-up approach in DP
		for (int i = 2; i <= n; i++) {
			for (int j = 2; j <= m; j++) {
				// init
				eggFloor[i][j] = Integer.MAX_VALUE;
				// we drop the egg from x-th floor
				for (x = 1; x <= j; x++) {
					// 1+Max(broken:[i-1,x-1],unbroken:[i][j-x])
					result = 1 + Math.max(eggFloor[i - 1][x - 1], eggFloor[i][j - x]);
					if (result < eggFloor[i][j])
						eggFloor[i][j] = result;
				}
			}
		}

		return eggFloor[n][m];
	}

	/**
	 * n个鸡蛋最多能测多少层
	 * @param n
	 *            n eggs
	 * @param m
	 *            m floors
	 * @return min trials
	 */
	private static int minTrialsOptimized(int n, int m) {
		if (n == 0)
			return 0;
		if (n == 1)
			return m;

		//init
		int[][] eggFloor = new int[m + 2][n + 2];//m floor n eggs
		eggFloor[0][0] = 0;

		for (int i = 1; i <= m; i++) {
			eggFloor[i][0] = 0;
			for (int j = 1; j <= n; j++) {
				eggFloor[i][j] = eggFloor[i - 1][j] + eggFloor[i - 1][j - 1] + 1;
				if (eggFloor[i][j] >= m)
					return i;
			}
		}
		return m;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		int n = 1, m = 2;
		// result outputs min no. of trials in worst case for n eggs and m floors
		int result1 = minTrials(n, m);
		int result2 =minTrialsOptimized(n, m);
		System.out.println(result1);
		System.out.println(result2);
	}
}
