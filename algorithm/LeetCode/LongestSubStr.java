import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * description：
 给定一个字符串，找到没有重复字符的最长子串，返回它的长度。
    思路：
    【1】迭代所有子字符串，每次迭代判断是否相同，并更新最大不重复长度
    【2】发现含重复元素，找到含重复元素的下标n，计算新的子字符串长度时，从n+1开始计算
 * @author Hartley
 * date： 2020/5/26
 */
public class LongestSubStr {

    //【1】暴力迭代法，时间O(n^3),空间O(max(m,n))
    public static int getLongestSubStr(String str){
        int n = str.length() -1;//字符串数组最大角标
        int maxLen = 0;
        for (int i=0;i<n;i++){
            for (int j=i+1;j<=n;j++){
                if(isAllUnique(str,i,j)) maxLen = Math.max(maxLen,j-i+1);
            }
        }

        return maxLen;
    }

    //判断字符串是否重复
    public static boolean isAllUnique(String str,int from,int to){
        Set<Character> set = new HashSet<>();
        for (int i=from;i<=to;i++){
            if (!set.contains(str.charAt(i))){//边判断边添加元素
                set.add(str.charAt(i));
            }else{
                return  false;
            }
        }
        return true;
    }

    //方法【2】一次循环完，时间复杂度O(N)
    //利用hashMap边循环边装，如果发现重复字符，定位重复字符位置，得出的新子字符从该位置+1开始
    public static int getLongestSubStr2(String str){
        HashMap<Character,Integer> map = new HashMap<>();//字符为key 角标为value
        int max = 0;
        for (int i=0,j=0;j<str.length();j++){
            if (map.containsKey(str.charAt(j))){
                //当原来子串中包含与当前位置相同的字符时
                i = map.get(str.charAt(j))+1;//记录重复字符的后个位置，下次计算子字符串长度需要酱
            }
            max = Math.max(max,j-i+1);
            map.put(str.charAt(j),j);
        }
        return max;
    }
    //程序入口
    public static void main(String[] args) {
        String str = "abcabvaabb";
        System.out.println(getLongestSubStr2(str));
    }
}
