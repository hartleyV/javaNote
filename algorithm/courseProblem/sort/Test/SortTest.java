import algorithm.courseProblem.sort.merge.QuickSort;
import algorithm.courseProblem.sort.method.*;
import algorithm.courseProblem.sort.method.review.HeapSort;
import algorithm.courseProblem.sort.method.review.MergeSort;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * description：
    测试排序类
 * @author Hartley
 * date： 2020/8/5
 */
public class SortTest {
    //Integer[] arr = {1,4,2,0 };
    //Integer[] arr = {1,4,2,5,0,3,6,8,7};

    @Test
    public void sortTest(){

        long start = System.currentTimeMillis();
        Integer[] arr = new Integer[100000];
        for (int i=0;i<arr.length;i++){
            arr[i] = new Random().nextInt(1000);
        }

        //Integer[] res = new _1PopSort().sort(arr);
        //Integer[] res = new _2SelectSort().sort(arr);
        //Integer[] res = new _3InsertSort().sort(arr);
        //Integer[] res = new _4MergeSort().sort(arr);
//        Integer[] res = new MergeSort().sort(arr);
        //Integer[] res = new _5QuickSort().sort(arr);
        //Integer[] res = new QuickSort().sort(arr);
        //Integer[] res = new _6BucketSort().sort(arr);


        Integer[] res = new _7HeapSort().sort(arr);

        System.out.println((System.currentTimeMillis() - start));
        //Integer[] res = new HeapSort().sort(arr);
        System.out.println(Arrays.toString(res));
    }


}
