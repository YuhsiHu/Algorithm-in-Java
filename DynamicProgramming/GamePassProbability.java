package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.SocketException;

/**
 * 小明想要在王者荣耀游戏里晋升一个段位，假设他一共需打了n场比赛，且必须成功赢得至少70%的场次才能成功晋升。
 * 假设每场比赛小明获胜的概率分别为p1，p2，…，pn，请帮他算出成功晋级段位的概率是多少？
输入：
参数1：整数num（0  num  1000），表示比赛的场数。
参数2：整数数组p[num] = {p1，p2，…，pnum}，其中pi表示小明有pi%的概率赢得第i场比赛。（0  pi  100）
输出：
成功晋级段位的概率，保留小数点后5位，最后结果四舍五入。
 * @author Hu Yuxi
 *
 */
public class GamePassProbability {
	public double calculatePassProbability(int[] p, int num) {
		double pass = 0.0d;
		double[][] matrix = new double[num + 1][num + 1];
		
		if (num==0) return 0;

		//matrix[i][j]代表到第i场时候赢j场概率
		matrix[0][0]=1;//第0场赢0场概率为1
		matrix[0][1]=0;//第0场赢1场概率为0	
		for (int i = 1; i <= num; i++) {
			// 根据递推式计算matrix[i][j]
			matrix[i][0] = matrix[i - 1][0] * (1 - p[i - 1] / 100.0);
			for (int j = 1; j <= num; j++) {
				matrix[i][j] = matrix[i - 1][j] * (1 - p[i - 1] / 100.0) + matrix[i - 1][j - 1] * p[i - 1] / 100.0;
			}
		}

		double low = 0.7 * num;
		for (int i = (int) Math.ceil(low); i <= num; i++) {			
			pass += matrix[num][i];
		}

		for (int i = 0; i <= num; i++)
			for (int j = 0; j <= num; j++) {
				System.out.print(" " + matrix[i][j] + " ");
				if (j == num)
					System.out.println("");
			}

		BigDecimal bg = new BigDecimal(pass);
		pass = bg.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
		return pass;
	}

	public static void main(String[] args) throws SocketException, IOException {
		int num;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("小明，你还想要打多少把王者荣耀？");
		String numstr = br.readLine();
		num = Integer.parseInt(numstr);
		int[] p = new int[num];
		System.out.println("每局多少概率胜利？");
		for (int i = 0; i < num; i++) {
			String pi = br.readLine();
			p[i] = Integer.parseInt(pi);
		}
		GamePassProbability test = new GamePassProbability();
		double pass = test.calculatePassProbability(p, num);
		System.out.println("你需赢的场数为：" + Math.ceil(0.7 * num));
		System.out.println("你晋级的概率是：" + pass);
	}
}
