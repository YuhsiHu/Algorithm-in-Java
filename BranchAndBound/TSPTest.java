import java.util.Random;

public class TSPTest {

	TSPTest() {
		//randomTest();
		manualTest();
	}

	/**
	 * 随机数生成数据测试
	 */
	public void randomTest() {
		TSPByBacktrack tspByBacktrack = new TSPByBacktrack();
		TSPByBranchAndBound tspByBranchAndBound = new TSPByBranchAndBound();
		
		Random random =new Random();
		int m=Integer.MAX_VALUE;
		
		/**
		 * 测试回溯法
		 */
		/**
		 * 5
		 */
		long startTime=System.currentTimeMillis();
		int [][] a=new int [6][];
		for(int i=0;i<6;i++) {
			a[i]=new int[6];
			for(int j=0;j<6;j++) {
				if(i==0||j==0) {
					a[i][j]=m;
				}else if(i==j){
					a[i][j]=0;
				}else {
					a[i][j]=random.nextInt(10);
				}
			}
		}
		for(int q=0;q<6;q++)
			for(int p=0;p<6;p++) {
				if(p==5) 
				{
					System.out.println(a[q][p]);
				
				}else System.out.print(a[q][p]+" ");
			}
		tspByBacktrack.initializeMap(a, 5);
		tspByBacktrack.backtrack(2);
		tspByBacktrack.show();
		
		/**
		 * 10
		 */
		int [][] b=new int [11][];
		for(int i=0;i<11;i++) {
			b[i]=new int[11];
			for(int j=0;j<11;j++) {
				if(i==0||j==0) {
					b[i][j]=m;
				}else {
					b[i][j]=random.nextInt(10);
				}
			}
		}
		tspByBacktrack.initializeMap(b, 10);
		tspByBacktrack.backtrack(2);
		tspByBacktrack.show();
		
		long endTime=System.currentTimeMillis();
		System.out.println("程序运行时间："+(endTime-startTime)+"ms");
		/**
		 * 测试分支限界法
		 */
		/**
		 * 5
		 */
		startTime=System.currentTimeMillis();
		float [][] a1=new float [6][];
		for(int i=0;i<6;i++) {
			a1[i]=new float[6];
			for(int j=0;j<6;j++) {
				if(i==0||j==0) {
					a1[i][j]=m;
				}else if(i==j){
					a1[i][j]=0;
				}else {
					a1[i][j]=Float.valueOf(random.nextInt(10));
				}
			}
		}
		tspByBranchAndBound.initializeMap(a1, 5);
		int []v=new int[5+1];
		tspByBranchAndBound.TSPByBranchAndBound(v);
		System.out.println("分支限界法");
		System.out.println("最短回路长为："+tspByBranchAndBound.TSPByBranchAndBound(v));
		System.out.print("最短回路为：");
		for(int i=1;i<=5;i++){
			System.out.print(v[i]+" ");
		}
		System.out.println();
		/**
		 * 10
		 */
		float [][] b1=new float [11][];
		for(int i=0;i<11;i++) {
			b1[i]=new float[11];
			for(int j=0;j<11;j++) {
				if(i==0||j==0) {
					b1[i][j]=m;
				}else if(i==j){
					b1[i][j]=0;
				}else {
					b1[i][j]=Float.valueOf(random.nextInt(10));
				}
			}
		}
		tspByBranchAndBound.initializeMap(b1, 10);
		int []v1=new int[10+1];
		tspByBranchAndBound.TSPByBranchAndBound(v1);
		System.out.println("分支限界法");
		System.out.println("最短回路长为："+tspByBranchAndBound.TSPByBranchAndBound(v1));
		System.out.print("最短回路为：");
		for(int i=1;i<=10;i++){
			System.out.print(v1[i]+" ");
		}
		System.out.println();
		
		/**
		 * 15
		 */
		float [][] c1=new float [16][];
		for(int i=0;i<16;i++) {
			c1[i]=new float[16];
			for(int j=0;j<16;j++) {
				if(i==0||j==0) {
					c1[i][j]=m;
				}else if(i==j){
					c1[i][j]=0;
				}else {
					c1[i][j]=Float.valueOf(random.nextInt(10));
				}
			}
		}
		tspByBranchAndBound.initializeMap(c1, 15);
		int []v2=new int[15+1];
		tspByBranchAndBound.TSPByBranchAndBound(v2);
		System.out.println("分支限界法");
		System.out.println("最短回路长为："+tspByBranchAndBound.TSPByBranchAndBound(v2));
		System.out.print("最短回路为：");
		for(int i=1;i<=15;i++){
			System.out.print(v2[i]+" ");
		}
		System.out.println();
		
		
		endTime=System.currentTimeMillis();
		System.out.println("程序运行时间："+(endTime-startTime)+"ms");

	}

	/**
	 * 手动测试数据
	 */
	public void manualTest() {
		TSPByBacktrack tspByBacktrack = new TSPByBacktrack();
		TSPByBranchAndBound tspByBranchAndBound = new TSPByBranchAndBound();
		int m=Integer.MAX_VALUE;
		
		/**
		 * 回溯法
		 */
		int[][] a = { {m,m,m,m,m},{m, 0, 6, 7, 9 }, {m, 8, 0, 9, 7 }, {m, 5, 8, 0, 8 }, {m, 6, 5, 5, 0 } };// 4 cities, best cost=23, path=1243
		int cities = 4;
//		tspByBacktrack.initializeMap(a, cities);
//		tspByBacktrack.backtrack(2);
//		tspByBacktrack.show();
		
		TSPByBacktrack tspByBacktrack1 = new TSPByBacktrack();
		int[][] a1={{m,m,m,m,m},{m,0,30,6,4},{m,30,0,5,10},{m,6,5,0,20},{m,4,10,20,0}};
		tspByBacktrack1.initializeMap(a1, cities);
		tspByBacktrack1.backtrack(2);
		tspByBacktrack1.show();
		
		/**
		 * 分支限界法
		 */
		int cities1=4;
		float[][] b={{0,0,0,0,0},{0,-1,30,6,4},{0,30,-1,5,10},{0,6,5,-1,20},{0,4,10,20,-1}};//4 cities, best cost=25, path=1342
		tspByBranchAndBound.initializeMap(b, cities1);
		int []v=new int[cities1+1];
		tspByBranchAndBound.TSPByBranchAndBound(v);
		System.out.println("分支限界法");
		System.out.println("最短回路长为："+tspByBranchAndBound.TSPByBranchAndBound(v));
		System.out.print("最短回路为：");
		for(int i=1;i<=cities1;i++){
			System.out.print(v[i]+" ");
		}

	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TSPTest tspTest = new TSPTest();
	}
}
