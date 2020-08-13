package algorithm.utils;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/8/9
 */
public class Node {
    public int val;
    public Node next;
    public Node(int value)
    {
        this.val = value;
    }

    public static void printNodes(Node head){
        while (head!=null){
            System.out.print(head.val +"->");
            head = head.next;
        }
        System.out.println();
    }
}
