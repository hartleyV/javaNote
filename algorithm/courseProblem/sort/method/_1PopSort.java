package algorithm.courseProblem.sort.method;

/**
 * description：
    冒泡排序，时间复杂度O(N^2),额外空间O(1)  （稳定）
 * @author Hartley
 * date： 2020/8/5
 */
public class _1PopSort {
    public Integer[] sort(Integer[] arr){
        if (arr.length<2 || arr==null) return arr;

        for (int i=0;i<arr.length;i++){
            for (int j=i;j<arr.length;j++){
                if (arr[i]>arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        return arr;
    }
}
