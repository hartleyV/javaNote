package algorithm.JZoffer;

/**
 * description：
    给定一整数n和二维数组arr，二维数组的**每行/每列元素递增**，
 要求在O(N)时间复杂度判断数组中有无整数n

 * @author Hartley
 * date： 2020/8/7
 */
public class _1FindIn2dArray {

    //根据题目特点找最优解
    public boolean Find(int target, int [][] array) {
        //分析问题=行列都是彼此递增
        int row=0,col=array[0].length-1;

        while(row<array.length && col>=0){

            if(array[row][col]>target){
                col--;
            }else if(array[row][col]<target){
                row++;
            }else{
                return true;
            }

        }

        return false;

    }

    //迭代查找 O(M*N)
    public boolean Find2(int target, int [][] array) {

        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                if(target == array[i][j]){
                    return true;
                }
            }
        }

        return false;
    }
}
