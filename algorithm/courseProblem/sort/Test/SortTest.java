import algorithm.courseProblem.sort.merge.QuickSort;
import algorithm.courseProblem.sort.method.*;
import algorithm.courseProblem.sort.method.review.HeapSort;
import algorithm.courseProblem.sort.method.review.MergeSort;
import org.junit.Test;

import java.util.Arrays;

/**
 * description：
    测试排序类
 * @author Hartley
 * date： 2020/8/5
 */
public class SortTest {
    //Integer[] arr = {1,4,2,0 };
    Integer[] arr = {1,4,2,5,0,3,6,8,7};

    @Test
    public void sortTest(){
        //Integer[] res = new _1PopSort().sort(arr);
        //Integer[] res = new _2SelectSort().sort(arr);
        //Integer[] res = new _3InsertSort().sort(arr);
        //Integer[] res = new _4MergeSort().sort(arr);
//        Integer[] res = new MergeSort().sort(arr);
        //Integer[] res = new _5QuickSort().sort(arr);
        Integer[] res = new QuickSort().sort(arr);
        //Integer[] res = new _6BucketSort().sort(arr);
        //Integer[] res = new _7HeapSort().sort(arr);
        //Integer[] res = new HeapSort().sort(arr);
        System.out.println(Arrays.toString(res));
    }


}
