import java.util.Stack;
import java.io.*;
/**
*堆栈练习
*案例【1】：要求在实现一特殊栈的同时，可以在O(1)时间内求出栈中最小值
*思路：以空间换时间，增加一最小栈，每次放入当前栈中最小的元素
（push一元素会与最小栈栈顶元素比较，若比其小则放入最小栈，若比其大则重复放最小栈栈顶元素

*@author Hartley
*@version 1.0.0
*/

class  StackTest
{

	//************程序入口***************
	public static void main(String[] args) 
	{
		MinStack stack = new MinStack();
		stack.push(3);
		stack.push(0);
		stack.push(5);
		stack.push(2);
		stack.push(2);

		System.out.println(stack.peek());
		System.out.println(stack.getMin());
	}
}

//实现特殊栈
class MinStack
{
	private Stack<Integer> stack ;//创建两个栈，一个正常压入元素的栈
	private Stack<Integer> minStack;//一个压入元素栈中最小值的栈

	public MinStack()
	{
		stack = new Stack<>();
		minStack = new Stack<>();
	}
	
	//peek取栈顶元素
	public Integer peek()
	{
		return stack.peek();
	}
	//压入元素
	public void push(Integer ele)
	{
		stack.push(ele);
		//向minStack加
		if (minStack.isEmpty())//如果最小栈还没有元素---直接加元素
		{
			minStack.push(ele);
		}else{
			if ( ele.compareTo( minStack.peek() ) <= 0 )//最小栈栈顶元素比 新加入的元素大时
			{
				minStack.push(ele);//压入新加入的元素
			}else{
				minStack.push( minStack.peek() );//否则压入自己的栈顶元素
			}
		}
	}

	//弹出栈顶元素
	public Integer pop() 
	{
		if (stack.empty())
		{
			throw new IndexOutOfBoundsException("栈中元素为空！！不能再掏空了");
		}
		minStack.pop();
		return stack.pop();
	}

	//获取当前堆栈中最小值
	public Integer getMin()
	{
		return minStack.peek();
	}
}
