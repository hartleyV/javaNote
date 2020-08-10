package algorithm.courseProblem.tree;

import algorithm.utils.TreeNode;

import java.util.Arrays;

/**
    关于二叉树的：
        打印树：前序（根-左-右）中序（左-根-右）后序（左-右-根）
    ============根据左中、右中推导原二叉树=================
 * @author Hartley
 * date： 2020/8/9
 */
public class DeduceTree {

    //根据前序、中序的遍历结果推导出原来的二叉树结构 eg.前序1 2 3 中序2 1 3
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length<=0 || in.length<=0) return null;

        //前序遍历结果的头元素即为二叉树的根 1
        TreeNode root = new TreeNode(pre[0]);
        //在中序结果中，以二叉树的根为界限，左边为左节点2 右边为右节点3
        for (int i=0;i<in.length;i++){
            if (in[i]==root.val){

                //此处注意递归中切分数组的范围：pre-[1 i+1) i个元素；in-[0,i) i个元素
                root.left = reConstructBinaryTree
                        (Arrays.copyOfRange(pre,1,i+1),Arrays.copyOfRange(in,0,i));//右端点取不到!!
                root.right = reConstructBinaryTree
                        (Arrays.copyOfRange(pre,i+1,pre.length),Arrays.copyOfRange(in,i+1,in.length));
            }
        }

        return root;

    }

}
