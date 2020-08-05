package algorithm.courseProblem.sort.method;

import java.util.Random;

/**
 * description：
    快速排序：荷兰国旗问题（Partition_按一个基准将其分成三段
 时间复杂度 好时O(nlogn) 坏时O(N*N) 额外空间复杂度O(logN) (不稳定~）
 * @author Hartley
 * date： 2020/8/5
 */
public class _5QuickSort {
    public Integer[] sort(Integer[] arr) {
        //对传入值有个基本判断
        if (arr.length < 2 || arr == null) return arr;


        quickSort(arr);

        return arr;
    }


    public void quickSort(Integer[] arr,int L,int R){

        if (L<R){
            //找基准点，并放到当前子数组的末尾
            Integer index = new Random().nextInt(arr.length);
            swap(arr,index,R);

            int[] pos = partition(arr,L,R);
            int left = pos[0];
            int right = pos[1];
            quickSort(arr,L,left);
            quickSort(arr,right,R);

        }

    }


    //将子数组按末尾元素为基准，划分三段，并返回小于区和大于区的边界索引
    public int[] partition(Integer[] arr,int L,int R){
        //初始化三个指针
        int a = L,b = R;
        Integer base = arr[R];

        while (a<=R){

            if (arr[a]<base){
                a++; //当前值小于base则小区扩大
            }else if (arr[a]>base){
                swap(arr,a,b--);//把大于base的值交换到后面，大区扩大
            }else{

            }


        }



    }

    public void swap(Integer arr[],int m,int n){
        Integer temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }



}
