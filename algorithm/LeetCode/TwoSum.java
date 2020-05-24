import java.util.Arrays;
import java.util.HashMap;

/**
 * LeetCode第一题
 * 问题：在一数值数值中，给出一个数，输出数组中两元素之和等于该数的下标
 * 思路：利用哈希表的映射，省去内层找数的循环（边装边找）
 * @author Hartley
 * date 2020/5/23
 */
public class TwoSum {
    //暴力法，两层循环,时间复杂度O(N)
    public static int[] stupid(int[] arr,int target){
        for(int i=0;i<arr.length;i++){
            int sub = target-arr[i];//找与i位置对应的数~
            for(int j=i+1;j<arr.length;j++){
                if(sub == arr[j]){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    //利用哈希表的映射，省去内层找数的循环
    public static int[] hashTest1(int[] arr,int target){
        //第一步把数装进哈希表
        HashMap<Integer,Integer> hash = new HashMap<>();
        for (int i=0;i<arr.length;i++){
            hash.put(arr[i],i);//数组的数值作为key，数组的索引作为value
        }
        //第二步用哈希表找数（注意不要找到同一位置的数）
        for(int j=0;j<arr.length;j++){
            int sub = target - arr[j];
            if(hash.containsKey(sub) && j!=hash.get(sub)){//同时对是不是一个位置进行判断
                return new int[]{j,hash.get(sub)};
            }
        }
        return null;
    }

    //同样利用哈希表，但是是边装边找
    public static int[] hashTest2(int[] arr,int target){
        HashMap<Integer,Integer> hash = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            int sub = target - arr[i];
            if(hash.containsKey(sub)){//因为是边装边找，所以不需要判断是否在数组的同一个位置
                return new int[] {hash.get(sub),i};
            }else{
                hash.put(arr[i],i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {4,5,15,10,6,2};
        int target = 10;
        System.out.println("暴力法："+ Arrays.toString(stupid(arr,target)));
        System.out.println("哈希表法1："+ Arrays.toString(hashTest1(arr,target)));
        System.out.println("哈希表法2："+ Arrays.toString(hashTest2(arr,target)));
    }
}
