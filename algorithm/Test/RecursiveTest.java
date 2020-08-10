import algorithm.utils.Node;
import algorithm.courseProblem.recursive.ReverseLinkedList;
import org.junit.Before;
import org.junit.Test;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/8/9
 */
public class RecursiveTest {
    private Node head;
    @Before
    public void init(){
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
    }

    @Test
    public void test(){
        Node.printNodes(head);

        Node reverseHead = new ReverseLinkedList().reverse(head);
        Node.printNodes(head);
        Node.printNodes(reverseHead);
    }
}
