package TSPByGeneticAlgorithm;

import java.util.Random;

/**
 * 遗传算法
 * 
 * @author Hu Yuxi
 * @date 2019-01-04
 * 
 */
public class GA {

	// 确定种群规模、迭代次数、个体选择方式、交叉概率、变异概率等
	private static final int entitysize = 100;// 种群规模
	private static final int p = 100;// 迭代次数
	private static final double p_bianyi = 0.05;// 变异概率
	private static final double p_jiaopei = 0.8;// 交配概率
	private static final int citynum = 30;// 城市数量
	private Distance weight_distance;
	private GAEntity[] gaEntity;// 遗传算法个体的集合数组
	private GAEntity[] tempEntity;// 轮盘赌时暂存解
	private double all_ability;// 所有个体路径总长度
	private MatchTable matchTable;
	private GAEntity bestEntity;// 最好的个体
	private double shortestRoad;// 最短路径

	// 初始化城市（随机产生城市坐标）
	private void Init_Distance() {
		weight_distance = new Distance(citynum, true);
	}

	// 初始化种群(随机产生100种的路径),暂不检测路径间的重复问题,因为重复的可能太小
	private void Init_GAEntity() {
		gaEntity = new GAEntity[entitysize];
		tempEntity = new GAEntity[entitysize];
		for (int i = 0; i < entitysize; i++) {
			gaEntity[i] = new GAEntity(citynum, "");
			System.out.println("初始种群" + i + ":" + gaEntity[i].printRoad());
		}
	}

	/**
	 * 当P<迭代次数时
	 */
	// 计算染色体适应度值(每个染色体的路径总和）和幸存程度
	private void Cal_AdaptabilityAndLucky() {
		all_ability = 0.0;
		double all_lucky = 0.0;

		// 计算所有解路径长度总和
		for (int i = 0; i < entitysize; i++) {
			all_ability += gaEntity[i].cal_Adaptability();
		}

		// 计算所有解总幸存值
		for (int i = 0; i < entitysize; i++) {
			// 幸存程度,路径越短幸存程度越高,为轮盘赌做准备
			all_lucky += gaEntity[i].cal_preLucky(all_ability);
		}

		// 归一化,计算每个个体的幸存概率
		for (int i = 0; i < entitysize; i++) {
			gaEntity[i].cal_Lucky(all_lucky);
		}

	}

	/**
	 * 轮盘赌选择
	 */
	private void chooseSample() {
		// 按某个选择概率选择样本,使用轮盘赌选择法，根据幸存程度选择,本质是重构解空间，解空间样本可重复
		double p = 0.0;
		double all_prelucky = 0.0;

		// 轮盘赌将tempEntity填满
		for (int i = 0; i < entitysize; i++) {
			p = Math.random();// 产生0到1之间的随机数
			all_prelucky = 0.0;// 置0重新开始计算
			tempEntity[i] = gaEntity[entitysize - 1];// 提高精确度

			// TODO 如何将匹配单个个体？
			for (int j = 0; j < entitysize; j++) {
				all_prelucky += gaEntity[j].getP_lucky();
				// 如果随机数p比幸存概率累加至目前的和要小就留下这个解
				if (p <= all_prelucky) {
					tempEntity[i] = gaEntity[j];
					break;
				}
			}
		}
		// 更新解空间
		for (int i = 0; i < entitysize; i++) {
			gaEntity[i] = null;
			gaEntity[i] = tempEntity[i];
		}

	}

	/**
	 * 个体交叉,采用部分匹配法
	 */
	private void Mating() {
		double mating[] = new double[entitysize];// 染色体的交配概率
		boolean matingFlag[] = new boolean[entitysize];// 染色体的可交配情况
		boolean findMating1 = false;
		Random random = new Random();
		matchTable = new MatchTable(citynum);
		int mating1 = 0;
		int mating2 = -1;// 指示当前交配的两个对象
		int position1, position2;// 指示交换位置
		int matingnum = 0;
		// 随机产生交配概率,确定可交配的染色体
		for (int i = 0; i < entitysize; i++) {
			mating[i] = Math.random();
			if (mating[i] < p_jiaopei) {
				// 可交配
				matingFlag[i] = true;
				matingnum++;
			} else {
				matingFlag[i] = false;
			}
		}
		// 交叉准备工作已完毕
		matingnum = matingnum / 2 * 2;// 参与交配的染色体数应该是偶数
		for (int i = 0; i < matingnum / 2; i++) {
			findMating1 = false;
			// 随机找一段做交换区域
			position1 = random.nextInt(citynum);
			position2 = random.nextInt(citynum);
			if (position1 <= position2) {
				// do nothing
			} else {
				// 保证position1<position2
				int t = position1;
				position1 = position2;
				position2 = t;
			}
			// 寻找两个可交配的染色体
			for (mating2++; mating2 < entitysize; mating2++) {
				// 开始寻找两个可交配的染色体
				if (matingFlag[mating2]) {
					if (findMating1) {
						break;// 已经找到mating1和mating2
					} else {
						// 找到mating1,继续循环找mating2
						mating1 = mating2;
						findMating1 = true;
					}
				}
			}
			// 这两个染色体进行交配（部分匹配法）
			// gaEntity[mating1]和gaEntity[mating2]在position1和position2上进行交叉
			// 构建匹配表
			matchTable.setTable(gaEntity[mating1], gaEntity[mating2], position1, position2);
			// 进行交叉操作
			GAEntity tempGaEntity1 = new GAEntity(citynum);// 子代1
			GAEntity tempGaEntity2 = new GAEntity(citynum);// 子代2

			if (!gaEntity[mating1].checkdifference(gaEntity[mating2])) {
				// 两个子代毫无差别,直接留下
				tempGaEntity1 = gaEntity[mating1];
				tempGaEntity2 = gaEntity[mating2];
			} else {
				// 交叉变换
				tempGaEntity1.setRoad(gaEntity[mating2], position1, position2);
				tempGaEntity2.setRoad(gaEntity[mating1], position1, position2);
				tempGaEntity1.modifyRoad(gaEntity[mating1], position1, position2, matchTable, true);
				tempGaEntity2.modifyRoad(gaEntity[mating2], position1, position2, matchTable, false);
			}

			// 结束插入首尾值
			gaEntity[mating1] = tempGaEntity1;
			gaEntity[mating2] = tempGaEntity2;
		}

	}

	/**
	 * 个体变异,采用简单的交换变异
	 */
	private void Variating() {
		double rating[] = new double[entitysize];// 染色体的变异概率
		boolean ratingFlag[] = new boolean[entitysize];// 染色体的可变异情况
		Random random = new Random();
		int position1, position2;// 指示交换位置
		// 随机产生变异概率,确定可变异的染色体
		for (int i = 0; i < entitysize; i++) {
			rating[i] = Math.random();
			if (rating[i] < p_bianyi) {
				ratingFlag[i] = true;
			} else {
				ratingFlag[i] = false;
			}
		}
		// 开始变异
		for (int i = 0; i < entitysize; i++) {
			if (ratingFlag[i]) {
				position1 = 0;
				position2 = 0;
				while (position1 == position2) {
					position1 = random.nextInt(citynum);
					position2 = random.nextInt(citynum);
				}
				// 单点变异,两位置交换次序
				gaEntity[i].exchange(position1, position2);
			}
		}
	}

	/**
	 * 每次迭代之后在这一代选一个最好的路径
	 */
	private void ChooseBestSolution(Boolean initBest) {
		Double roadLength = Double.MAX_VALUE;
		int bestRoad = 0;
		for (int i = 0; i < entitysize; i++) {
			// 逐个查找,寻找最好路径
			if (roadLength > gaEntity[i].getAdaptability()) {
				roadLength = gaEntity[i].getAdaptability();
				bestRoad = i;
			}
		}

		System.out.println("该次迭代最好的路径：" + gaEntity[bestRoad].printRoad());
		System.out.println("该次迭代最低消耗：" + roadLength);

		// 如果是最后一代,得出的就是最终解了
		if (initBest) {
			shortestRoad = roadLength;
			bestEntity = gaEntity[bestRoad];
		} else if (shortestRoad > roadLength) {
			// 如果不是最后一代,也需要判断是否更新
			shortestRoad = roadLength;
			bestEntity = gaEntity[bestRoad];
		}
	}

	/**
	 * 遗传算法迭代过程
	 */
	private void Iterator() {
		long startTime=System.currentTimeMillis();
		Init_Distance();// 初始化城市
		Init_GAEntity();// 初始化路径解
		boolean initBest = true;// 初始化,true时候代表还没到最后一代
		// 迭代100次
		for (int i = 0; i < p; i++) {
			System.out.println("第" + i + "次迭代：");
			Cal_AdaptabilityAndLucky();// 计算适应度并算出每个个体幸存概率
			ChooseBestSolution(initBest);// 选择当代最优解,true表示还在迭代过程中
			initBest = false;
			chooseSample();// 轮盘赌
			Mating();// 交叉产生子代
			Variating();// 子代变异
		}

		Cal_AdaptabilityAndLucky();// 计算适应度
		ChooseBestSolution(false);// 选当代最优解,false表示是最后一代了
		System.out.println("最好的路径：" + bestEntity.printRoad());
		System.out.println("最低消耗：" + shortestRoad);
		long endTime=System.currentTimeMillis();
		System.out.println("消耗时间:"+(endTime-startTime)+"ms");
	}

	// 打印适应度最高的解
	public static void main(String[] args) {
		GA ga = new GA();
		ga.Iterator();
	}
}
