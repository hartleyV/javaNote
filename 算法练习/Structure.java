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
		Stack s = new Stack(6);
		s.push(7);
		s.push(3);
		s.push(-8);
		s.push(0);
		System.out.println(s.poll() );
		System.out.println(s.poll() );
		System.out.println(s.poll() );
		System.out.println(s.poll() );

	}
}

//*******************************【数组模拟栈结构】********************************
class Stack
{
	int[] arr;
	int index= -1 ;//当前指针位置
	public Stack(int size)
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