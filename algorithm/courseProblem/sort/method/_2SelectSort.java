package algorithm.courseProblem.sort.method;

/**
 * description：
    选择排序：时间复杂度O(N^2),额外空间O(1) （不稳定）
 * @author Hartley
 * date： 2020/8/5
 */
public class _2SelectSort {
    public Integer[] sort(Integer[] arr){
        //对传入值有个基本判断
        if (arr.length<2 || arr==null) return arr;

        for (int i=0;i<arr.length;i++){
            int index = i;
            for (int j=i;j<arr.length;j++){
                if (arr[index]>arr[j])
                    index = j;
            }

            if (index!=i){
                Integer temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
            }
        }
        return  arr;
    }
}
