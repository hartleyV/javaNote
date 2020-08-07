package algorithm.JZoffer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * description：
    反向打印链表：从尾巴到头
 //反向打印，那我先反转链表
 //压栈
 //递归（类似压栈
 * @author Hartley
 * date： 2020/8/7
 */
public class _3ReversePrintLinkedList {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
       return null;
    }

    //方法一：反向打印，那先反转链表
    public ArrayList<Integer> reverserMethod(ListNode listNode){
        listNode = reverseLinkedList(listNode);
        ListNode temp = listNode;

        ArrayList<Integer> res = new ArrayList<>();

        while(listNode!=null){
            res.add(listNode.value);
            listNode = listNode.next;
        }

        listNode = reverseLinkedList(temp);
        return res;
    }

    //方法二：利用栈先进后出
    public ArrayList<Integer> stackMethod(ListNode listNode){
        Stack<Integer> helper = new Stack<>();
        ArrayList<Integer> res = new ArrayList<>();

        //压栈
        while (listNode!=null){
            helper.push(listNode.value);
            listNode = listNode.next;
        }

        while (!helper.empty()){
            res.add(helper.pop());
        }

        return res;
    }

    //方法三：利用递归栈
    public void recursiveMethod(ListNode listNode,ArrayList<Integer> res){
        if (listNode!=null){
            if (listNode.next!=null){
                recursiveMethod(listNode.next,res);
            }
            res.add(listNode.value);
        }

    }

    public ListNode reverseLinkedList(ListNode listNode){
        ListNode pre=null,next=null;
        while (listNode!=null){
            next = listNode.next;
            listNode.next = pre;
            pre = listNode;//pre保存了反转链表的头部
            listNode = next;
        }
        return pre;
    }



    private  class ListNode{
        Integer value;
        ListNode next;
    }
}
