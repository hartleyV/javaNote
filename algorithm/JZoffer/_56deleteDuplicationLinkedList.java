package algorithm.JZoffer;

import algorithm.utils.ListNode;

/**
 在一个排序的链表中，存在重复的结点，
 请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 * @author Hartley
 * date： 2020/8/13
 */
public class _56deleteDuplicationLinkedList {

    //不用空间的，还没有捋明白。。
    public ListNode deleteDuplication(ListNode pHead)
    {
        if(pHead==null || pHead.next==null) return pHead;

        //为了创建pre哨兵，给原链表加个头
        ListNode head = new ListNode(-1);
        head.next = pHead;

        ListNode pre = head,cur = pHead;

        while(cur!=null){
            //捋不清就加层判断，避免null
            if(cur.next!=null && cur.val==cur.next.val){//跳出条件时cur为原来重复的节点
                while(cur.next!=null && cur.val==cur.next.val){
                    cur = cur.next;
                }

                pre.next = cur.next;//所以让pre指向cur的下一个
                cur = cur.next;
            }else{
                pre = cur;
                cur = cur.next;
            }
        }

        return head.next;
    }
}
