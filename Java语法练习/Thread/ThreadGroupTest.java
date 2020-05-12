/**
*ThreadGroup--������ʹ��
*@author Hartley
*@version 1.0.0
*/

class  ThreadGroupTest
{
	//************�������***************
	public static void main(String[] args) 
	{
		ThreadGroup defaultGroup = Thread.currentThread().getThreadGroup();
		System.out.println("��ʼ��Ĭ�����м����̣߳�"+defaultGroup.activeCount());
		System.out.println("Ĭ�����ʲô��"+defaultGroup.getName());
		System.out.println("Ĭ�����Ƿ�Ϊ��̨�飺"+defaultGroup.isDaemon());
		A a = new A();
		a.start();
		System.out.println("�����߳�a��Ĭ�����м�����̣߳�"+defaultGroup.activeCount());
		defaultGroup.setDaemon(true);//�����߳���Ϊ��̨��
		System.out.println("���ú�Ĭ�����Ƿ�Ϊ��̨�飺"+defaultGroup.isDaemon());

		ThreadGroup group = new ThreadGroup("���߳�");
		A a2 = new A(group,"2");
		A a3 = new A(group,"3");
		System.out.println("���߳��������м������е��̣߳�"+group.activeCount());
		a2.start();
		a3.start();
		System.out.println("���߳��������м������е��̣߳�"+group.activeCount());
		
	}
}

//�����߳���A��B
class A extends Thread
{
	public A(){}
	public A (ThreadGroup group,String name)
	{
		super(group,name);//���ø���Thread�Ĺ�������Ҳ����˵����ǰ�̹߳�Ϊgroup�߳���
	}

	public void run()
	{
		for (int i = 0;i<20 ;i++ )
		{
			//System.out.println(getName()+" :��ǰ����= "+i);
		}
	}
}
