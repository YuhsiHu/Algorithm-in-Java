import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 遗传算法个体类，采取基于路径的编码方式
 * @author Hu Yuxi
 * @date 2019-01-04
 */
public class GAEntity {

	private static Integer[] initRoad = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
			18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29 };// 用于生成30个城市随机解的母本
	private int num;// 城市个数
	private List<Integer> roadlist;//路径的解
	private Integer[] road;
	private double adaptability = 0.0;// 个体适应度
	private double p_lucky = 0.0;// 幸存概率

	/**
	 * Initialize
	 * @param n 城市数目
	 */
	GAEntity(int n) {
		num = n;
		roadlist = new ArrayList<Integer>();
		road = new Integer[num];
	} 
	
	/**
	 * 
	 * @param n
	 * @param s
	 */
	GAEntity(int n, String s) {
		num = n;
		roadlist = new ArrayList<Integer>();
		road = new Integer[num];
		InitRoad();
	}

	/**
	 * 将initRoad打乱获得一个随机解
	 */
	private void InitRoad() {
		roadlist = Arrays.asList(initRoad);//把initRoad转为List
		Collections.shuffle(roadlist);//打乱顺序
		road = (Integer[]) roadlist.toArray();//转回为ArrayList给road
	}

	/**
	 * 把j填到第i个位置
	 * @param i 第i个位置
	 * @param j 城市j
	 */
	public void setRoad(int i, int j) {
		roadlist.set(i, j);
		road[i] = j;
	}

	/**
	 * 返回路径里第i个位置的城市
	 * @param i 第i个位置
	 * @return 城市
	 */
	public int getRoad(int i) {
		return road[i];
	}

	/**
	 * 返回适应度
	 * @return 适应度
	 */
	public double getAdaptability() {
		return adaptability;
	}

	/**
	 * 设置适应度
	 * @param adaptability 适应度
	 */
	public void setAdaptability(double adaptability) {
		this.adaptability = adaptability;
	}

	/**
	 * 返回幸存概率
	 * @return 幸存概率
	 */
	public double getP_lucky() {
		return p_lucky;
	}

	/**
	 * 设置幸存概率
	 * @param p_lucky
	 */
	public void setP_lucky(double p_lucky) {
		this.p_lucky = p_lucky;
	}

	/**
	 * 返回路径+幸存概率字符串
	 * @return 路径+幸存概率
	 */
	public String printRoad() {
		String p = "";
		for (int i = 0; i < num; i++) {
			p += "  " + road[i] + ";";
		}
		p += "幸存概率：" + p_lucky;
		return p;
	}

	/**
	 * 计算适应度
	 * @return 适应度(路径长度)
	 */
	public double cal_Adaptability() {
		adaptability = 0.0;
		for (int i = 0; i < num - 1; i++) {
			//路径上的距离
			adaptability += Distance.getDistance(road[i], road[i + 1]);
		}
		adaptability += Distance.getDistance(road[num - 1], road[0]);//返回出发城市
		return adaptability;
	}

	/**
	 * 计算幸存概率
	 * @param all_ability 种群总适应度(路径长度)，1-个体路径长度/总长度
	 * @return 幸存概率
	 */
	public double cal_preLucky(double all_ability) {
		p_lucky = 1 - adaptability / all_ability;
		return p_lucky;
	}
	
	/**
	 * 计算幸存概率
	 * @param all_lucky 
	 */
	public void cal_Lucky(double all_lucky) {
		p_lucky = p_lucky / all_lucky;
	}

	/**
	 * 继承父代部分路径
	 * @param parent 父代个体
	 * @param position1 起始位置
	 * @param position2 结束位置
	 */
	public void setRoad(GAEntity parent, int position1, int position2) {
		roadlist.clear();
		for (; position1 <= position2; position1++) {
			road[position1] = parent.getRoad(position1);
			roadlist.add(road[position1]);// 当前已有路径统计
		}
	}

	/**
	 * 子代首尾值继承父代
	 * @param parent 父代
	 * @param position1 交叉起始位置
	 * @param position2 交叉结束位置
	 * @param matchTable
	 * @param ifChild1 是不是子代1
	 */
	public void modifyRoad(GAEntity parent, int position1, int position2, MatchTable matchTable, boolean ifChild1) {
		int roadnum;
		boolean ifModify = false;//是否需要更改首尾的值
		if (ifChild1) {// 子代1的查询表应该从父代2开始,最终值落在父代1中
			for (int i = 0; i < num; i++) {
				//position1和position2之间跳过
				if (i >= position1 && i <= position2) {
					i = position2;
					continue;
				}
				
				//对子代1插入首尾值
				//直接获取父代值
				roadnum = parent.getRoad(i);
				//查看这个值是否与当前中间段有重复
				ifModify = checkRoad(roadnum);

				while (ifModify) {
					//需要更改,从父代2对应位置取值
					roadnum = matchTable.getRoadNum(false, roadnum);
					//再次查看是否与当前中间段有重复,事实上可以保证不会重复了
					ifModify = checkRoad(roadnum);
				}
				road[i] = roadnum;
				roadlist.add(roadnum);
			}
			//子代1处理结束
		} else {
			// 子代2的查询表应该从父代1开始
			for (int i = 0; i < num; i++) {
				//position1和position2之间跳过
				if (i >= position1 && i <= position2) {
					i = position2;
					continue;
				}
				//对子代2插入首尾值
				roadnum = parent.getRoad(i);
				ifModify = checkRoad(roadnum);

				while (ifModify) {
					//需要更改,从父代1对应位置取值
					roadnum = matchTable.getRoadNum(true, roadnum);
					// 再次查看是否与当前中间段有重复
					ifModify = checkRoad(roadnum);
				}
				road[i] = roadnum;
				roadlist.add(roadnum);

			}
		}
		// 子代2处理结束
	}

	/**
	 * 
	 * @param roadnum 
	 * @return 包含返回true否则false
	 */
	private boolean checkRoad(int roadnum) {
		if (roadlist.contains(roadnum)) {
			return true;
		}
		return false;
	}

	/**
	 * 交换位置
	 * @param p1 p1位置
	 * @param p2 p2位置
	 */
	public void exchange(int p1, int p2) {
		int t = road[p1];
		road[p1] = road[p2];
		road[p2] = t;
	}

	/**
	 * 比较路径是否有差别
	 * @param g 与之比较的个体
	 * @return 两个解有区别返回true,完全一样返回false
	 */
	public boolean checkdifference(GAEntity g) {
		for (int i = 0; i < num; i++) {
			if (road[i] == g.getRoad(i)) {
				continue;
			} else {
				return true;
			}
		}
		return false;
	}
}
