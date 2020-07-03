/**
 * description：
    已知两个有序数组，找到两个数组合并后的中位数。
        [1,2] [1,2,3,4] ==> (2+2)/2=2
        [1,2] [3]  ==> 2
    -要求时间复杂度O(log(m+n))

 * @author Hartley
 * date： 2020/6/17
 */
public class FindMedianSortedArrays {
    //【暴力法1】：采用"归并排序"合并数组，根据长度的奇偶性找到中位数
    // 时间复杂度：遍历全部数组，O（m + n）;
    // 空间复杂度：开辟了一个数组，保存合并后的两个数组，O（m + n）
    public static double find1(int[]A,int[]B){

        int len = A.length+B.length;
        int[] arr = new int[len];

        //**********"归并"排序,合并数组,************
        int pos_a=0;
        int pos_b=0;
        int index=0;
       while(index<len) {
           int temp=0;
            //某一数组出界则跳出循环，直接将另一数组装入arr

           //arr2数组先装完，所以下面装arr1数组
           if(pos_b==B.length){
               while(pos_a!=A.length){
                   arr[index++] = A[pos_a++];
               }
               break;
           }

           //arr1数组先装完
           if (pos_a==A.length){
               while (pos_b!=B.length){
                   arr[index++]=B[pos_b++];
               }
               break;
           }

            if (A[pos_a]<B[pos_b]){
                temp = A[pos_a++];
            }else{
                temp = B[pos_b++];
            }

            arr[index++] = temp;
        }


        //**********根据长度奇偶找出中位数************
        double mid = len%2==0?(arr[len/2-1]+arr[len/2])/2.0 : arr[len/2];

        return mid;
    }

    //【改善一丢丢的法2】：在合并数组的同时找出中位数
    //时间复杂度是 O（m + n）,额外空间复杂度O(1)
    public static double find2(int[]A,int[]B){
        int len = A.length+B.length;

        int pos_a=0,pos_b=0;
        int count=0;
        //无论奇偶都保存
        int left=0,right=0;
        //len奇偶都需要循环len/2+1次
       while (++count<=(len/2+1)){
            left = right;
            //循环时，判断A没越界的同时满足：B越界了 或者 B位置元素比A位置元素大
            if(pos_a<A.length &&(pos_b>=B.length || A[pos_a]<B[pos_b])){
                right = A[pos_a++];
            }else{
                right = B[pos_b++];
            }
        }
       double mid = len%2==0? (left+right)/2.0 : right;

        return mid;
    }

    //【3】为了满足时间复杂度O(log(m+n))，需要采用二分法
    public static double find3(int[]arr1,int[]arr2){
        return 0;
    }

    //程序入口
    public static void main(String[] args) {
        int[] arr1 = {1};
        int[] arr2 = {1,2,3};
        //int[] arr2 = {1,2,3};
        System.out.println(find2(arr1,arr2));
    }
}
