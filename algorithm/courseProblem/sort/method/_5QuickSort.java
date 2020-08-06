package algorithm.courseProblem.sort.method;

import java.util.Random;

/**
 * description：
    快速排序：一趟整理出： 小于base base 大于base
 其中[1]经典快排固定base，[2]改良的随机取base====对于时间复杂度的期望为O(NlogN)
 时间复杂度 好时O(nlogn) 坏时O(N*N) 额外空间复杂度O(logN) (不稳定~）

 * @author Hartley
 * date： 2020/8/5
 */
public class _5QuickSort {
    public Integer[] sort(Integer[] arr) {
        //对传入值有个基本判断
        if (arr.length < 2 || arr == null) return arr;


        //traditionQuickSort(arr,0,arr.length-1);
        quickSort(arr,0,arr.length-1);

        return arr;
    }


    //[1]传统快排（设定固定位置为基准-如子数组中第一个值
    public void traditionQuickSort(Integer[] arr,int L,int R){
        if (L>R) return;

        //设定数组第一个值为基准值
        Integer base = arr[L];
        //左右两个探针依次寻找（碰头结束；左指针找到大于基准 且 右指针找到小于基准 则对调位置
        int left = L;
        int right = R;

        //碰头则结束该轮探测
        while (left!=right){
            //右探针先行，找到小于基准的位置
            while (arr[right]>=base && right>left){//注意里面是没找到的条件
                right--;
            }

            //左探针找到大于基准的位置
            while (arr[left]<=base && right>left){
                left++;
            }

            if (right>left){
                swap(arr,left,right);
            }

        }
        //碰头结束后归位基准值~
        swap(arr,L,left);

        traditionQuickSort(arr,L,left-1);
        traditionQuickSort(arr,left+1,R);

    }

    //[2]不固定基准位置的快排
    public void quickSort(Integer[] arr,int L,int R){
        if (L>R) return;

        int i=L,j=R;//两个探测器

        //随机取基准
        int index = L+ new Random().nextInt(R-L+1);
        int base = arr[index];
        swap(arr,index,L);//把基准值放到当前子数组的头部

        while (i!=j){

            while (arr[j]>=base && i<j){
                j--;
            }

            while (arr[i]<=base && i<j){
                i++;
            }

            if (i<j){
                swap(arr,i,j);
            }
        }
        //基准值放到中间~
        swap(arr,L,i);

        quickSort(arr,L,i-1);
        quickSort(arr,i+1,R);
    }


    public void swap(Integer arr[],int m,int n){
        Integer temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }



}
