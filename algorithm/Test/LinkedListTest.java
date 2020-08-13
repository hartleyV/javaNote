import algorithm.JZoffer._25RandomListNodeClone;
import algorithm.utils.RandomListNode;
import org.junit.Before;
import org.junit.Test;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/8/13
 */
public class LinkedListTest {
    RandomListNode pHead;

    @Before
    public void init(){
        pHead = new RandomListNode(1);
        RandomListNode sec = new RandomListNode(2);
        RandomListNode third = new RandomListNode(3);
        pHead.next = sec;
        sec.next =third;
        third.next = null;

        pHead.random = third;
        sec.random = pHead;
        third.random = null;
    }

    @Test
    public void RandomListNodeCloneTest(){
        _25RandomListNodeClone.Clone2(pHead);
    }
}
