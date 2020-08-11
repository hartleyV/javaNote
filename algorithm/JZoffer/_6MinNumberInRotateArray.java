package algorithm.JZoffer;

/**
 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 输入一个*非递减排序的数组的一个旋转，输出旋转数组的最小元素

 如：123456 - 345621 ； 12222 - 22212

  遍历则失去题目意义，不可不可，
    需要根据题目特点：相当于两段排好顺序的数组，找接触处元素（按二分分类：

 * @author Hartley
 * date： 2020/8/10
 */
public class _6MinNumberInRotateArray {
    public int minNumberInRotateArray(int [] array) {
        //123456 - 345621 ； 12222 - 22122

        //利用二分法（但是注意相等时的情形！
        int left = 0,right = array.length-1,mid = 0;

        while(left<right){
            mid = (right-left)/2 + left;
            //大于 说明mid在没有反转的序列上
            if(array[mid]>array[right]){
                left = mid +1;

            }else if(array[mid]<array[right]){
                //小于说明mid在反转的序列上
                right = mid;
            }else{
                //等于说明没有办法确认，依次遍历，，
                right--;
            }
        }


        return array[left];
        /*

        //遍历则失去题目意义，不可不可
        int res = 0;
        if(array==null||array.length==0)  return 0;

       for(int i=1;i<array.length;i++){
           if(array[i]<array[i-1]){
                res = array[i];
            }
       }*/

        //return res;
    }
}
