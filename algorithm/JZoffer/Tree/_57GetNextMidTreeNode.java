package algorithm.JZoffer.Tree;

import algorithm.utils.TreeLinkNode;

import java.util.ArrayList;

/**
给出二叉树的一个节点（二叉树包括左右父），求在中序遍历中该节点的下一个节点

 * @author Hartley
 * date： 2020/8/21
 */
public class _57GetNextMidTreeNode {

    //分析题目：根据特点写===逻辑分析。。
    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        //如果有右节点，则下个节点为子右节点的左节点最头
        if(pNode.right != null){
            TreeLinkNode right = pNode.right;

            while(right.left!=null){
                right = right.left;
            }
            return right;
        }

        //没有右节点
        //如果为当前结点为父节点的左子，则直接为父节点
        if(pNode.next!=null && pNode.next.left == pNode ) return pNode.next;

        //如果当前结点为父节点的右子，则一直找，直到父节点为父节点的左子，返回他
        if(pNode.next!=null && pNode.next.right == pNode){
            TreeLinkNode father = pNode.next;
            while(father.next!=null && father.next.left !=father){
                father = father.next;
            }
            return father.next;
        }
        return null;
    }
    //无脑法：先找到根节点、再把中序遍历存到list、最后在list找下个节点
    ArrayList<TreeLinkNode> nodes = new ArrayList<>();
    public TreeLinkNode GetNext2(TreeLinkNode pNode)
    {

        if(pNode == null) return null;


        //找出树的根结点
        TreeLinkNode root = pNode;

        while(root.next !=null){
            root = root.next;
        }

        //将中序遍历结果传入ArrayList
        addMidNodes(root);

        for(int i=0;i<nodes.size();i++){
            if(nodes.get(i) == pNode && i!=nodes.size()-1){
                return nodes.get(i+1);
            }
        }

        return null;
    }

    public void addMidNodes(TreeLinkNode root){
        if(root==null) return;

        addMidNodes(root.left);
        nodes.add(root);
        addMidNodes(root.right);
    }
}
