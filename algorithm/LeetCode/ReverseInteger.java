/**
 * description：
 将给出的整数x翻转
     例1:x=123，返回321
     例2:x=-123，返回-321
    如果翻转后溢出则输出零
 * @author Hartley
 * date： 2020/7/23
 */
public class ReverseInteger {

    //处理翻转后溢出：判断最后一次循环中rev与int溢出值大小
    public int reverse (int x) {
        // write code here
        int rev = 0;

        while(x!=0){
            int pop = x%10;
            x /= 10;
            if (rev>Integer.MAX_VALUE/10 || rev<Integer.MIN_VALUE/10) return 0;
            rev = rev*10 + pop;
        }

        return rev;

    }
    //处理翻转后溢出：翻转前用long操作，翻转后判断
    public int reverse2 (int x) {
        long rev = 0;
        while (x!=0){
            int pop = x%10;
            x /= 10;
            rev = rev*10 + pop;
        }

        if (rev>Integer.MAX_VALUE || rev<Integer.MIN_VALUE) return 0;

        return (int)rev;
    }
}
