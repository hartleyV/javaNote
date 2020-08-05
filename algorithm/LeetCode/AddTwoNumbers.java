
/**
 * description：
 * 【1】通过链表进行加减（不会溢出）
    比如 2-4-3-null + 5-6-4-null （342+465=807）
    结果为 7-0-8-null
 思路：每个节点同步前进，加和后本位留下n%10，下一次进位为n/10
        在边界判断中，判断节点1和节点2哪个到终点，可以用三目运算符简化代码

 *【2】扩展，逆向相加
     比如 2-4-3-null + 5-6-4-null （243+564=817）
     结果为 8-1-7-null
 思路：将链表指向反向，相加完成再转过来
    【迭代】解法（easy，，适用于线性结构）
    【递归】解法（较难理解，适用于树形结构，可以将其看作是栈）

 * @author Hartley
 * date： 2020/5/26
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

    //链表逆序,普通迭代写法
    public static Node reverseLink(Node head){
        Node cur = head;
        Node pre = null;
        Node next =null;

        while(cur!=null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;//最后cur=null到达末尾退出循环，返回cur前一个 即为反转链表的头
    }
    //链表逆序，递归写法**好难理解~~~~将递归想象成栈，后进先出
    public static Node reverserLinkRecursion(Node head){
        Node newHead =head.next;//用于移动的结点
        //程序出口
        if (head==null || head.next==null){
            return head;
        }

        /*1->2->3->null
          循环到出口，栈顶的head为3
          回溯到第二层栈，其head为2，head.next是3，让他指向2（head.next.next=head),
          并切断2到3的指向（head.next=null)，
          依次向前回溯，每次回溯的返回值都是最后一个节点3，从而保存住新链表的头
         */

        //核心代码********************************
        newHead = reverserLinkRecursion(head.next);
        head.next.next = head;
        head.next = null;
        //***************************************
        return  newHead;//保留反转链表的新头

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

        //printLinkedList(reverseLink(l1));
        printLinkedList(reverserLinkRecursion(l1));
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
