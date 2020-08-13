package algorithm.JZoffer;

/**
 给定一个double类型的浮点数base和int类型的整数exponent。
 求base的exponent次方。



 * @author Hartley
 * date： 2020/8/12
 */
public class _12Power {

    //[2]简单思路：2^-1==(1/2)^1
    public  double Power2(double base, int exponent) {
        if (exponent==0 && base-0.0<0.0000001){
            throw new RuntimeException("0的负数次幂没有意义");
        }

        if (exponent==0){
            return 1;
        }

        double res = 1;
        if(exponent<0){
            base = 1/base;
            exponent = -1 * exponent;
//            exponent = Math.abs(exponent);
        }

        for(int i=0;i<exponent;i++){
            res *=base;
        }

        return res;
    }

    //[1]快速幂 如果是32次方，等于16次方*16次方
    public  double Power(double base, int exponent) {
        if (exponent==0 && base-0.0<0.0000001){
            throw new RuntimeException("0的负数次幂没有意义");
        }
        if (exponent==0){
            return 1;
        }
        double res = 1;
        if(exponent<0){
            base = 1/base;
            exponent = -1 * exponent;
        }

        return helper(base,exponent);
    }

    public double helper(double base, int exponent){
        if (exponent<=0) return 1;
        if (exponent==1) return base;
        //每次递归循环，次数除2
        double result = helper(base,exponent/2);
        result *= result;

        if ((exponent%2)==1){
            result *= base;
        }
        return result;
    }


}
