package LeetCode._002_AddTwoNumbers;
/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例:
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * 
 * Solution
 * @author Hu Yuxi
 *
 */
public class _002_AddTwoNumbers {

	/**
	 * 
	 * @param l1 link list 1
	 * @param l2 link list 2
	 * @return result
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultList = new ListNode(0);
        int cache = 0;//进位
        
        ListNode l3 = resultList;
        
        while (l1 != null || l2 != null || cache > 0){
            int l1Val = l1 == null ? 0 : l1.val;
            int l2Val = l2 == null ? 0 : l2.val;
            int l3Val = l1Val + l2Val + cache;
            cache = 0;
            
            // 判断是否大于 9 大于9 进一位
            if (l3Val >  9){
                cache = 1;
                l3Val = l3Val - 10;
            }
            //添加结果
            l3.next = new ListNode(l3Val);
            //下一个结点
            l3 = l3.next;
            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;
        }
        
        return resultList.next;
    }
}
