package algorithm.JZoffer;

/**
 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法

 //如果没有思路就列举，找规律。。。

 * @author Hartley
 * date： 2020/8/11
 */
public class _10RectCover {
    public int RectCover(int target) {
        if(target<=0) return 0;
        if(target==1) return 1;
        if(target==2) return 2;

        int a=1,b=2;
        int res = 0;

        for(int i=3;i<=target;i++){
            res = a+b;
            a = b;
            b = res;
        }

        return res;
    }
}
