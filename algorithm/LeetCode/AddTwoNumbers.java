import org.junit.jupiter.api.Test;

/**
 * description：通过链表进行加减（不会溢出）
    比如 2-4-3-null + 5-6-4-null （342+465=807）
    结果为 7-0-8-null
 *
 * @author Hartley
 * date： 2020/5/24
 */
public class AddTwoNumbers {

    //普通实现
    public static Node commonTest(Node l1,Node l2){
        Node p = l1,q = l2;
        Node head = new Node(0);//存储结果头的前一节点的链表
        Node curr = head;
        int carry = 0;//标记进位
        while(p!=null || q!=null){
                int n1 = p!=null?p.value:0;//三目运算符在多个逻辑叠加时，可有效简化代码
                int n2 = q!=null?q.value:0;
                int sum = n1+n2 + carry;
                carry = sum/10;//进位
                curr.next = new Node(sum%10);
                curr = curr.next;
            //更新指针,注意要判断next值是否为null，否则空指针异常
                if(p!=null) p = p.next;
                if(q!=null) q = q.next;
        }
        if (carry>0){
            curr.next = new Node(carry);
        }

        return head.next;//记录位置的后一个
    }

    //************程序入口************
    public static void main(String[] args) {
        Node l1 = new Node(2);
        l1.next = new Node(4);
        l1.next.next = new Node(3);
        l1.next.next.next = new Node(6);

        Node l2 = new Node(5);
        l2.next = new Node(6);
        l2.next.next = new Node(4);

        printLinkedList(l1);
        printLinkedList(l2);
        printLinkedList(commonTest(l1,l2));
    }

    //打印链表
    public static void printLinkedList(Node head)
    {
        while (head!=null)
        {
            System.out.print(head.value+" -> ");
            head = head.next;
        }
        System.out.println("null");
    }

}

//节点类
class Node {
    public int value;
    public Node next;
    public Node(int value){
        this.value = value;
    }
}
