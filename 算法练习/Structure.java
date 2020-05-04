/**
*ʵ��ջ���Ƚ����
*ʵ�ֶ��У��Ƚ��ȳ�
*ʵ����ջ�ṹģ����� & �ö��нṹģ��ջ�ṹ
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

//*******************************������ģ��ջ�ṹ��********************************
class StackTest
{
	int[] arr;
	int index= -1 ;//��ǰָ��λ��
	public StackTest(int size)
	{
		arr = new int[size];
	}

	//�鿴ջ��Ԫ��
	public int peek()
	{
		return arr[index];
	}

	//��Ԫ��ѹջ
	public void push(int ele)
	{
		arr[++index] = ele;

	}

	//��Ԫ�ش�ջ�е���
	public int poll()
	{
		if(index<0)
		{
			System.out.println("error");
		}
		return arr[index--];
	}

}

//*******************************������ģ����нṹ��********************************
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
	//���Ԫ��ʱ��start end+1
	public void push(int num)
	{
		end = getPos(arr,end);
		arr[end++] = num;
		
	}
	//����Ԫ��ʱ��start+1 end
	public int poll()
	{
		start = getPos(arr,start);
		return arr[start++];
	}
	//�ж��Ƿ񳬳������С��ѭ��start��endָ��
	public int getPos(int[] arr,int p)
	{
		if(p>=arr.length)
		{
			p = 0;
		}

		return p;
	}
}