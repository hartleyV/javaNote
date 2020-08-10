import algorithm.courseProblem.tree.DeducePreTree;
import algorithm.courseProblem.tree.DeduceTree;
import algorithm.utils.TreeNode;
import org.junit.Before;
import org.junit.Test;

/**
    二叉树测试
 * @author Hartley
 * date： 2020/8/9
 */
public class TreeTest {
    TreeNode treeNode;
    int[] pre = {1,2,3,4,5,6};
    int[] in = {3,2,1,5,4,6};
    int[] post = {3,2,5,6,4,1};

    @Before
    public void init(){
        treeNode = new TreeNode(1);

        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(4);

        treeNode.left.left = new TreeNode(3);
        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(6);


    }

    @Test
    public void test(){
//        TreeNode treeNode = DeducePreTree.rebuild(in,post);
        TreeNode treeNode = DeducePreTree.rebuild(in,0,in.length-1,post,0,post.length-1);
        TreeNode.printTreePre(treeNode);
    }
}
