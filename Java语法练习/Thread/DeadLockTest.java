/**
*���������߳��໥�ȴ��Է��ͷ�ͬ��������ʱ
ͨ��sleep--��A˯����B����A��ͬ��������Ҫ��A�����������A����������������B�����ͷ�
A�����ٵ���B��ͬ����������Ҫ��B���������ҲҪ�ȼ���B�õ��ͷţ������--
*@author Hartley
*@version 1.0.0
*/

class  DeadLockTest implements Runnable
{
	A a;
	B b;
	public DeadLockTest()
	{
		Thread.currentThread().setName("������");
		a = new A();
		b = new B();
	}

	//ʵ��run����
	public void run()
	{
		a.init(b);//��3���������̣߳�A��initΪͬ���������Ὣ����a��ס
	}
	//************�������***************
	public static void main(String[] args) 
	{
		DeadLockTest target = new DeadLockTest();
		Thread sub = new Thread(target,"���߳�");
		sub.start();
		target.b.init(target.a);//��1��B��initΪͬ���������Ὣ����b��ס
	}
}

class A 
{
	public synchronized void init(B b)//Ҳ��Ϊͬ����������
	{
		System.out.println(Thread.currentThread().getName()+"ִ����A��init����");
/*
		try
		{
			Thread.sleep(200);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
*/		
		b.t();//��4����Ҫ�õ�b�ļ���������������˯������û���ͷţ��������߳�����ing
		//��ʱa�ļ�����Ҳû���ͷš�ע�⡿���b��˯���Ǹ�BҪ��ͬһ������!!
	}
	//ͬ������
	public synchronized void t()
	{
		System.out.println(Thread.currentThread().getName()+"A��ͬ������");
	}
}

class B 
{
	public synchronized void init(A a)
	{
		System.out.println(Thread.currentThread().getName()+"ִ����B��init����");
		
		try
		{
			Thread.sleep(200);//��2�����߳�˯���ˣ���ִ�����̵߳�run������
			//ע���ʱ����B����û���ͷ�
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		a.t();//��5�����߳�˯���ˣ�A��t����Ϊͬ������������Ҫ�õ�A�����������������Aû���ͷ�
		//�������߳�Ҳ��������������ȴ��Է��ͷ�����ͬ���������������������� over~
	}
	//ͬ������
	public synchronized void t()
	{
		System.out.println(Thread.currentThread().getName()+"B��ͬ������");
	}
}