package Backtracking;

/**
 * 给定n个作业的集合{J1,J2,…,Jn}。每个作业必须先由机器1处理，然后由机器2处理。作业Ji需要机器j的处理时间为tji。
 * 对于一个确定的作业调度，设Fji是作业i在机器j上完成处理的时间。所有作业在机器上完成处理的时间和称为该作业调度的完成时间和。
 * 批处理作业调度问题要求对于给定的n个作业，制定最佳作业调度方案，使其完成时间和达到最小。这属于回溯法中的排列树问题。
 * 
 * 1 3 2 18
 * 
 * @author Hu Yuxi
 * @date 2018-12-17
 *
 */
public class Flowshop {
		 int n;//作业数
		 int[][] m;//各作业所需的处理时间
		 int f1;//机器1完成处理时间
		 int[] f2;//机器2完成处理时间
		 int f;//完成时间和
		 int bestf;//当前最优值
		 int []x;//当前作业调度
		 int[] bestx;//当前最优作业调度
		 
		 /**
		  * initialize
		  * @param n
		  * @param m
		  */
		public Flowshop(int n,int[][] m){
			this.n=n;
			this.m=m;
			f1=0;
			f=0;
			bestf=10000;//给定初始值
			bestx=new int[n+1];
			x=new int[n+1];
			//初始化
			for(int i=1;i<=n;i++){
				x[i]=i;
			}
			f2=new int[n+1];
		}
		
		/**
		 * swap x[i] and x[j]
		 * @param x
		 * @param i
		 * @param j
		 */
		public  void swap(int[] x,int i,int j){
			int temp=x[i];
			x[i]=x[j];
			x[j]=temp;
		}
	 
		/**
		 * 
		 * @param i
		 */
		public  void backtrack(int i){
			if(i>n){
				for(int j=1;j<=n;j++)
					bestx[j]=x[j];
				bestf=f;
			}
			else{
				for(int j=i;j<=n;j++){
					f1=f1+m[x[j]][1];//作业x[j]在第一台机器的时间
					f2[i]=((f2[i-1]>f1)?f2[i-1]:f1)+m[x[j]][2];//f2[i]等于f2[i-1]和f1中较大者加上作业x[j]在第2台机器的时间
					f=f+f2[i];
					if(f<bestf){
						swap(x,i,j);
						backtrack(i+1);
						swap(x,i,j);
					}
					f1=f1-m[x[j]][1];
					f=f-f2[i];
				}
			}
		}
		
		/**
		 * 
		 * @param args
		 */
		public static void main(String[] args) {
			int n=3;
			int[][] m={{0,0,0},{0,2,1},{0,3,1},{0,2,3}};//m的下标从1开始，因此第一行的0和每一行第一列的0无用
			Flowshop f=new Flowshop(n,m);
			f.backtrack(1);
			System.out.println("最优批处理作业调度顺序为：");
			for(int i=1;i<=n;i++)
				System.out.print(f.bestx[i]+" ");
			System.out.println();
			System.out.println("最优调度所需的最短时间为："+f.bestf);
		}
}
