package Backtracking;

import java.util.Scanner;

/**
 * 世界名画陈列馆问题。
 * 世界名画陈列馆由m×n个排列成矩形阵列的陈列室组成。
 * 为了防止名画被盗，需要在陈列室中设置警卫机器人哨位。
 * 每个警卫机器人除了监视它所在的陈列室外，还可以监视与它所在的陈列室相邻的上、下、左、右4个陈列室.
 * 试设计一个安排警卫机器人哨位的算法，使得名画陈列馆中每一个陈列室都在且仅仅在一个警卫机器人的监视之下，且所用的警卫机器人数最少。
 * 不重复监视
 * 
 * @author Hu Yuxi
 * @date 2018-12-17
 *
 */
public class Monitor {
	 /**
     * 声明一个int类型的二维数组
     */
    static int[][] matrix;

    /**
     * 初始化二维数组，运算过程中未被监视为0，监视了为1，放置了警卫为2
     * @param length
     * @param width
     */
    public static int[][] initializeMatrix(int length,int width){
        matrix = new int[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = 0;
            }
        }
        return matrix;
    }

    /**
     * 判断此位置是否可以添加警卫
     * @param i
     * @param j
     * @return
     */
    public static boolean canAdd(int i,int j){
    	//该位置上下左右都应该没有被监视
        if (matrix[i][j] == 0 && (i-1 < 0 || matrix[i - 1][j] != 1) && (j-1 < 0 ||
                matrix[i][j - 1] != 1) && (i+1 >= matrix.length || matrix[i + 1][j] != 1)  &&
                (j+1 >= matrix[i].length || matrix[i][j + 1] != 1)){
            return true;
        }
        return false;
    }

    /**
     * 设置警卫并且改变周围监视状况
     * @param i
     * @param j
     */
    public static void set(int i,int j){
        // 此位置放置警卫
        matrix[i][j] = 2;
        //改变放置警卫能侦测到位置的值
        //上
        if (i > 0 && matrix[i - 1][j] == 0)	{
            matrix[i - 1][j] = 1;
        }
        //左
        if (j > 0 && matrix[i][j - 1] == 0){
            matrix[i][j - 1] = 1;
        }
        //右
        if (j+1 < matrix[i].length && matrix[i][j + 1] == 0)	{
            matrix[i][j + 1] = 1;
        }
        //下
        if (i+1 < matrix.length && matrix[i + 1][j] == 0)	 {
            matrix[i + 1][j] = 1;
        }
    }

    /**
     * 检查是否全部监视
     * @param length
     * @param width
     * @return
     */
    public static boolean check(int length,int width){
        for (int i = 0; i < length;++i){
            for (int k = 0; k < width; ++k){
                if (matrix[i][k] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 全部回退
     * @param length
     * @param width
     */
    public static void goBack(int length,int width){
        for (int i = 0; i < length; ++i){
            for (int k = 0; k < width; ++k){
                matrix[i][k] = 0;
            }
        }
    }

    /**
     * 警卫位置的填入
     * @param x
     * @param y
     * @param length
     * @param width
     */
    public static void insert(int x,int y,int length,int width){
        //判断此位置是否越界
        if (x >= length || y >= width){
            return;
        }
        //判断此位置是否合适放置
        if (canAdd(x, y)){
            set(x, y); //如果合适，设置警卫并且改变相应的值
        }
        //到下一行接着放置
        if (y == width-1){
            insert(x+1,0,length,width);
        }else{
            insert(x,y+1,length,width);
        }
    }

    /**
     * 逐一添置警卫
     * @param length
     * @param width
     */
    public static void insert1(int length,int width){
        for (int i = 0; i < length;++i){
            for (int j = 0; j < width;++j){
                //往i,j位置添加
                insert(i,j,length,width);
                //判断是否填满
                if (check(length,width)){
                    break;
                }else {
                    //回退
                    goBack(length, width);
                }
            }
        }
    }
    
    /**
     * 统计需要警卫个数
     * 打印二维数组元素
     */
    public static void show2(){
    	int monitor=0;
        for(int i = 0;i < matrix.length;i++) {
            for(int j = 0;j < matrix[i].length;j++) {
            	if(matrix[i][j]==2) {
            		monitor++;
            	}else if(matrix[i][j]==0) {
            		System.out.println("No solution!");
            		return;
            	}
                System.out.print(matrix[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("需要警卫数："+monitor);
        return;
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
    	//无限读取陈列馆信息
        for(;;) {
            System.out.println("设置陈列馆:");
            System.out.print("行数m: ");
            Scanner sc1 = new Scanner(System.in);
            int m = Integer.parseInt(sc1.next());
            System.out.print("列数n: ");
            sc1 = new Scanner(System.in);
            int n = Integer.parseInt(sc1.next());
            initializeMatrix(m, n);
            insert1(m, n);
            show2();
        }
    }
}
