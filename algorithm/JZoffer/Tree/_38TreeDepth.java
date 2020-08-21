package algorithm.JZoffer.Tree;

import algorithm.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 输入一棵二叉树，求该树的深度。
 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 * @author Hartley
 * date： 2020/8/18
 */
public class _38TreeDepth {

    //分治：分别左子树 和 右子树最大深度，然后返回其中最大的
    public int TreeDepth(TreeNode root) {
        if(root==null) return 0;

        int leftDepth = TreeDepth(root.left) +1;
        int rightDepth = TreeDepth(root.right) +1;
        return Math.max(leftDepth,rightDepth);
        //return leftDepth>rightDepth?leftDepth:rightDepth;
    }

    //利用二叉树按层遍历，每层结束后高度加一（层数是根据当前队列中的size判断）
    public int TreeDepth2(TreeNode root) {
        if(root==null) return 0;


        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);//初始化队列
        int res = 0;
        while(queue.size()!=0){
            int size = queue.size();//当前层的节点数，用size判断某层是否遍历完毕
            while(size!=0){
                TreeNode cur = queue.poll();
                //分别加入同一层的左右子节点！！
                if(cur.left!=null){
                    queue.add(cur.left);
                }
                if(cur.right!=null){
                    queue.add(cur.right);
                }
                size--;
            }
            res++;
        }
        return res;
    }
}
