package algorithm.utils;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/8/13
 */
public class RandomListNode {
    public int label;
    public RandomListNode next = null;
    public RandomListNode random = null;

    public RandomListNode(int label) {
        this.label = label;
    }

    public static void printRandomListNode(RandomListNode node){
        while (node!=null){
            String ranStr;
            if (node.random==null){
                ranStr = null;
            }else {
                ranStr = node.random.label+"";
            }
            System.out.print(node.label+": "+ranStr+" ——> ");
            node = node.next;
        }
        System.out.println();
    }
}
