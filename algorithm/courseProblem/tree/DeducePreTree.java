package algorithm.courseProblem.tree;

import algorithm.utils.TreeNode;

import java.util.Arrays;

/**
    已知中后序，求前序
 * @author Hartley
 * date： 2020/8/10
 */
public class DeducePreTree {
    public static TreeNode rebuild(int[]in,int[]post) {
        if (in.length == 0 || post.length == 0) return null;

        TreeNode root = new TreeNode(post[post.length - 1]);
        //不用Arrays的copyOfRange
        for (int i = 0; i < in.length; i++) {
            if (in[i] == root.val) {//i在中序 为二叉树的根
                root.left = rebuild(Arrays.copyOfRange(in, 0, i), Arrays.copyOfRange(post, 0, i));
                root.right = rebuild(Arrays.copyOfRange(in, i + 1, in.length), Arrays.copyOfRange(post, i, post.length - 1));
            }
        }
        return root;
    }
    ////不用Arrays的copyOfRange...有点乱，，理不清啦！！
    public static TreeNode rebuild(int[]in,int inStart,int intEnd,int[]post,int postStart,int postEnd){
        if (inStart>intEnd || postStart>postEnd) return null;
        TreeNode root = new TreeNode(post[postEnd]);

        for (int i=inStart;i<=intEnd;i++){
            if (in[i]==root.val){
               root.left = rebuild(in,inStart,i-1,post,postStart,postStart+i-inStart-1);
               root.right = rebuild(in,i+1,intEnd,post,postStart+i-inStart,postEnd-1);
            }
        }

        return root;
    }
}
