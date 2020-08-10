package algorithm.utils;

import algorithm.courseProblem.tree.DeducePreTree;

/**
    树
 * @author Hartley
 * date： 2020/8/9
 */
public class TreeNode {
    public int val;
    public TreeNode left,right;
    public TreeNode(int val){this.val = val;}

    //前序打印树
    public static void printTreePre(TreeNode treeNode){
        if (treeNode==null) return;

        //先打印根
        System.out.print(treeNode.val+",");
        printTreePre(treeNode.left);
        printTreePre(treeNode.right);
    }
    //中序打印树
    public static void printTreeMid(TreeNode treeNode){
        if (treeNode==null) return;

        printTreeMid(treeNode.left);//先打印左
        System.out.print(treeNode.val+",");
        printTreeMid(treeNode.right);
    }
    //后序打印树
    public static void printTreePost(TreeNode treeNode){
        if (treeNode==null) return;

        printTreePost(treeNode.left);//先打印左
        printTreePost(treeNode.right);
        System.out.print(treeNode.val+",");//最后打印根
    }

}
