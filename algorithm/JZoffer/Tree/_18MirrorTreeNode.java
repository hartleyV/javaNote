package algorithm.JZoffer.Tree;

import algorithm.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 操作给定的二叉树，将其变换为源二叉树的镜像。
 （反转子树的左右节点
 * @author Hartley
 * date： 2020/8/18
 */
public class _18MirrorTreeNode {
    public void Mirror(TreeNode root) {
        //左右节点交换！
        reverse(root);

    }
    //[1]利用递归（easy
    public TreeNode reverse(TreeNode root){
        if(root==null) return null;

        TreeNode left = reverse(root.left);
        TreeNode right = reverse(root.right);
        //注意不可以直接指向，需要中间变量~
        root.left = right;
        root.right = left;
        return root;
    }

    //[2]层间遍历（广度优秀搜索）二叉树
    public void Mirror2(TreeNode root) {
        if(root == null) return;
        //层间遍历需要Queue
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i=0;i<size;i++){
                //同一层的东东
                TreeNode cur = queue.poll();
                TreeNode temp = cur.left;
                cur.left = cur.right;
                cur.right = temp;

                if(cur.left!=null) queue.add(cur.left);
                if(cur.right!=null) queue.add(cur.right);
            }
        }

    }
}
