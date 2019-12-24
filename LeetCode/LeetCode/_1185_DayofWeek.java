package LeetCode;

public class _1185_DayofWeek {

	/**
	 * 日期在1971-2100年
	 * 
	 * @param day day
	 * @param month month
	 * @param year year
	 * @return day of the week
	 */
	public String dayOfTheWeek(int day, int month, int year) {
		// 列出返回结果
		String days[] = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		// 列出每月天数
		int months[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int start = 4;// 1971年1月1第一天星期五,所以从星期四开始
		int sum = 0;

		//计算从1971.1.1到当前年经过了多少天
		for (int i = 1971; i < year; ++i) {
			sum += daysOfyear(i);
		}

		//计算当前年到当前月经过了多少天
		for (int i = 1; i < month; ++i) {
			if (i == 2 && isLeapYear(year)) {
				sum += 29;
			} else {
				sum += months[i - 1];
			}
		}

		//计算当前月到当前日期经过了多少天
		sum += day;
		
		//计算周几
		sum = sum % 7;
		int now = (start + sum) % 7;
		return days[now];
	}

	/**
	 * 判断是不是闰年
	 * 
	 * @param year
	 * @return
	 */
	private boolean isLeapYear(int year) {
		return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
	}

	/**
	 * 一年有多少天，闰年366，平年365
	 * 
	 * @param year
	 * @return
	 */
	private int daysOfyear(int year) {
		return isLeapYear(year) ? 366 : 365;
	}

}
