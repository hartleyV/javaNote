package algorithm.courseProblem.sort.method;

/**
 * description：
    插入排序：最好情况O(N) eg. 1 2 3 4,最坏情况O(N*N)  （稳定）
 * @author Hartley
 * date： 2020/8/5
 */
public class _3InsertSort {
    public Integer[] sort(Integer[] arr) {
        //对传入值有个基本判断
        if (arr.length < 2 || arr == null) return arr;


        for (int i=1;i<arr.length;i++){
            ////当前元素在j+1位置！！，以此向前插入，并记录当前插入位置
            for (int j=i-1;j>=0 &&(arr[j+1]<arr[j]) ;j--){
                    Integer temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;

            }
        }

        return  arr;
    }
}
