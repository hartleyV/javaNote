package algorithm.courseProblem.recursive;

import algorithm.utils.Node;

/**
    训练递归的思想(出口条件，缩小规模后编写功能）
 * @author Hartley
 * date： 2020/8/9
 */
public class ReverseLinkedList {

    //通过递归反转单链表 1->2->3->null
    public Node reverse(Node head){
        if (head.next==null) return head;

        //此处假装reverse已经是反转[head.next,null)部分的结果2<-3
        Node newHead = reverse(head.next);

        //后面只需将头部指向调整正确即可 1->2<-3
        head.next.next = head;
        head.next = null;
        return  newHead;
    }



}
