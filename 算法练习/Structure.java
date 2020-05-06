import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/**
*ʵ��ջ���Ƚ����
*ʵ�ֶ��У��Ƚ��ȳ�
*ʵ����ջ�ṹģ����� & �ö��нṹģ��ջ�ṹ
*@author Hartley
*/
class Structure 
{

	//�������
	public static void main(String[] args) 
	{
		StackToQueueTest2 s = new StackToQueueTest2();
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

//*******************************��ģ��ջ�ṹ��********************************
//˼·��Stack-�Ƚ�������������飬push��ָ��++��pop��ָ��--��peek���ȡָ��+1��ֵ��
class StackTest
{
	int[] arr;
	int index= 0 ;//��ǰָ��λ��
	public StackTest(int size)
	{
		arr = new int[size];
	}

	//�鿴ջ��Ԫ��
	public Integer peek()
	{
		//�ж������ջΪ��--ָ��С�ڶ�ջ��С���򱨴�
		if(index == 0)
		{
			return null;
			//throw new ArrayIndexOutOfBoundsException("the stack is empty!");
		}
		return arr[index-1];
	}

	//��Ԫ��ѹջ
	public void push(int ele)
	{
		//�ж����ָ�볬����ջ��С���򱨴�
		if(index == arr.length)
		{
			throw new ArrayIndexOutOfBoundsException("the stack is full!");
		}

		arr[index++] = ele;

	}

	//��Ԫ�ش�ջ�е���
	public int pop()
	{
		if(index<0)
		{
			throw new ArrayIndexOutOfBoundsException("the stack is empty!");
		}
		return arr[--index];
	}

}

//*******************************��ģ����нṹ��********************************
//˼·��Queue-�Ƚ��ȳ����������飬����ָ��start��end����ǰ����Ԫ�ظ���size��
//push��endָ��++��size++��pop��startָ��++��size--��peek���ȡָ��startλ�õ�ֵ��
class QueueTest
{
	Integer size;//������нṹ�Ĺؼ�--start��end�ֱ���size���
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
	//���Ԫ��ʱ��end+1
	public void push(int num)
	{
		if(size==arr.length)
		{
			throw new ArrayIndexOutOfBoundsException("the Queue is full !");
		}
		arr[end] = num;
		end = getPos(end);//endָ���Ƶ���ǰĩβԪ�ص���һ��λ��
		size++;
		
	}
	//����Ԫ��ʱ��start+1 
	public Integer poll()
	{
		if(size==0)
		{
			throw new ArrayIndexOutOfBoundsException("the Queue is empty!");
		}
		int temp = start;//׼��Ҫ������startָ��
		start = getPos(start);//startָ����ǰ�ƶ�һ��
		size--;//��Ӧ����Ԫ��size��һ
		//System.out.print("Start:"+start);
		return arr[temp];
	}
	//�ж��Ƿ񳬳������С��ѭ��start��endָ��
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

//*******************************��ջģ����нṹ��********************************
//˼·��������ջ����һ��ջ��ڶ���ջ��Ԫ�أ�ʣ��һ��ջ�����һ�������ļ�Ϊģ����е�pollֵ
//ʵ�֣�popջ&pushջ��push��popջ��Ԫ�أ�һ���Ե��ꣻpopջ��Ԫ�ز�Ҫ���� pop ջ& helpջ�����ɣ���
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

	//��pushStack����popStack��Ҫ����popStackΪ�գ��ҵ���Ҫһ���Ե�
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
//˼·2���ɣ�����helpջ�����󡰶�β��Ԫ����ջ�ף�����ջ����ӵ�Ԫ�ػ�ֱ�ӷ��ڡ����ס�Ԫ����
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

	//����help��stack
	public void swap()
	{
		Stack<Integer> tmp = help;
		help = stack;
		stack = tmp;
	}
	
}
*/
//*******************************������ģ��ջ�ṹ��********************************
//˼·���������У���һ��������ڶ������е�Ԫ�أ�ʣ��һ�����������һ�������ļ�Ϊģ��ջ��popֵ
//���пɣ���helpջ�����󡰶�β��Ԫ����ջ�ף����ö�������ӵ�Ԫ�ػ�ֱ�ӷ��ڡ���β��Ԫ�غ�
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
			help.add( queue.poll() );//queue��pollʱ��size����--
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
			help.add( queue.poll() );//queue��pollʱ��size����--
		}
		int res = queue.poll();
		help.add(res);//peekֻ�ǲ鿴ջ��Ԫ�أ����ı�ԭ��Ԫ��
		swap();
		return res;
	}

	//��������������ԭʼ��������::Ŀ��1-��ȡ��ǰ���У�Ŀ��2-��ո�������
	public void swap()
	{
		Queue<Integer> temp = help;
		help = queue;
		queue = temp;		
	}
}