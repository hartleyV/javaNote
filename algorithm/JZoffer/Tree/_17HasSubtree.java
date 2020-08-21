package algorithm.JZoffer.Tree;

import algorithm.utils.TreeNode;

/**
判断子树tree2是不是另一个树tree1的子结构：
    遍历tree1，直至tree1的一个子树的根节点与tree2的根节点相同，
    相同则依次判断子树的左右子节点是否与tree1的一致
 * @author Hartley
 * date： 2020/8/15
 */
public class _17HasSubtree {
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        //遍历root1中的节点
        if(root1==null || root2==null) return false;

        if(root1.val == root2.val){
            return dfs(root1,root2);
        }

        return HasSubtree(root1.left,root2) || HasSubtree(root1.left,root2);
    }
    //root1为父树中与子结构相同根的子树
    public boolean dfs(TreeNode root1,TreeNode root2){
        if(root2==null) return true; //如果root2先遍历完，则是的
        if(root1==null) return false;

        if(root1.val == root2.val)
            return dfs(root1.left,root2.left) && dfs(root1.right,root2.right);

        return false;
    }
}
