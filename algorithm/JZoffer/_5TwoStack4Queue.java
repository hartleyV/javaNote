package algorithm.JZoffer;

import java.util.Stack;

/**
 * description：
 *两个栈模拟队列：
    push：push到栈1
    pop：判断栈2-为空则一次性由栈1导到栈2；否则栈二直接pop
 * @author Hartley
 * date： 2020/8/10
 */
public class _5TwoStack4Queue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(!stack2.empty()){
            return stack2.pop();
        }else{
            while(!stack1.empty()){
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }


    }
}
