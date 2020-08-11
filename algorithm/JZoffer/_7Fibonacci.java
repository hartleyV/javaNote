package algorithm.JZoffer;

import java.util.HashMap;

/**
    斐波拉契数列：递归耗时耗空间（可以用note改良-11ms
        但最好的还是迭代-10ms
 * @author Hartley
 * date： 2020/8/11
 */
public class _7Fibonacci {
    public int Fibonacci(int n) {
        //迭代写法
        if(n==0) return 0;
        if(n==1) return 1;

        int fn = 0,f1 = 0,f2=1;
        for(int i=2;i<=n;i++){
            fn = f1 + f2;
            f1 = f2;
            f2 = fn;
        }

        return fn;
        /*
        HashMap<Integer,Integer> note = new HashMap<>();

        return FibonacciWithNode(n,note);
        */
    }

    //改良版的递归（带着笔记的）
    public Integer FibonacciWithNode(Integer n, HashMap<Integer,Integer> note){
        if(n==0) return 0;
        if(n==1) return 1;

        if(!note.containsKey(n-1)){
            note.put(n-1,FibonacciWithNode(n-1,note));
        }

        if(!note.containsKey(n-2)){
            note.put(n-2,FibonacciWithNode(n-2,note));
        }

        return note.get(n-1)+note.get(n-2);
    }
}
