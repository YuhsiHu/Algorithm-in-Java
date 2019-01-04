package Backtracking;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * 问题描述： 罗密欧与朱丽叶身处一个m×n的迷宫中。 每一个方格表示迷宫中的一个房间。 这m×n个房间中有一些房间是封闭的，不允许任何人进入。
 * 在迷宫中任何位置均可沿8个方向进入未封闭的房间。 罗密欧位于迷宫的(p，q)方格中，他必须找出一条通向朱丽叶所在的(r，s)方格的路。
 * 在抵达朱丽叶之前，他必须走遍所有未封闭的房间各一次，而且要使到达朱丽叶的转弯次数为最少。
 * 每改变一次前进方向算作转弯一次。请设计一个算法帮助罗密欧找出这样一条道路。
 * 
 * 数据输入： 由文件input.txt给出输入数据。 第一行有3个正整数n，m，k，分别表示迷宫的行数，列数和封闭的房间数。
 * 接下来的k行中，每行2个正整数，表示被封闭的房间所在的行号和列号。最后的2行，每行也有2个正整数，分别表示罗密欧所处的方格(p，q)和朱丽叶所处的方格(r，s)。
 * 
 * 结果输出: 将计算出的罗密欧通向朱丽叶的最少转弯次数和有多少条不同的最少转弯道路输出到文件output.txt。
 * 文件的第一行是最少转弯次数。文件的第2行是不同的最少转弯道路数。接下来的n行每行m个数，表示迷宫的一条最少转弯道路。A[i][j]=k表示第k步到达方格(i,j)；A[i][j]=-1表示方格(i,j)是封闭的。
 * 如果罗密欧无法通向朱丽叶则输出“No Solution!”。 输入文件示例 input.txt 3 4 2 1 2 3 4 1 1 2 2
 * 
 * 输出文件示例 output.txt 6
 * 
 * @author Hu Yuxi
 * @date 2018-12-17
 *
 */
public class RomeoMaze {
	// 控制方向
	private static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	private static int[] dy = { 1, 1, 0, -1, -1, -1, 0, 1 };
	// 罗密欧和朱丽叶的位置
	private static int startX, startY, endX, endY;
	private static int n, m, k; // 行数，列数，被封闭的房间
	private static int dirs, minn, count;//当前转弯数，最少转弯数，转弯最少道路条数
	private static int[][] board, best;//当前，最佳

	/**
	 * 判断这个坐标没有被走过
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean check(int x, int y) {
		return (x > 0 && x <= n && y > 0 && y <= m && board[x][y] == 0);
	}

	/**
	 * 回溯法搜索路径
	 * @param x
	 * @param y
	 * @param dep
	 * @param di
	 */
	public static void Search(int x, int y, int dep, int di) {
		if (dep == n * m - k && x == endX && y == endY && dirs <= minn) {
			if (dirs < minn) {
				minn = dirs;
				count = 1;
				for (int i = 1; i <= n; i++)
					for (int j = 1; j <= m; j++)
						best[i][j] = board[i][j];
			} else
				count++;
			return;
		} else
			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (check(nx, ny)) {
					if (di != i && dep > 1)
						dirs++;
					board[nx][ny] = dep + 1;
					Search(nx, ny, dep + 1, i);
					if (di != i && dep > 1)
						dirs--;
					board[nx][ny] = 0;
				}
			}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner cin = new Scanner(new BufferedInputStream(System.in));
		System.out.println("请依次输入迷宫行数、列数、封闭的房间个数：");
		n = cin.nextInt();
		m = cin.nextInt();
		k = cin.nextInt();
		// 新建两个二维数组
		board = new int[n + 1][m + 1];
		best = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= m; j++)
				board[i][j] = 0;
		// 输入封闭房间位置
		System.out.println("请输入封闭房间的行号和列号：");
		for (int i = 0; i < k; i++) {
			int a = cin.nextInt();
			int b = cin.nextInt();
			// 标记封闭的房间
			board[a][b] = -1;
		}
		// 罗密欧位置就是起始位置
		System.out.println("哦！我的罗密欧在哪里？");
		startX = cin.nextInt();
		startY = cin.nextInt();
		// 朱丽叶位置就是终止位置
		System.out.println("啊！我的朱丽叶在哪里？");
		endX = cin.nextInt();
		endY = cin.nextInt();

		minn = 1000000;
		board[startX][startY] = 1;
		Search(startX, startY, 1, 0);
		System.out.println("最少转弯次数："+minn);
		System.out.println("最少转弯道路条数："+count);
		// 打印路径矩阵
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++)
				System.out.print(best[i][j] + " ");
			System.out.println();
		}
	}

}
