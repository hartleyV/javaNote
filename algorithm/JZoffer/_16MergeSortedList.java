package algorithm.JZoffer;

import algorithm.utils.ListNode;

/**
 * 合并两个有序链表
 输入两个单调递增的链表，输出两个链表合成后的链表，
 当然我们需要合成后的链表满足单调不减规则
 * @author Hartley
 * date： 2020/8/13
 */
public class _16MergeSortedList {

    //[1]解法1 迭代解
    public ListNode Merge(ListNode list1, ListNode list2) {
        //直接依次遍历，谁小谁走，
        ListNode head = new ListNode(-1);//初始化，后面直接返回他下一个节点即可
        ListNode cur = head;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }

            cur = cur.next;//cur下一项
        }

        //谁不为空指向谁
        cur.next = list1 != null ? list1 : list2;

        return head.next;
    }

    //[2]解法二 递归解

    public ListNode helper(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else {
            if (list1.val <= list2.val) {
                list1.next = helper(list1.next, list2);//当成已知
                return list1;
            } else {
                list2.next = helper(list1, list2.next);
                return list2;
            }
        }
    }
}
