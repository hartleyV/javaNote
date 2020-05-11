import java.util.concurrent.*;//Callable的包装类FutureTask
/**
*【线程】
     |---进程：系统运行时多个进程并发执行，具有独立性--地址空间、动态性--生命周期、并发性
	      线程是进程的子执行单元（一个线程必有一个父进程，一进程可以至少有一个线程）
		  且子线程可以【共享】父进程的资源，子线程间是“抢占式”运行
	 |---优势：比进程划分尺度小→效率高，共享父进程资源→便于通信、创建多线程代价小	
	 |---创建线程
		 |---【Thread】
			  |---类---重写public void run()方法，创建线程类对象，用start()方法启动进程（此时线程对象时Thread对象，且负责执行的run方法也是该Thread的）
			  |---使用Thread创建线程，线程间**不可共享实例变量**
			  |---常用方法
						  Thread.currentThread()获取当前执行的线程对象
						  getName()
		 |---【Runnable】
			  |---接口--实现run方法，创建线程类对象，将其作为target创建Thread对象，start（此时真正的线程对象仍是Thread对象，但执行的是target的run方法）
			  |---**可以共享**同一target的实例变量
		 |---【Callable】
			  |---可用lambda表达式实现call方法获取Callable对象，再用FutureTask包装Callable对象，
			        获得target，创建Thread对象，start！
			  |---Runnable增强版：可以获取返回值，抛出异常			
			        |---FutureTask增加的方法--get方法--获取线程最后的返回值，为阻塞式方法，
					     会抛出中断异常 & 运行异常
*【生命周期】
     |---创建（New）：new线程（JVM为其分配内存
     |---就绪（Runnable）：线程start，JVM会为线程创建栈和程序计数器
	               （但不一定立即运行，如果想立即运行可以让当前进程睡觉Thread.sleep(1)
	 |---运行（Running)
	 |---阻塞（Blocking)
			  |---情况：
			        |---sleep方法---睡醒后到就绪状态
			        |---阻塞式方法---方法返回后到就绪状态
			        |---等等通知notify---得到通知后到就绪状态
			        |---试图获得其他线程正占用的同步监视器---获取后到就绪状态
			        |---suspend方法（容易死锁）---resume恢复后到就绪状态
	 |---死亡（Dead)
			  |---情况
					 |---call、run方法结束
					 |---抛出异常 OR 错误
					 |---调用stop方法（容易死锁）

			  |---isAlive方法---对新建、死亡的线程返回false
			  |---死亡的线程不可再start！否则抛出IllegalThreadStateException
*【线程控制】--方便的方法
     |---join线程：调用join方法后立马停下当前线程，并让join的线程先执行完
	                     （join方法还可设置join进程的最长执行时间，超过则不再等待）
     |---后台线程（DaemonThread）：调用setDaemon(True)//设置成后台进程，再start
															当前台进程死光了，后台进程也不苟活；
															isDaemon()判断当前线程是否为后台线程
     |---sleep线程：让调用sleep方法的线程进入阻塞状态，只有等醒过来该线程才有可能运行
	 						 会抛出中断异常（有更好移植性，控制并发首选sleep，而不是yield）
     |---让步线程：让调用yield方法的线程进入就绪状态，不会使该线程阻塞，线程调度器根据
							线程的优先级，让运行优先级大于等于该线程的被执行，否则还会执行该线程
     |---设置线程优先级：调用setPriority(int )设置、getPriority获取（优先级高，被执行概率大）
	 									MAX_PRIORITY==10;MIN_PRIORITY==1;NORM_PRIORITY==5;
*【线程安全】
     |---两个线程同时取钱，由于线程调用不确定性，很可能取多了--需要【同步监视器】的锁定
     |---【同步监视器】：
	 		     |---目的：阻止线程对同一共享资源进行并发访问-->将共享变量作为同步监视器
	 		     |---用法：阻synchronized(obj){同步代码块}---obj为同步监视器
	 		     |---流程：加锁同步监视器---修改---释放

*@author Hartley
*@version 1.0.0
*/

class  ThreadTest
{

	//【Thread、Runnable】线程测试 
	public static void threadTest1()
	{
		for (int i=0;i<100 ;i++ )
		{
			System.out.println(Thread.currentThread().getName() +" : "+i);//获取当前进程名

			//在i==20时启动两个线程
			if (i==20)
			{
				/*//启动Thread创建的线程
				new FirstThread().start();//启动第一个线程
				new FirstThread().start();//启动第二个线程
				*/

				//用Runnable创建
				SecondThread target = new SecondThread();
				Thread t1 = new Thread(target,"线程一");
				t1.start();//不可直接调用run方法（否则只是调用普通方法-单线程）
				new Thread(target,"线程二").start();//支持命名~ 
			}
		}
	}
	
	//【Callable】线程测试---采用lambda表达式（匿名内部类）构建Callable
	public static void threadTest2()
	{
		
		//Future接口的实现类FutureTask包装Callable对象，然后就可以构造Thread执行
		FutureTask<Integer> target = new FutureTask<>( (Callable<Integer>)()->{//类型强转
			int i=0;
			for (;i<500 ;i++ )
			{
				System.out.println(Thread.currentThread().getName()+" : "+i);
			}
			return i;//call()方法可以用返回值
		});
		
		for (int i = 0;i<100 ;i++ )
		{
			System.out.println(Thread.currentThread().getName()+" : "+i);

			if (i==20)
			{
				//启动两个线程??实际运行只有一个线程在工作，，？？？
				new Thread(target,"线程一").start();//类似Runnable
				new Thread(target,"线程二").start();
			}
		}

		try
		{
			System.out.println("Call方法的返回值："+target.get());
			//get方法会抛出中断异常,以及运行异常，且get为阻塞式方法，没有数据就死等。。
		}
		catch (InterruptedException | ExecutionException e)
		{
			e.printStackTrace();
		}		
	}

	//线程控制方法 之 【join线程】
	public static void joinTest()
	{
		for (int i=0;i<100 ;i++ )
		{
			System.out.println(Thread.currentThread().getName() +" : "+i);//获取当前进程名

			//在i==20时启动两个线程
			if (i==20)
			{
				//用Runnable创建
				SecondThread target = new SecondThread();
				Thread t1 = new Thread(target,"线程一");
				t1.start();//不可直接调用run方法（否则只是调用普通方法-单线程）

				try
				{
					t1.join();//线程一插队，先完成线程一~---会有中断异常
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
				new Thread(target,"线程二").start();
				//支持命名~ 因为共享target的实例变量，所以线程一插队完成后就没线程二的事儿了
			}
		}
	}

	//【Daemon后台进程】
	public static void daemonTest()
	{
		for (int i=0;i<10 ;i++ )
		{
			System.out.println(Thread.currentThread().getName() +" : "+i);//获取当前进程名

			if (i==3)
			{
				FirstThread t = new FirstThread();//循环输出到900
				t.setDaemon(true);//将其设置为后台线程
				t.start();//启动该线程，就绪
				System.out.println("t是不是Daemon? "+t.isDaemon() );
				//后台线程因为前台进程的Dead而随之dead，没有输出到900
			}
		}
	}

	//【sleep进程+让步+设置线程优先级】
	public static void sleepTest()
	{
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);//将主线程优先级置为最高
		for (int i=0;i<100 ;i++ )
		{
			System.out.println(Thread.currentThread().getName() +" : "+i);//获取当前进程名

			if (i==20)
			{
				SecondThread target = new SecondThread();//循环输出到50-1
				Thread t1 = new Thread(target,"进程");
				t1.setPriority(Thread.MIN_PRIORITY);//将t1线程优先级置为最低
				t1.start();
				t1.yield();//让步一下~
				/*		
				try
				{
					t1.sleep(1000);//让线程睡一秒，可能抛出中断异常???进程执行完再sleep？？？
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				*/
			}
		}
	}
	//************程序入口***************
	public static void main(String[] args) 
	{
		//threadTest1();
		//threadTest2();
		//daemonTest();
		sleepTest();
	}
}

//自定义----【Thread线程类】=======================================
class FirstThread extends Thread
{
	private int i;//i为实例变量---进程间不共享~

	//重写Thread的run方法
	public void run()
	{
		for (;i<900 ;i++ )
		{
			System.out.println(this.getName() +" : "+i);//由于继承了Thread类，可以直接获取进程名
		}
	}
}


//自定义----【Runnable线程类】=======================================
class SecondThread implements Runnable
{
	private int i;//i为实例变量---因为是同一个target 所以共享~

	//重写Thread的run方法
	public void run()
	{
		for (;i<500 ;i++ )
		{
			System.out.println(Thread.currentThread().getName() +" : "+i);
			//用Runnable接口实现线程--只能用静态方法获取当前线程对象
		}
	}
}

