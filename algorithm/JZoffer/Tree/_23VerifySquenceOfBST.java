package algorithm.JZoffer.Tree;

/**
 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 如果是则返回true,否则返回false。假设输入的数组的任意两个数字都互不相同。

 * @author Hartley
 * date： 2020/8/20
 */
public class _23VerifySquenceOfBST {
    public boolean VerifySquenceOfBST(int [] sequence) {
        //二叉搜索树；当前结点相对于子节点 左小右大
        //后序遍历 左、右、中
        if(sequence==null || sequence.length==0) return false;

        return judge(sequence,0,sequence.length-1);
    }

    //采用递归分割法：找到根节点——>分割左右两部分 index
    // ——>右边比当前根节点小则false——>递归左右两部分，正常结束返回true；
    public boolean judge(int[] seq,int start,int end){
        if(start>=end) return true;

        int root = seq[end];

        int index = start;

        //找左右子树的分界线
        for(;index<end;index++){
            if(seq[index]>root){
                break;
            }
        }

        //判断右子树有没有小于节点值的
        for(int j = index;j<end;j++){
            if(seq[j]<root){
                return false;
            }
        }

        return judge(seq,start,index-1) && judge(seq,index+1,end);
    }
}
