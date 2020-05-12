/**
*ThreadGroup--创建与使用
*@author Hartley
*@version 1.0.0
*/

class  ThreadGroupTest
{
	//************程序入口***************
	public static void main(String[] args) 
	{
		ThreadGroup defaultGroup = Thread.currentThread().getThreadGroup();
		System.out.println("初始，默认组有几个线程："+defaultGroup.activeCount());
		System.out.println("默认组叫什么："+defaultGroup.getName());
		System.out.println("默认组是否为后台组："+defaultGroup.isDaemon());
		A a = new A();
		a.start();
		System.out.println("启动线程a后，默认组有几个活动线程："+defaultGroup.activeCount());
		defaultGroup.setDaemon(true);//设置线程组为后台组
		System.out.println("设置后，默认组是否为后台组："+defaultGroup.isDaemon());

		ThreadGroup group = new ThreadGroup("新线程");
		A a2 = new A(group,"2");
		A a3 = new A(group,"3");
		System.out.println("新线程所在组有几个运行的线程："+group.activeCount());
		a2.start();
		a3.start();
		System.out.println("新线程所在组有几个运行的线程："+group.activeCount());
		
	}
}

//定义线程类A、B
class A extends Thread
{
	public A(){}
	public A (ThreadGroup group,String name)
	{
		super(group,name);//调用父类Thread的构造器，也就是说将当前线程归为group线程组
	}

	public void run()
	{
		for (int i = 0;i<20 ;i++ )
		{
			//System.out.println(getName()+" :当前计数= "+i);
		}
	}
}
