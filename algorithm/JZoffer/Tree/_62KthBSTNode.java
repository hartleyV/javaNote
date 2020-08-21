package algorithm.JZoffer.Tree;

import algorithm.utils.TreeNode;

import java.util.ArrayList;

/**
 给定一棵二叉搜索树，请找出其中的第k小的结点。
 例如， （5，3，7，2，4，6，8）中，按结点数值大小顺序第三小结点的值为4。


 思路：BST的中序遍历为由小到大的顺序！！
 * @author Hartley
 * date： 2020/8/20
 */
public class _62KthBSTNode {
    TreeNode KthNode(TreeNode pRoot, int k)
    {
        ArrayList<TreeNode> list = new ArrayList<>();
        midList(pRoot,list);//中序遍历到指定列表中~

        if(k>=1 && list.size()>=k)//此处的条件比较周全，等同于判断了k和pRoot的边界
            return list.get(k-1);

        return null;
    }

    public void midList(TreeNode root,ArrayList<TreeNode> list){
        if(root == null) return ;

        midList(root.left,list);
        list.add(root);
        midList(root.right,list);
    }
}
