import java.util.Collections;
import java.util.LinkedList;
/**
 * 
 * @author Hu Yuxi
 * @date 2018-12-22
 *
 */
public class TSPByBranchAndBound {

	int n;//城市数目
	float[][] a;// 城市的邻接矩阵, n座城市矩阵为[n-1][n-1]
	int [] v=new int[n];
	float bestc=Float.MAX_VALUE;

	/**
	 * 无参构造函数
	 */
	public TSPByBranchAndBound() {

	}

	/**
	 * 
	 * @param a
	 *            城市邻接矩阵
	 */
	public void initializeMap(float[][] a,int cities) {
		n=cities;
		this.a = a;
	}

	/**
	 * 
	 * 结点类
	 *
	 */
	public static class HeapNode implements Comparable {
		float lcost;// 子树费用的下界
		float cc;// current cost, 当前费用
		float rcost;// x[s:n-1]中顶点最小出边费用和
		int s;// 根节点到当前节点的路径为x[0:s]
		int[] x;// 需要进一步搜索的顶点是x[s+1:n-1]

		/**
		 * 构造方法
		 * 
		 * @param lc
		 * @param ccc
		 * @param rc
		 * @param ss
		 * @param xx
		 */
		public HeapNode(float lc, float ccc, float rc, int ss, int[] xx) {
			lcost = lc;
			cc = ccc;
			s = ss;
			x = xx;
		}

		/**
		 * 比较结点
		 */
		public int compareTo(Object x) {
			float xlc = ((HeapNode) x).lcost;
			if (lcost < xlc)
				return -1;
			if (lcost == xlc)
				return 0;
			return 1;
		}
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	public float TSPByBranchAndBound(int[] v) {
		int n = v.length - 1;// 节点数
		LinkedList<HeapNode> heap = new LinkedList<HeapNode>();
		// minOut[i]=i的最小出边费用
		float[] minOut = new float[n + 1];
		float minSum = 0;// 最小出边费用和
		
		for (int i = 1; i <= n; i++) {// 针对每个节点，找到最小出边
			// 计算minOut[i]和minSum
			float min = Float.MAX_VALUE;
			for (int j = 1; j <= n; j++) {
				if (a[i][j] < Float.MAX_VALUE && a[i][j] < min)
					min = a[i][j];
			}
			if (min == Float.MAX_VALUE)
				return Float.MAX_VALUE;
			minOut[i] = min;
			minSum += min;
		}

		// 初始化
		int[] x = new int[n];
		for (int i = 0; i < n; i++)
			x[i] = i + 1;
		HeapNode enode = new HeapNode(0, 0, minSum, 0, x);
		float bestc = Float.MAX_VALUE;

		// 搜索排列空间树
		while (enode != null && enode.s < n - 1) {
			// 非叶节点
			x = enode.x;
			if (enode.s == n - 2) {
				// 当前扩展结点是叶节点的父节点
				// 再加两条边构成回路
				// 所构成回路是否优于当前最优解
				if (a[x[n - 2]][x[n - 1]] != -1 && a[x[n - 1]][1] != -1
						&& enode.cc + a[x[n - 2]][x[n - 1]] + a[x[n - 1]][1] < bestc) {
					// 找到费用更小的回路
					bestc = enode.cc + a[x[n - 2]][x[n - 1]] + a[x[n - 1]][1];
					enode.cc = bestc;
					enode.lcost = bestc;
					enode.s++;
					heap.add(enode);
					Collections.sort(heap);
				}
			} else {// 内部结点
					// 产生当前扩展结点的儿子结点
				for (int i = enode.s + 1; i < n; i++) {
					if (a[x[enode.s]][x[i]] != -1) {
						// 可行儿子结点
						float cc = enode.cc + a[x[enode.s]][x[i]];
						float rcost = enode.rcost = minOut[x[enode.s]];
						float b = cc + rcost;// 下界
						if (b < bestc) {
							// 子树可能含有最优解，结点插入最小堆
							int[] xx = new int[n];
							for (int j = 0; j < n; j++)
								xx[j] = x[j];
							xx[enode.s + 1] = x[i];
							xx[i] = x[enode.s + 1];
							HeapNode node = new HeapNode(b, cc, rcost, enode.s + 1, xx);
							heap.add(node);
							Collections.sort(heap);
						}
					}
				}
			}
			// 取下一个扩展结点
			enode = heap.poll();
		}
		// 将最优解复制到v[1...n]
		for (int i = 0; i < n; i++)
			v[i + 1] = x[i];
		return bestc;
	}

	public void show() {
		System.out.println("best cost is:"+bestc);
		System.out.println("best path is:");
		for(int i=1;i<=n;i++) {
			System.out.print(v[i]+" ");
		}
	}
}
