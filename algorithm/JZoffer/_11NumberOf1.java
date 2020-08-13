package algorithm.JZoffer;

/**
 输入一个整数，输出该数32位二进制表示中1的个数。
 其中负数用补码表示。

 主要考察负数在二进制中是补码存在的，并且在右移位时会保留符号位
 * @author Hartley
 * date： 2020/8/12
 */
public class _11NumberOf1 {

    //[2]每次循环将1归零,miao~
    public int NumberOf1Method2(int n) {
        int res = 0;
        while (n!=0){
            res++;//注意此处！直接看循环次数
            n = n & (n-1);//会消去最右边的1
        }
        return res;
    }

    //[1]直接去掉符号位
    public int NumberOf1(int n) {
        int res = 0;

        if(n<0){
            //去掉最高位的符号位
            n = n & 0x7FFFFFFF;
            res++;
        }

        while(n!=0){
            if((n&1)!=0) res++;
            n = n>>1;//右移时符号位会一直都在。
        }

        /*
        //主要考察位运算，不是这样String啦！
        String str = Integer.toBinaryString(n);
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='1'){
                res++;
            }
        }
        */
        return res;
    }



    public static void main(String[] args) {
        int n = -1;
        int res = 0;
        printBinary(n);

        if(n<0){
            //去掉最高位的符号位
            n = n & 0x7FFFFFFF;
            res++;
        }
        printBinary(n);

        while(n!=0){
            if((n&1)!=0) res++;
            n = n>>1;
        }

    }
    public static void printBinary(int n){
        System.out.println(Integer.toBinaryString(n));
    }



}
