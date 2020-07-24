/**
 * description：
 在不使用额外的内存空间的条件下判断一个整数是否是回文 输出true 或 false
 提示：
     负整数可以是回文吗？（比如-1）
     如果你在考虑将数字转化为字符串的话，请注意一下不能使用额外空间的限制
     你可以将整数翻转。但是，如果你做过题目“Reverse Integer”，你会知道将整数翻转可能会出现溢出的情况，你怎么处理这个问题？
     这道题有更具普遍性的解法。

 1.不能使用额外空间
 2.注意翻转后溢出的情况
 3.负数不是回文

 * @author Hartley
 * date： 2020/7/23
 */
public class PalindromNumber {
    //直接倒置数字，比较与原数字是否一致
    public  boolean isPalindrome (int x) {
        // write code here
        if (x<0) return false;//负数非回文
        //倒置后溢出（大于2147483647）不存在回文数字的情况，所以一旦溢出直接false
        int x2=x;
        int rev = 0;//倒置数字
        while (x!=0){
            int pop = x%10;
            x /= 10;
            if (rev>Integer.MAX_VALUE/10) return false;
            rev = rev*10+pop;
        }

        return x2==rev;//注意x在循环时已经改变
    }

    //通过获取数值位数，如1221比较12和倒置后的21即可，12321比较12与倒置的21
    public  boolean isPalindrome2 (int x) {
        if (x<0) return false;
        //数字x总位数 lgX + 1
        int n = (int) (Math.log10(x)+1);
        //倒置时奇数偶数都循环n/2次，后面验证时讨论
        int rev=0;//因为是倒置x的一半，所以不用考虑溢出问题
        for (int i=0;i<n/2;i++){
            int pop = x%10;
            x /= 10;
            rev = rev*10 + pop;
        }

        //偶数直接比较即可
        if (n%2==0){
            return rev==x;
        }else{
            //奇数
            x /= 10;//去掉中间位
            return rev==x;
        }
    }


}
