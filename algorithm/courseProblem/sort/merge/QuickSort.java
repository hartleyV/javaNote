package algorithm.courseProblem.sort.merge;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/8/9
 */
public class QuickSort {
    public Integer[] sort(Integer[] arr) {
        //对传入值有个基本判断
        if (arr.length < 2 || arr == null) return arr;

        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    public void quickSort(Integer[]arr,int L,int R){
        if (L>R) return;
        Integer base = arr[L];//基准
        int left = L,right = R;
        while (left!=right){
            while (arr[right]>=base && left<right){
                right--;
            }
            while (arr[left]<=base && left<right){
                left++;
            }
            if (left<right){
                swap(arr,left,right);
            }
        }
        swap(arr,L,left);//碰头的left为中间点
        quickSort(arr,L,left-1);
        quickSort(arr,left+1,R);
    }

    public void swap(Integer[]arr,int a,int b){
        Integer temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
