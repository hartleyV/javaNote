package algorithm.JZoffer.Tree;

import algorithm.utils.TreeNode;

/**
 用来判断一棵二叉树是不是对称的。
 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。


 * @author Hartley
 * date： 2020/8/21
 */
public class _58isSymmetricalTree {
    boolean isSymmetrical(TreeNode pRoot)
    {
        //判断左子树和右子树是不是相同
        return pRoot==null || judge(pRoot.left,pRoot.right);
    }

    boolean judge(TreeNode node1,TreeNode node2){
        if(node1 == null && node2 == null) return true;

        if(node1 ==null || node2==null) return false;

        if(node1.val!=node2.val) return false;

        return judge(node1.left,node2.right) && judge(node1.right,node2.left);
    }
}
