package algorithm.utils;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/8/9
 */
public class Node {
    public int value;
    public Node next;
    public Node(int value)
    {
        this.value = value;
    }

    public static void printNodes(Node head){
        while (head!=null){
            System.out.print(head.value+"->");
            head = head.next;
        }
        System.out.println();
    }
}
