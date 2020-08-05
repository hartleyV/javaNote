package algorithm.courseProblem.sort.method;

/**
 * description：
    归并排序：分左右两部分（分治的思想），每段通过外排法排序
    时间复杂度O(N*logN) 空间复杂度O(N)  （稳定~）
 小和、逆序对问题
 * @author Hartley
 * date： 2020/8/5
 */
public class _4MergeSort {
    public Integer[] sort(Integer[] arr) {
        //对传入值有个基本判断
        if (arr.length < 2 || arr == null) return arr;

        mergeSort(arr,0,arr.length-1);

        return arr;
    }

    //递归作用只是将问题对半分解（类似二分法查找的对半~）
    public void mergeSort(Integer[] arr,int left,int right){
        if (left == right){
            return ;
        }

        //分成左右两部分(right+left)/2
        int mid = left + (right - left)/2;

        mergeSort(arr,left,mid);
        mergeSort(arr,mid+1,right);

        //合并&外排排序
        merge(arr,left,mid,right);
    }

    //外排排序，并且合并到原来数组中
    public  void merge(Integer[] arr,int left,int mid,int right){
        //额外空间出现！！
        Integer[] helper = new Integer[right-left+1];
        int index=0;

        //外排排序
        //以中间为界，分为两段，谁小谁进辅助数组
        int posA = left , posB = mid+1;
        for (;posA<=mid&&posB<=right;){
            helper[index++] = arr[posA]<=arr[posB] ? arr[posA++]:arr[posB++];
        }


        //把左边或者右边剩下的部分放入数组中
        for (;posA<=mid;posA++){
            helper[index++] = arr[posA];
        }
        for (;posB<=right;posB++){
            helper[index++] = arr[posB];
        }

        //合并到原数组中~
        for (int i=left;i<=right;i++){
            arr[i] = helper[i-left];
        }

    }
}
