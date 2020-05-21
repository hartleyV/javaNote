import java.util.concurrent.*;//Callable的包装类FutureTask
import java.util.*;
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
     |---【同步代码块】：
	 		     |---目的：阻止线程对同一共享资源进行并发访问-->将共享变量作为同步监视器
	 		     |---用法：阻synchronized(obj){同步代码块}---obj为同步监视器
	 		     |---流程：加锁同步监视器---修改---释放
     |---【同步方法】：
	 		     |---用法：在方法前加 synchronized修饰符，会隐式将 this 作为同步监视器，
				 				 即将调用该方法的对象锁定起来--然后修改对象实例变量---方法结束释放锁
	 		     |---特点：对修改多线程共享实例变量的方法进行同步---线程安全类
				 				 每个线程都可以正确调用该类的方法，并且对象保持合理状态
			 	 |---不可变类因为对象状态不可变，一定是线程安全的，可变类则应创建两个版本（单线程不同步，多线程同步）
     |---释放同步监视器的情况：
	 		     |---调用监视器对象的wait方法--当前线程暂停（sleep、yield不会释放，其他进程调用
					  该进程的suspend方法，该进程不会释放锁--应避免使用suspend resume控制线程）
				 |---同步代码块、方法 执行完毕 OR 抛出异常错误 OR return break中止
     |---【同步锁】：
	 		     |---用法：加锁lock后，当获取多个锁，应按相反的加锁顺序释放锁unlock
	 		     |---相比synchronized特点
					   |--灵活，可重入锁可以多次加锁，
					   |--提供了非块结构的tryLock方法，传入时间参数 获取超时失效锁tryLock(long,TimeUnit)
						   获取可中断锁的lockInterruptibly					   	  
	 		     |---根接口
				       |--Lock接口--实现类ReentrantLock可重入锁--一个线程可对已上重入锁的继续加锁（常用）
					   |--ReadWriterLock接口--ReentrantReadWriteLock
					   |--Java8新增StampedLock类，大部分可以替代以上两个，有Writing、Reading、ReadOptimistic三种模式的锁
     |---死锁：两线程相互**等待对方释放同步监视器**时(注意并非程序堵塞就是死锁）--见DeadLockTest.java
*【线程通信】
	 |---为保证线程间协调运行，在进程不退出synchronized下，其他进程能够访问共享变量
     |---【传统方法】---synchronized
	        可以用 同步代码块的【同步监视器】/同步方法的【this】 调用Object类中--见ThreadExercise2.java
			|--wait--有带时间参数的--不带参数表示当前线程一直等待，直至其他线程notify/notifyAll
						 同时会释放当前线程对同步监视器的锁定，（线程被放到对象等待池，notify后对象被放到锁标志等待池）
			|--notify--随机唤醒等待线程中的某一个线程，且只当调用notify的线程释放锁定后（wait后）
							该线程才会被唤醒
			|--notifyAll--唤醒所有等待中断线程
     |---【Condition】---Lock
	 		用Lock对象充当同步监视器，Condition对象来暂停、唤醒指定线程（类似上面的）
			Condition condition = lock.newCondition();//将Condition对象绑定在该lock对象上
			|--await--让当前线程等待，直到用Condition对象调用signal/signalAll__还有很多变形：awaitUntil、awaitNanos(long)
			|--signal--唤醒某一个线程，且只当调用线程释放Lock对象锁定(await后)，该线程才被唤醒			
			|--signalAll--唤醒在此Lock对象上等待的所有线程			
     |---【阻塞队列-BlockingQueue】
	 		BlockingQueue接口的put、take为阻塞式方法，当加入/删元素时队列满/空-则阻塞该线程
			|--实现类：
				|--ArrayBlockingQueue：基于数组实现的阻塞队列
				|--LinkedBlockingQueue：基于链表
				|--PriorityBlockingQueue：类似PriorityQueue，会自动排序--
					通过对象的Comparable自然排序或Comparator定制排序
				|--SynchronousQueue：同步队列，存取必须交替进行
				|--DelayQueue：按Delay接口的getDelay排序（存入元素必须实现Delay接口并实现getDelay）
*【线程组】
     |---对一批线程分类管理，程序可直接操控线程组；如果没有显示指定线程属于哪个线程组，
	 	  则该线程归为创建它的线程所在的线程组ThreadGroup
     |---创建线程时可指定new Thread(ThreadGroup group,Runnable target,String name);
     |---创建线程组：new Thread(ThreadGroup, String name);//父进程组可空
		  |---常用方法
			 |---activeCount：线程组中活动的线程数
			 |---interupt：中断线程组中所有线程
			 |---setPriority(int)：设置线程组中线程的最大优先级
			 |---setDaemon(boolean)：设置线程组中进程为后台线程（当线程组最后一个后台线程没了，线程组自动销毁）
			 |---isDaemon()：是否后台线程组
			 |---void uncaughtException(Thread,Throwable)：处理组内没处理异常的任意线程？？怎么用
			      ThreadGroup实现了Thread.UncaughtExceptionHandler静态接口的方法（如上），
				  所有每个线程组被当作默认的异常处理器（当组内线程不处理、抛出异常时）
*【异常处理器】
			 |---Thread类提供两个方法设置异常处理器：
			 		静态：static setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh) //给线程类的所有线程实例设置默认的异常处理器
					setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh)给调用该方法的线程实例设置异常处理器
			 |---选择用异常处理器流程：
			 		某个线程抛出异常，先看该线程有没有设置异常处理器
					如果没有，则调用该线程所在组的uncaughtException方法（线程组有父进程组则
					调用父进程组的uncaughtException方法）；否则看该线程所在类有无设置默认异
					常处理器；（如果异常对象是ThreadDeath的对象，则不处理）最后打印跟踪信息
					到错误输入流，结束线程。
			 |---与catch区别：catch捕获异常后，异常不会向上传给上一级调用者，而异常处理器会
*【线程池】
     |---用来做啥：程序要用大量生存周期短的线程时，通过线程池执行任务节省启动新线程的成本；
							有效控制并发线程数量（防止并发线程过多，导致性能下降，JVM崩溃）
     |---怎么创建：用【Executors】工厂类的静态工厂方法创建
	       |---创建【ExecutorService】线程池
		   			 newCachedThreadPool缓存功能的线程池，创建线程后放入池中缓存
					 newFixedThreadPool(int)创建可重用固定线程的池--newSingleThreadExecutor()单线程
					 newWorkingSteelPool(int并行级别)创建足够多的线程池以支持并行级别，参数留空则根据几核的CPU设置级别
	       |---创建【ScheduledExecutorService】线程池
		   			 newScheduledThreadPool(int池中保存的线程数)可以指定延时后执行任务，
					 创建指定线程数的池；newSingleScheduledThreadPool创建单个线程可以延时的池
     |---怎么用：		
		ExecutorSerivice：submit(Runnable、Callable对象)将这两个对象提交给线程池，线程池在
									有空闲线程时执行该对象任务，run结束后返回值Future（isDone、isCancelled方法获取线程执行状态）
		ScheduledExecutorSerivice：
			*schedule(Runnable/Callable,long delay,TimeUnit)指定任务在延迟后执行，返回ScheduledFuture	
			*scheduleAtFixedRate(Runnable,intialDelay,period,TimeUnit)按指定频率持续重复执行，
		      intialDelay后开始执行，intialDelay+period后重复执行。。。
			*scheduleAtFixedDely((Runnable,intialDelay,delay,TimeUnit)每次执行中止和下一次开始
			  都存在固定的延迟delay
     |---拢共四点步骤
	             |---创建线程池	 		
	             |---创建Runnable/Callable对象，作为执行任务 		
	             |---加任务submit给线程池（用线程池对象调用submit方法）--不需要Thread start什么的，线程池会自动执行	
	             |---不提交任务，则用shutdown关闭线程池	
*Java7 ForkJoinPool(ExecutorService实现类）：利用多核系统并行计算能力，大任务拆除小任务
				在各个processor上运算，最后合并返回结果（任务需要可分解ForkJoinTask）
*【相关类】
     |---ThredLocal<T>:用于隔离多个线程间的数据共享（同步是为解决多线程对共享资源并发访问）
									通过将并发访问的共享资源复制多份，每个进程都有资源副本，所以不需要同步
	             |---使用：将其作为共享变量的类型，在赋值、取值时用其set(T), T get()方法	 								
     |---Collections静态方法：包装集合为线程安全的集合
	 		synchronizedCollection(Collection<>)--包装为线程安全的集合
			synchronizedXXX--Set、SortSet、List、Map、SortMap
     |---线程安全的集合类
	      ConCurrentXXX支持并发多线程写入访问
		  （多线程访问以公共集合可以ConcurrentLinkedQueue--多线程高效访问，不可含null）
		  （多线程写入可用ConcurrentHashMap---支持16线程同时写入）
		  CopyOnWriteXXX（xxArraySet底层封装了xxArrayList）--适合读操作，因写操作会频繁复制数组，性能差

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

	//异常处理器
	public static void exhandleTest()
	{
		//【2】线程所在组的uncaughtException方法
		ThreadGroup group = Thread.currentThread().getThreadGroup();
		//【3】线程类设置的默认异常处理器
		Thread.setDefaultUncaughtExceptionHandler( (thread,throwable)->
					System.out.println("线程默认异常处理器："+throwable.getMessage()+"异常"));
		//【1】线程直接对应的异常处理器
		
		Thread.currentThread().setUncaughtExceptionHandler( (thread,throwable)->
					System.out.println(thread.getName()+"线程产生了"+throwable.getMessage()+"异常"));
		/**/
		int a = 9;
		int b = a/0;//算术异常

		System.out.println("over");
	}

	//线程池
	public static void ThreadPoolTest()
	{
		ExecutorService pool = Executors.newFixedThreadPool(6);//创建固定大小的线程池
		Runnable task = ()->{
			for (int i =0 ; i<100 ;i++ )
			{
				System.out.println(Thread.currentThread().getName()+"  : "+i);
				//从线程名字中带pool可知该线程为线程池中的~
			}
		};

		Future result = pool.submit(task);//给线程池提交任务
		pool.submit(task);
		
		
		pool.shutdown();//关闭线程池！！！
		System.out.println("task完成了没？"+result.isDone());//false---实时判断线程完成没
		try
		{
			Thread.sleep(1000);
			System.out.println("task完成了没？"+result.isDone());//true
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
	}

	//Java7的Fork JoinPool
	//打印任务-无返回值
	public static void forkJoinPoolTest() throws InterruptedException
	{
		ForkJoinPool pool = new ForkJoinPool();//创建ForkJoin池

		PrintTask task = new PrintTask(0,300);//创建任务打印0-300
		pool.submit(task);
		
		pool.awaitTermination(3,TimeUnit.SECONDS);//shutdown前加这个用来设定时间等待ForkJoin池跑完任务
		pool.shutdown();//关闭池
	}

	//add任务-有返回值
	public static void forkJoinPoolTest2() throws InterruptedException,ExecutionException
	{
		int[] arr = new int[300];
		Random random = new Random();//随机赋值
		int total = 0;//边赋值边计算总和
		for (int i=0;i<300 ;i++ )
		{
			arr[i] = random.nextInt(20);//20以内随机数
			total+=arr[i];
		}
		System.out.println("arr正确总和为："+total);

		//创建ForkJoin池
		//ForkJoinPool pool = new ForkJoinPool();
		ForkJoinPool pool = ForkJoinPool.commonPool();//创建通用池
		AddTask task = new AddTask(arr,0,arr.length);//创建求和任务
		Future<Integer> future = pool.submit(task);//任务提交给pool
		System.out.println("并行计算结果为: "+future.get());

		pool.shutdown();//关闭池
	}
	//************程序入口***************
	public static void main(String[] args) throws Exception
	{
		//threadTest1();
		//threadTest2();
		//daemonTest();
		//sleepTest();
		//exhandleTest();
		//ThreadPoolTest();
		//forkJoinPoolTest();
		forkJoinPoolTest2();
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

//并行打印任务--无返回值，用fork并行计算就可=============================
class PrintTask extends RecursiveAction//继承可分解任务的抽线类，重写compute方法
{
	private final static int THRESHOLD = 50;//每个并行线程只打印50条
	private int start;
	private int end;
	public PrintTask(int start,int end)
	{
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute()
	{
		//只有当start与end在50以内才打印
		if (end - start < THRESHOLD)
		{
			for (int i = start ;i<end ;i++ )
			{
				System.out.println(Thread.currentThread().getName() +" : "+i);
			}			
		}else{
			//否则递归创建对象
			int mid = (start + end)>>1;
			PrintTask left = new PrintTask(start,mid);
			PrintTask right = new PrintTask(mid,end);

			left.fork();//并行计算小任务
			right.fork();
		}
	}

}

//并行加和任务，用fork并行计算 + join合并计算结果==========================
class AddTask extends RecursiveTask<Integer>//注意并行计算有返回值需加泛型
{
	private final static int THRESHOLD = 50;//每个并行线程只加50条
	private int start;
	private int end;
	private int[] arr;
	public AddTask(int[] arr,int start,int end)
	{
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute()//有返回值咯~
	{
		//只有当start与end在50以内才加和
		if (end - start < THRESHOLD)
		{
			int sum = 0;
			for (int i = start ;i<end ;i++ )
			{
				sum+=arr[i];
			}			
			return sum;

		}else{
			//否则递归创建对象
			int mid = (start + end)>>1;
			AddTask left = new AddTask(arr,start,mid);//此处mid不会被取
			AddTask right = new AddTask(arr,mid,end);

			left.fork();//并行计算小任务
			right.fork();

			return left.join() + right.join();
		}
	}

}
