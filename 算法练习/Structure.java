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

//*******************************������ģ��ջ�ṹ��********************************
class Stack
{
	int[] arr;
	int index= -1 ;//��ǰָ��λ��
	public Stack(int size)
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