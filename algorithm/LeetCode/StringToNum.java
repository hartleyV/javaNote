/**
 * description：
 实现函数 atoi 。函数的功能为将字符串转化为整数
 提示：仔细思考所有可能的输入情况。
 1.起始有空格
 2.含英文--英文开头输出0 英文在中间则截断
 3.超出int范围 则输出最值
 4.开头除了空格，遇到正负数字则开始，否则直接输出0
 * @author Hartley
 * date： 2020/7/23
 */
public class StringToNum {
    public int atoi (String str) {
        // write code here
        boolean isHead = false;//找开头
        int ret = 0;
        int sign = 1;//默认符号为正
        for(char c:str.toCharArray()){
            //处理前置空格
            if (c==' '){
                continue;
            }
            if (c=='+' && !isHead){
                isHead = true;
                sign = 1;
                continue;
            }
            if (c=='-' && !isHead){
                isHead = true;
                sign = -1;
                continue;
            }

            if (c>='0' && c<='9'){
                //防止加和时溢出，需要先进行验证-2147483648  2147483647
                int pop = c-'0';

                //注意此处要乘以符号，边界包含等于部分的验证
                if (ret*sign>Integer.MAX_VALUE/10 || (ret*sign==Integer.MAX_VALUE/10 && pop>7) ) return Integer.MAX_VALUE;
                if (ret*sign<Integer.MIN_VALUE/10 || (ret*sign==Integer.MIN_VALUE/10 && pop*sign<-8)) return Integer.MIN_VALUE;

                ret = ret*10 + pop;
            }else{
                return sign*ret;
            }

        }

        return sign*ret;
    }
}
