package algorithm.JZoffer;

/**
 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
 求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * @author Hartley
 * date： 2020/8/11
 */
public class _9JumpFloorII {
    public int JumpFloorII(int target) {
        //数学问题 f(n) = f(n-1) + f(n-2) + ...f(1)
        //f(n-1) = f(n-2) + ...f(1) ===》f(n) = 2f(n-1)

        if(target<=0) return 0;
        if(target==1) return 1;
        if(target==2) return 2;

        return 1<<(target-1);
    }
}
