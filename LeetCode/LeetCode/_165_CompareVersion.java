package LeetCode;

/**
 * 比较两个版本号 version1 和 version2。 如果 version1 > version2 返回 1，如果 version1 <
 * version2 返回 -1， 除此之外返回 0。 你可以假设版本字符串非空，并且只包含数字和 . 字符。 . 字符不代表小数点，而是用于分隔数字序列。
 * 例如，2.5 不是“两个半”，也不是“差一半到三”，而是第二版中的第五个小版本。 你可以假设版本号的每一级的默认修订版号为 0。 例如，版本号 3.4
 * 的第一级（大版本）和第二级（小版本）修订号分别为 3 和 4。其第三级和第四级修订号均为 0。
 * 
 * @author Hu Yuxi
 *
 */
public class _165_CompareVersion {
	/**
	 * 
	 * @param version1
	 * @param version2
	 * @return 如果 version1 > version2 返回 1,如果 version1 < version2 返回 -1,除此之外返回 0
	 */
	public int compareVersion(String version1, String version2) {
		String[] array1 = version1.split("\\.");
		String[] array2 = version2.split("\\.");

		int i = 0;
		// 逐段化为整数比较
		for (i = 0; i < array1.length && i < array2.length; i++) {
			Integer num1 = Integer.parseInt(array1[i]);
			Integer num2 = Integer.parseInt(array2[i]);
			if (num1 < num2) {
				return -1;
			} else if (num1 > num2) {
				return 1;
			}
		}
		// 比到这了,前面的版本号都一样,还有一个版本的版本号后面有剩余
		while (i < array1.length) {
			Integer num1 = Integer.parseInt(array1[i]);
			if (num1 != 0) {
				return 1;
			}
			i++;
		}

		while (i < array2.length) {
			Integer num2 = Integer.parseInt(array2[i]);
			if (num2 != 0) {
				return -1;
			}
			i++;
		}
		return 0;
	}

}
