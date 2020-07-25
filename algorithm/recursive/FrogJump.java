import java.util.HashMap;
import java.util.Map;

/**
 * description：
    一只青蛙一次只能跳一个台阶或者两个台阶，求跳N个台阶的跳法？
 * @author Hartley
 * date： 2020/7/25
 */
public class FrogJump {
    //自上而下的思路：跳到f(n)的情况无外乎是从f(n-1)或者是f(n-2)过来的
    //边界条件即有两个台阶时f(2)=2;一个台阶时f(1)=1
    //时间复杂度：指数级别
    public static int f1(int n) {
        if (n==1) return 1;
        if (n==2) return 2;

        return f1(n-1)+f1(n-2);
    }

    //自下而上，用备忘录记录，避免重复计算
    public static int f2(int n) {
        if (n<1) return -1;
        Map<Integer,Integer> note = new HashMap<>();
        return helper(note,n);
    }

    public static int helper(Map<Integer,Integer> note,int n){
        if (n==1) return 1;
        if (n==2) return 2;

        //"剪枝"
        if (note.containsKey(n)){
            System.out.println("kacakaca");
            return note.get(n);
        }
        int ret = helper(note,n-1)+helper(note,n-2);
        note.put(n,ret);

        return ret;
    }

    //自下而上，用两个指针记录前后状态
    public static int f3(int n) {
        if (n==1) return 1;
        if (n==2) return 2;

        int pre=1,cur=2,ret=0;
        for (int i=3;i<=n;i++){
            ret = pre + cur;
            pre = cur;
            cur = ret;
        }
        return ret;
    }

    //入口
    public static void main(String[] args) {
        System.out.println(f2(9));
    }
}
