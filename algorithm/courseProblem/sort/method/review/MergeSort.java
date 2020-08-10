package algorithm.courseProblem.sort.method.review;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/8/9
 */
public class MergeSort {
    public Integer[] sort(Integer[] arr) {
        if (arr.length < 2 || arr == null) return arr;

        mergeSort(arr,0,arr.length-1);
        return arr;
    }

    public  void mergeSort(Integer[]arr,int left,int right){
        if (left==right) return;
        int mid = (right-left)/2 + left;

        mergeSort(arr,left,mid);
        mergeSort(arr,mid+1,right);

        merge(arr,left,mid,right);
    }

    //外排
    public void merge(Integer[]arr,int left,int mid,int right){
        Integer[] helper = new Integer[right-left+1];
        int posA=left,posB=mid+1,index=0;
        while (posA<=mid && posB<=right){
            helper[index++] = arr[posA]<arr[posB]?arr[posA++]:arr[posB++];
        }

        while (posA<=mid){
            helper[index++] = arr[posA++];
        }
        while (posB<=right){
            helper[index++] = arr[posB++];
        }

        for (int i = left;i<=right;i++){
            arr[i] = helper[i-left];
        }
    }

}
