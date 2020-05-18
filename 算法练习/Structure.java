import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/**
*实现栈：先进后出
*实现队列：先进先出
*实现用栈结构模拟队列 & 用队列结构模拟栈结构
*@author Hartley
*/
class Structure 
{

	//程序入口
	public static void main(String[] args) 
	{
		StackToQueueTest s = new StackToQueueTest();
		//StackTest s = new StackTest(5);
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);

		System.out.println(s.poll() );
		
		System.out.println(s.poll() );
		s.push(5);
		s.push(6);
		System.out.println(s.peek() );
		System.out.println(s.poll() );
		System.out.println(s.poll() );
		System.out.println(s.peek() );
		System.out.println(s.poll() );
		/**/
		

	}
}

//*******************************【模拟栈结构】********************************
//思路：Stack-先进后出（基于数组，push后指针++，pop后指针--，peek后读取指针+1的值）
class StackTest1
{
	int[] arr;
	int index= 0 ;//当前指针位置
	public StackTest1(int size)
	{
		arr = new int[size];
	}

	//查看栈顶元素
	public Integer peek()
	{
		//判断如果堆栈为空--指针小于堆栈大小，则报错
		if(index == 0)
		{
			return null;
			//throw new ArrayIndexOutOfBoundsException("the stack is empty!");
		}
		return arr[index-1];
	}

	//把元素压栈
	public void push(int ele)
	{
		//判断如果指针超出堆栈大小，则报错
		if(index == arr.length)
		{
			throw new ArrayIndexOutOfBoundsException("the stack is full!");
		}

		arr[index++] = ele;

	}

	//把元素从栈中弹出
	public int pop()
	{
		if(index<0)
		{
			throw new ArrayIndexOutOfBoundsException("the stack is empty!");
		}
		return arr[--index];
	}

}

//*******************************【模拟队列结构】********************************
//思路：Queue-先进先出（基于数组，两个指针start、end；当前队列元素个数size）
//push后end指针++、size++；pop后start指针++、size--，peek后读取指针start位置的值）
class QueueTest
{
	Integer size;//解决队列结构的关键--start、end分别与size耦合
	Integer start;
	Integer end;
	Integer[] arr;
	public QueueTest(int initSize)
	{
		if (initSize<0)
		{
			throw new IllegalArgumentException("The Queue init size is less than zero");
		}
		
		arr = new Integer[initSize];
		size = 0;
		start =0;
		end = 0;
		
	}
	public Integer peek()
	{
		if (size==0)
		{
			return null;
		}
		return arr[ start ];
	}
	//添加元素时，end+1
	public void push(int num)
	{
		if(size==arr.length)
		{
			throw new ArrayIndexOutOfBoundsException("the Queue is full !");
		}
		arr[end] = num;
		end = getPos(end);//end指针移到当前末尾元素的下一个位置
		size++;
		
	}
	//弹出元素时，start+1 
	public Integer poll()
	{
		if(size==0)
		{
			throw new ArrayIndexOutOfBoundsException("the Queue is empty!");
		}
		int temp = start;//准备要弹出的start指针
		start = getPos(start);//start指针向前移动一格
		size--;//对应队列元素size减一
		//System.out.print("Start:"+start);
		return arr[temp];
	}
	//判断是否超出数组大小，循环start、end指针
	public int getPos(int p)
	{
		if(p==arr.length-1)
		{
			p = 0;
		}else{
			p++;
		}

		return p;
	}
}

//*******************************【栈模拟队列结构】********************************
//思路：用两个栈，第一个栈向第二个栈弹元素，剩第一个栈中最后一个弹出的即为模拟队列的poll值
//实现：pop栈&push栈（push到pop栈导元素：一次性倒完；pop栈有元素不要倒） pop 栈& help栈（不可！）
class StackToQueueTest
{
	private Stack<Integer> pushStack;
	private Stack<Integer> popStack;
	public StackToQueueTest()
	{
		pushStack = new Stack<Integer>();
		popStack = new Stack<Integer>();
	}

	public void push(Integer num)
	{
		pushStack.push(num);
	}

	public Integer poll()
	{
		if (pushStack.isEmpty() && popStack.isEmpty() )
		{
			throw new RuntimeException("the Queue is empty");
		}
		pushToPop();
		return popStack.pop();
	}
	public Integer peek()
	{
		if (pushStack.isEmpty() && popStack.isEmpty() )
		{
			throw new RuntimeException("the Queue is empty");
		}
		pushToPop();
		return popStack.peek();
	}

	//从pushStack倒入popStack需要满足popStack为空，且导入要一次性倒
	public void pushToPop()
	{
		if(popStack.isEmpty() )
		{
			while( !pushStack.isEmpty() )
			{
				popStack.push( pushStack.pop() );
			}
		}
	}
	
}
//思路2不可，跟与help栈互换后“队尾”元素在栈底，而用栈新添加的元素会直接放在“队首”元素上
/*
class StackToQueueTest2
{
	private Stack<Integer> stack;
	private Stack<Integer> help;
	public StackToQueueTest2()
	{
		stack = new Stack<Integer>();
		help = new Stack<Integer>();
	}

	public void push(Integer num)
	{
		stack.push(num);
	}

	public Integer poll()
	{
		if (stack.isEmpty()  )
		{
			throw new RuntimeException("the Queue is empty");
		}
		while(stack.size()!=1)
		{
			help.push( stack.pop());
		}
		int res = stack.pop();
		swap();
		return res;
	}
	public Integer peek()
	{
		if (stack.isEmpty()  )
		{
			throw new RuntimeException("the Queue is empty");
		}
		while(stack.size()!=1)
		{
			help.push( stack.pop());
		}
		int res = stack.pop();
		help.add(res);
		swap();
		return res;
	}

	//交换help与stack
	public void swap()
	{
		Stack<Integer> tmp = help;
		help = stack;
		stack = tmp;
	}
	
}
*/
//*******************************【队列模拟栈结构】********************************
//思路：两个队列，第一个队列向第二个队列弹元素，剩第一个队列中最后一个弹出的即为模拟栈的pop值
//队列可：与help栈互换后“队尾”元素在栈底，而用队列新添加的元素会直接放在“队尾”元素后！
class QueueToStackTest
{
	private Queue<Integer> queue;
	private Queue<Integer> help;
	public QueueToStackTest()
	{
		queue = new LinkedList<Integer>();
		help = new LinkedList<Integer>();
	}
	public void getQueue()
	{
		System.out.println(queue);
	}

	public void push(int num)
	{
		queue.add(num);
	}

	public Integer pop()
	{
		if (queue.isEmpty())
		{
			throw new RuntimeException("Stack is Empty");
		}
		while (queue.size() != 1 )
		{
			help.add( queue.poll() );//queue在poll时其size不断--
		}
		Integer res = queue.poll();
		swap();
		return res;
	}
	public Integer peek()
	{
		if (queue.isEmpty())
		{
			return null;
			//throw new RuntimeException("Stack is Empty");
		}
		while (queue.size() != 1 )
		{
			help.add( queue.poll() );//queue在poll时其size不断--
		}
		int res = queue.poll();
		help.add(res);//peek只是查看栈顶元素，不改变原有元素
		swap();
		return res;
	}

	//交换辅助队列与原始队列引用::目的1-获取当前队列，目的2-清空辅助队列
	public void swap()
	{
		Queue<Integer> temp = help;
		help = queue;
		queue = temp;		
	}
}