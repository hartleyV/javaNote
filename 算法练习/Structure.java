/**
*实现栈：先进后出
*实现队列：先进先出
*实现用栈结构模拟队列 & 用队列结构模拟栈结构
*@author Hartley
*/
class Structure 
{

	public static void main(String[] args) 
	{
		QueueTest s = new QueueTest(3);
		s.push(7);
		s.push(3);
		s.push(-8);
		s.push(0);
		System.out.println(s.poll() );
		System.out.println(s.poll() );
		System.out.println(s.poll() );
		System.out.println(s.poll() );
		System.out.println(s.peek() );

	}
}

//*******************************【数组模拟栈结构】********************************
class StackTest
{
	int[] arr;
	int index= -1 ;//当前指针位置
	public StackTest(int size)
	{
		arr = new int[size];
	}

	//查看栈顶元素
	public int peek()
	{
		return arr[index];
	}

	//把元素压栈
	public void push(int ele)
	{
		arr[++index] = ele;

	}

	//把元素从栈中弹出
	public int poll()
	{
		if(index<0)
		{
			System.out.println("error");
		}
		return arr[index--];
	}

}

//*******************************【数组模拟队列结构】********************************
class QueueTest
{
	int size;
	int start=0;
	int end=0;
	int[] arr;
	public QueueTest(int size)
	{
		this.size = size;
		arr = new int[size];
	}
	public int peek()
	{
		return arr[start];
	}
	//添加元素时，start end+1
	public void push(int num)
	{
		end = getPos(arr,end);
		arr[end++] = num;
		
	}
	//弹出元素时，start+1 end
	public int poll()
	{
		start = getPos(arr,start);
		return arr[start++];
	}
	//判断是否超出数组大小，循环start、end指针
	public int getPos(int[] arr,int p)
	{
		if(p>=arr.length)
		{
			p = 0;
		}

		return p;
	}
}