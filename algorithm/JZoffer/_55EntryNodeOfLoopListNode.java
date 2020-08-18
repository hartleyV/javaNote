package algorithm.JZoffer;

import algorithm.utils.ListNode;

/**
 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 * @author Hartley
 * date： 2020/8/15
 */
public class _55EntryNodeOfLoopListNode {
    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        //移动快慢指针（优化）**
        ListNode fast=pHead,slow=pHead;//初始化快慢指针
        //依次移动，快指针两步 慢指针一步。直至1.两指针碰到 或 2.快指针指空
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast==slow) break;
        }
        if(fast==null || fast.next==null) return null;//情况2快指针指空，则直接出去
        fast = pHead;//情况2.快指针指头节点，
        while(fast!=slow){//快慢指针同步走，直至撞到
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
        /*
        //移动快慢指针（自拟）
        //题意为判断有没有环
        if(pHead==null || pHead.next==null || pHead.next.next==null) return null;
        //快慢指针法：fast走两步，slow走一步，碰头后fast回原点，fast与slow一起走，再次碰头即为入口
        ListNode fast=pHead.next.next,slow=pHead.next;
        //有环的话没有null了把？
        while(slow!=null && fast!=null){

            if(fast==slow){
                fast = pHead;//快指针回到头，与慢指针一起走
                while(slow!=null && fast!=null){
                    if(fast==slow){
                        return slow;
                    }else{
                        fast = fast.next;
                        slow = slow.next;
                    }
                }

            }else{
                slow = slow.next;//慢指针走一步
                fast = fast.next.next;//快指针走两步
            }

        }
        return null;
        */

        //空间法（easy
        /*
        HashSet<ListNode> helper = new HashSet<>();

        while(pHead!=null){
            if(helper.contains(pHead)){
                return pHead;
            }else{
                helper.add(pHead);
                pHead = pHead.next;
            }
        }

        return null;
        */
    }
}
