package algorithm.JZoffer;

import algorithm.utils.TreeNode;

import java.util.ArrayList;

/**
 输入一颗二叉树的根节点和一个整数，
 按字典序打印出二叉树中结点值的和为输入整数的所有路径。
 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。

 (递归左右节点，判断路径需要满足： 到达了叶子节点的同时 && 总和为target )
 * @author Hartley
 * date： 2020/8/19
 */
public class _24FindAddTreePath {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(root==null) return res;

        ArrayList<Integer> path = new ArrayList<>();
        FindPath(root,target,res,path);
        return res;
    }
    //dfs
    public void FindPath(TreeNode root,int target,
                         ArrayList<ArrayList<Integer>> res,
                         ArrayList<Integer> path){
        if(root==null) return;
        path.add(root.val);//添加节点
        target-=root.val;//更新target值

        //当前值为0并且是叶子节点，则当前路径是的
        if(target==0 && (root.left==null && root.right==null)){
            res.add(path);
            return;
        }
        //递归左右子树，参数是引用传递，为了保证每条path间独立
        //该构造方法为深拷贝，
        //eg. 第一次根节点1 path( 1 ) ;第二次1的左节点为2 新的path( 1 2 )
        FindPath(root.left,target,res,new ArrayList<Integer>(path));
        FindPath(root.right,target,res,new ArrayList<Integer>(path));
    }
}
