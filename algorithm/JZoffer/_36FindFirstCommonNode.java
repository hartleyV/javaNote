package algorithm.JZoffer;

import algorithm.utils.ListNode;

/**
 输入两个链表，找出它们的第一个公共结点。
 //该解题没有考虑环的情况
    如果考虑则：1.两个无环=双指针解法
              2.两个有环=a.共入口 找头到入口的部分 -减去长度，遍历
                        b.不共入口，遍历其中的入口走一遍，可否撞到另一个入口节点
 * @author Hartley
 * date： 2020/8/15
 */
public class _36FindFirstCommonNode {

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {

        /**/
        //双指针法，（不适用没有公共节点的两个环）

        //法二：想办法补齐长度
        //eg. p1 1-2-3 p2 2-3 （如果相交 尾节点必然一致：因为节点只有一个next指针）
        ListNode cur1 = pHead1,cur2 = pHead2;
        while(cur1!=cur2){
            cur1 = cur1!=null?cur1.next:pHead2;//相当于连成了{1-2-3}- 2 -3-null
            cur2 = cur2!=null?cur2.next:pHead1;//{2-3}-1- 2 -3-null
        }
        return cur1;
    }

    //有公共节点是尾节点肯定一致，减去多余长度
    public ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
        ListNode cur1 = pHead1,cur2 = pHead2;
        //没有环，则最后一个节点一定为公共节点，统计一下链表的长度
        int len1 = 0 ,len2 = 0,length=0;
        while(cur1!=null){
            len1++;
            cur1 = cur1.next;
        }
        while(cur2!=null){
            len2++;
            cur2 = cur2.next;
        }

        if(len1>len2){
            len1 -=len2;
            while(len1--!=0 ){
                pHead1 = pHead1.next;
            }
        }else{
            len2-=len1;
            while(len2--!=0 ){
                pHead2 = pHead2.next;
            }
        }
        while(pHead1!=pHead2){
            pHead1 = pHead1.next;
            pHead2 = pHead2.next;
        }

        return pHead1;
    }



}
