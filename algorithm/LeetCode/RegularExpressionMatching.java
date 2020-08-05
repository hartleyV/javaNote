/**
 * description： 【HARD】
 请实现支持'.'和'*'的通配符模式匹配
 * @author Hartley
 * date： 2020/7/24
 */
public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        //*表示任意长度字符
        return true;
    }

    public static void main(String[] args) {
        String str = "ABCDEFGHIJKLMN";

        while (!str.isEmpty()){
            str = str.substring(1);
            System.out.println(str);
        }
    }
}
