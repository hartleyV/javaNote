package algorithm.JZoffer;

import algorithm.utils.ListNode;

/**
 输入一个链表，输出该链表中倒数第k个结点。
 * @author Hartley
 * date： 2020/8/13
 */
public class _14FindLinkedList_KthToTail {
    public ListNode FindKthToTail(ListNode head, int k) {
        //两个指针距离k-1 第一个快指针到null时，第二个指针就是倒数第k个啦
        if(head==null || k<=0) return null;//情况要考虑周全，k的取值

        ListNode fast=head,slow=head;
        for(int i=1;i<k;i++){
            fast = fast.next;
            if(fast==null) return null;
        }

        while(fast.next!=null){
            //注意此处边界 1 2 3 null （k=2 fast先走到2，当fast走到3时，slow就是倒数第k个了
            fast =fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
