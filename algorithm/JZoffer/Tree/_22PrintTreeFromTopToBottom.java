package algorithm.JZoffer.Tree;

import algorithm.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 （还是层间遍历 ， 利用队列广度优先遍历BFS就可
 * @author Hartley
 * date： 2020/8/19
 */
public class _22PrintTreeFromTopToBottom {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<>();

        if(root==null){
            return res;
        }

        //利用队列进行广度优先遍历
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while(nodes.size()!=0){
            TreeNode cur = nodes.poll();
            res.add(cur.val); //该处可能会空指针，所有

            if(cur.left!=null) nodes.add(cur.left);
            if(cur.right!=null) nodes.add(cur.right);
        }
        return res;
    }
}
