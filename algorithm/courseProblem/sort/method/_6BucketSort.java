package algorithm.courseProblem.sort.method;

/**
 * description：
 桶排序：
 * @author Hartley
 * date： 2020/8/6
 */
public class _6BucketSort {

    public Integer[] sort(Integer[] arr) {
        //对传入值有个基本判断
        if (arr.length < 2 || arr == null) return arr;

        easyBucketSort(arr);

        return arr;
    }

    public void easyBucketSort(Integer[] arr){
        Integer[] helper = new Integer[arr.length];

        //初始化helper
        for (int i=0;i<helper.length;i++){
            helper[i] = 0;
        }

        for (int j=0;j<arr.length;j++){
            helper[arr[j]]++;
        }

        for (int k=0,m=0;k<helper.length;k++){
            //注意此处桶的角标为arr中的数值
            while (helper[k]!=0){
                arr[m++] = k;
                helper[k]--;
            }
        }
    }
}
