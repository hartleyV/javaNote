package algorithm.JZoffer.Tree;

import algorithm.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 请实现一个函数按照之字形打印二叉树，
 即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，
 其他行以此类推。

 (BFS 改变加入list方式 （不可改变入队列的方式！！
 * @author Hartley
 * date： 2020/8/19
 */
public class _59ZigzagPrintTree {
    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(pRoot==null) return res;

        //层间遍历，(X不可以 改变压入队列顺序 因为其子节点入队列的次序已经固定了）
        //通过改变加入list的方式 add(index,value)
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(pRoot);

        boolean flag = true;//默认尾部追加新元素（左到右）

        while(!nodes.isEmpty()){
            int size = nodes.size();
            ArrayList<Integer> level = new ArrayList<>();

            for(int i=0;i<size;i++){
                TreeNode cur = nodes.poll();

                if(flag){
                    level.add(cur.val);
                }else{
                    level.add(0,cur.val);//改成从前部插入
                }

                if(cur.left!=null) nodes.add(cur.left);
                if(cur.right!=null) nodes.add(cur.right);
            }
            flag = !flag;
            res.add(level);
        }
        return res;
    }
}
