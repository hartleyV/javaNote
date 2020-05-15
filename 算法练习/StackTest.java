import java.util.Stack;
import java.io.*;
/**
*��ջ��ϰ
*������1����Ҫ����ʵ��һ����ջ��ͬʱ��������O(1)ʱ�������ջ����Сֵ
*˼·���Կռ任ʱ�䣬����һ��Сջ��ÿ�η��뵱ǰջ����С��Ԫ��
��pushһԪ�ػ�����Сջջ��Ԫ�رȽϣ�������С�������Сջ������������ظ�����Сջջ��Ԫ��

*@author Hartley
*@version 1.0.0
*/

class  StackTest
{

	//************�������***************
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

//ʵ������ջ
class MinStack
{
	private Stack<Integer> stack ;//��������ջ��һ������ѹ��Ԫ�ص�ջ
	private Stack<Integer> minStack;//һ��ѹ��Ԫ��ջ����Сֵ��ջ

	public MinStack()
	{
		stack = new Stack<>();
		minStack = new Stack<>();
	}
	
	//peekȡջ��Ԫ��
	public Integer peek()
	{
		return stack.peek();
	}
	//ѹ��Ԫ��
	public void push(Integer ele)
	{
		stack.push(ele);
		//��minStack��
		if (minStack.isEmpty())//�����Сջ��û��Ԫ��---ֱ�Ӽ�Ԫ��
		{
			minStack.push(ele);
		}else{
			if ( ele.compareTo( minStack.peek() ) <= 0 )//��Сջջ��Ԫ�ر� �¼����Ԫ�ش�ʱ
			{
				minStack.push(ele);//ѹ���¼����Ԫ��
			}else{
				minStack.push( minStack.peek() );//����ѹ���Լ���ջ��Ԫ��
			}
		}
	}

	//����ջ��Ԫ��
	public Integer pop() 
	{
		if (stack.empty())
		{
			throw new IndexOutOfBoundsException("ջ��Ԫ��Ϊ�գ����������Ϳ���");
		}
		minStack.pop();
		return stack.pop();
	}

	//��ȡ��ǰ��ջ����Сֵ
	public Integer getMin()
	{
		return minStack.peek();
	}
}
