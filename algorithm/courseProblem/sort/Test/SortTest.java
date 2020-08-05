import algorithm.courseProblem.sort.method.*;
import org.junit.Test;

import java.util.Arrays;

/**
 * description：
    测试排序类
 * @author Hartley
 * date： 2020/8/5
 */
public class SortTest {
    Integer[] arr = {1,4,2,5,0,3,6,8,7};

    @Test
    public void sortTest(){
        //Integer[] res = new _1PopSort().sort(arr);
        //Integer[] res = new _2SelectSort().sort(arr);
        //Integer[] res = new _3InsertSort().sort(arr);
        Integer[] res = new _4MergeSort().sort(arr);
        System.out.println(Arrays.toString(res));
    }


}
