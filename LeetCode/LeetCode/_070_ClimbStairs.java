package LeetCode;
/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * 
 * @author Hu Yuxi
 *
 */
public class _070_ClimbStairs {
	
	public int climbStairs(int n) {
        if (n == 1) {
           return 1;
       }
       int first = 1;
       int second = 2;
       for (int i = 3; i <= n; i++) {
           int third = first + second;
           first = second;
           second = third;
       }
       return second;
   }

}
