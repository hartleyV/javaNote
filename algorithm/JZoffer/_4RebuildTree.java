package algorithm.JZoffer;

import algorithm.utils.TreeNode;

import java.util.Arrays;

/**
 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树
    利用递归（黑盒）：前序第一个元素为根节点，然后在中序里面，以改根节点分为左子树和右子树
 * @author Hartley
 * date： 2020/8/10
 */
public class _4RebuildTree {
    //根据前序、中序的遍历结果推导出原来的二叉树结构 eg.前序1 2 3 中序2 1 3
    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
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
