package algorithm.JZoffer;

/**
 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法
    同Fibonacci数列，迭代解比递归解好~
 * @author Hartley
 * date： 2020/8/11
 */
public class _8JumpFloor {
    public int JumpFloor(int target) {
        if(target<=0) return 0;
        if(target==1) return 1;
        if(target==2) return 2;

        int f1 = 1, f2 = 2;
        int res = 0;
        for(int i = 3;i<=target;i++){
            res = f1 + f2;
            f1 = f2;
            f2 = res;
        }

        return res;


        /*
        if(target==1) return 1;
        if(target==2) return 2;

        return JumpFloor(target-1)+JumpFloor(target-2);
        */
    }
}
