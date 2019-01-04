/**
 * 
 * @author Hu Yuxi
 * @date 2018-12-22
 *
 */
public class TSPByBacktrack {

	int n;// 城市数量
	int NoEdge=Integer.MAX_VALUE;// 两座城市之间不直接连接定为MAX_VALUE
	int[][] a = new int[n][n];// 邻接矩阵，存储任意两个城市之间的代价
	int[] bestx = new int[n]; // 存储当前最小代价的路线
	int[] x = new int[n];// 存储当前的路线
	int bestc = Integer.MAX_VALUE;// best cost,存储当前最小代价
	int cc = 0;// current cost,存储当前代价

	/**
	 * 无参构造函数
	 */
	public TSPByBacktrack() {
		
	}
	/**
	 * 
	 * @param map 邻接矩阵，n座城市矩阵为[n+1][n+1]
	 * @param cities 城市数目
	 */
	public void initializeMap(int[][]map,int cities) {
		n=cities;
		this.a = new int[n+1][n+1];
		this.x= new int[n+1];
		a=map;
		for(int i=0;i<=n;i++) {
			x[i]=i;
		}
	}
	/**
	 * 回溯法求解
	 * @param t
	 */
	public void backtrack(int t) {
		System.out.println("backtrack t:"+t);
		System.out.println("当前线路x:");
		for(int i=0;i<t;i++)System.out.print(x[i]+" ");
		System.out.println("当前费用cc:"+cc);
		//t初值为2
		if (t > n) {
			bestc = cc;
			System.out.println("最低费用bestc更新:"+bestc);
			bestx = x.clone();
		} else {
			for (int j = t; j <= n; j++) {
				// 考虑还没有走过的结点
				if (check(x, j, t, a, n)==1) {
					// 有路可走
					swap(x,t, j);
					if (t < n && cc + a[x[t - 1]][x[t]] < bestc) {
						// 加入该结点后的代价比当前最小代价还小，替换
						cc = cc + a[x[t - 1]][x[t]];
						// 继续深入搜索
						System.out.println("继续深入探索,cc:"+cc);
						System.out.println("j:"+j);
						System.out.println("t:"+t);
						backtrack(t + 1);
						// 试探已经结束，把代价回退到替换之前
						cc = cc - a[x[t - 1]][x[t]];
						// 恢复现场
						swap(x,t, j);
						System.out.println("试探结束,代价回退为:"+cc);
					}
					if (t == n && cc + a[x[t - 1]][x[t]] + a[x[n]][1] < bestc) {
						//到最后一个结点了，需要回到起始结点
						cc = cc + a[x[t - 1]][x[t]] + a[x[n]][1];
						backtrack(t + 1);
						//试探已经结束，把代价回退到替换之前
						cc = cc - a[x[t - 1]][x[t]] - a[x[n]][1];
						System.out.println("试探结束,代价回退为:"+cc);
						// 恢复现场
						swap(x,t, j);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param x 当前路径
	 * @param j 第j座城市
	 * @param t 第t座城市
	 * @param a 邻接矩阵
	 * @param n 城市数目
	 * @return 是否可达
	 */
	public int check(int[] x,int j, int t, int[][] a, int n) {
		if (t < 2)
			return 1;
		if (t < n && a[x[t-1]][x[t]] != NoEdge)
			return 1;
		if (t == n && a[x[t-1]][x[t]] != NoEdge && a[x[t]][x[1]] != NoEdge)
			return 1;
		
		return 0;
	}

	/**
	 * 交换位置
	 * @param x 第x座城市
	 * @param y 第y座城市
	 */
	public void swap(int[]a,int x, int y) {
		int t = a[x];
		a[x] = a[y];
		a[y] = t;
	}
	
	/**
	 * 输出结果到控制台
	 */
	public void show() {
		System.out.println("回溯法");
		System.out.println("best cost is:"+bestc);
		System.out.println("best path is:");
		for(int i=1;i<=n;i++) {
			System.out.print(bestx[i]+" ");
		}
		System.out.println();
	}
}
