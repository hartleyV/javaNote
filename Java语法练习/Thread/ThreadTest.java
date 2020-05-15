import java.util.concurrent.*;//Callable�İ�װ��FutureTask
import java.util.*;
/**
*���̡߳�
     |---���̣�ϵͳ����ʱ������̲���ִ�У����ж�����--��ַ�ռ䡢��̬��--�������ڡ�������
	      �߳��ǽ��̵���ִ�е�Ԫ��һ���̱߳���һ�������̣�һ���̿���������һ���̣߳�
		  �����߳̿��ԡ����������̵���Դ�����̼߳��ǡ���ռʽ������
	 |---���ƣ��Ƚ��̻��ֳ߶�С��Ч�ʸߣ�����������Դ������ͨ�š��������̴߳���С	
	 |---�����߳�
		 |---��Thread��
			  |---��---��дpublic void run()�����������߳��������start()�����������̣���ʱ�̶߳���ʱThread�����Ҹ���ִ�е�run����Ҳ�Ǹ�Thread�ģ�
			  |---ʹ��Thread�����̣߳��̼߳�**���ɹ���ʵ������**
			  |---���÷���
						  Thread.currentThread()��ȡ��ǰִ�е��̶߳���
						  getName()
		 |---��Runnable��
			  |---�ӿ�--ʵ��run�����������߳�����󣬽�����Ϊtarget����Thread����start����ʱ�������̶߳�������Thread���󣬵�ִ�е���target��run������
			  |---**���Թ���**ͬһtarget��ʵ������
		 |---��Callable��
			  |---����lambda���ʽʵ��call������ȡCallable��������FutureTask��װCallable����
			        ���target������Thread����start��
			  |---Runnable��ǿ�棺���Ի�ȡ����ֵ���׳��쳣			
			        |---FutureTask���ӵķ���--get����--��ȡ�߳����ķ���ֵ��Ϊ����ʽ������
					     ���׳��ж��쳣 & �����쳣
*���������ڡ�
     |---������New����new�̣߳�JVMΪ������ڴ�
     |---������Runnable�����߳�start��JVM��Ϊ�̴߳���ջ�ͳ��������
	               ������һ���������У�������������п����õ�ǰ����˯��Thread.sleep(1)
	 |---���У�Running)
	 |---������Blocking)
			  |---�����
			        |---sleep����---˯�Ѻ󵽾���״̬
			        |---����ʽ����---�������غ󵽾���״̬
			        |---�ȵ�֪ͨnotify---�õ�֪ͨ�󵽾���״̬
			        |---��ͼ��������߳���ռ�õ�ͬ��������---��ȡ�󵽾���״̬
			        |---suspend����������������---resume�ָ��󵽾���״̬
	 |---������Dead)
			  |---���
					 |---call��run��������
					 |---�׳��쳣 OR ����
					 |---����stop����������������

			  |---isAlive����---���½����������̷߳���false
			  |---�������̲߳�����start�������׳�IllegalThreadStateException
*���߳̿��ơ�--����ķ���
     |---join�̣߳�����join����������ͣ�µ�ǰ�̣߳�����join���߳���ִ����
	                     ��join������������join���̵��ִ��ʱ�䣬�������ٵȴ���
     |---��̨�̣߳�DaemonThread��������setDaemon(True)//���óɺ�̨���̣���start
															��ǰ̨���������ˣ���̨����Ҳ�����
															isDaemon()�жϵ�ǰ�߳��Ƿ�Ϊ��̨�߳�
     |---sleep�̣߳��õ���sleep�������߳̽�������״̬��ֻ�е��ѹ������̲߳��п�������
	 						 ���׳��ж��쳣���и�����ֲ�ԣ����Ʋ�����ѡsleep��������yield��
     |---�ò��̣߳��õ���yield�������߳̽������״̬������ʹ���߳��������̵߳���������
							�̵߳����ȼ������������ȼ����ڵ��ڸ��̵߳ı�ִ�У����򻹻�ִ�и��߳�
     |---�����߳����ȼ�������setPriority(int )���á�getPriority��ȡ�����ȼ��ߣ���ִ�и��ʴ�
	 									MAX_PRIORITY==10;MIN_PRIORITY==1;NORM_PRIORITY==5;
*���̰߳�ȫ��
     |---�����߳�ͬʱȡǮ�������̵߳��ò�ȷ���ԣ��ܿ���ȡ����--��Ҫ��ͬ����������������
     |---��ͬ������顿��
	 		     |---Ŀ�ģ���ֹ�̶߳�ͬһ������Դ���в�������-->�����������Ϊͬ��������
	 		     |---�÷�����synchronized(obj){ͬ�������}---objΪͬ��������
	 		     |---���̣�����ͬ��������---�޸�---�ͷ�
     |---��ͬ����������
	 		     |---�÷����ڷ���ǰ�� synchronized���η�������ʽ�� this ��Ϊͬ����������
				 				 �������ø÷����Ķ�����������--Ȼ���޸Ķ���ʵ������---���������ͷ���
	 		     |---�ص㣺���޸Ķ��̹߳���ʵ�������ķ�������ͬ��---�̰߳�ȫ��
				 				 ÿ���̶߳�������ȷ���ø���ķ��������Ҷ��󱣳ֺ���״̬
			 	 |---���ɱ�����Ϊ����״̬���ɱ䣬һ�����̰߳�ȫ�ģ��ɱ�����Ӧ���������汾�����̲߳�ͬ�������߳�ͬ����
     |---�ͷ�ͬ���������������
	 		     |---���ü����������wait����--��ǰ�߳���ͣ��sleep��yield�����ͷţ��������̵���
					  �ý��̵�suspend�������ý��̲����ͷ���--Ӧ����ʹ��suspend resume�����̣߳�
				 |---ͬ������顢���� ִ����� OR �׳��쳣���� OR return break��ֹ
     |---��ͬ��������
	 		     |---�÷�������lock�󣬵���ȡ�������Ӧ���෴�ļ���˳���ͷ���unlock
	 		     |---���synchronized�ص�
					   |--�������������Զ�μ�����
					   |--�ṩ�˷ǿ�ṹ��tryLock����������ʱ����� ��ȡ��ʱʧЧ��tryLock(long,TimeUnit)
						   ��ȡ���ж�����lockInterruptibly					   	  
	 		     |---���ӿ�
				       |--Lock�ӿ�--ʵ����ReentrantLock��������--һ���߳̿ɶ������������ļ������������ã�
					   |--ReadWriterLock�ӿ�--ReentrantReadWriteLock
					   |--Java8����StampedLock�࣬�󲿷ֿ������������������Writing��Reading��ReadOptimistic����ģʽ����
     |---���������߳��໥**�ȴ��Է��ͷ�ͬ��������**ʱ(ע�Ⲣ�ǳ����������������--��DeadLockTest.java
*���߳�ͨ�š�
	 |---Ϊ��֤�̼߳�Э�����У��ڽ��̲��˳�synchronized�£����������ܹ����ʹ������
     |---����ͳ������---synchronized
	        ������ ͬ�������ġ�ͬ����������/ͬ�������ġ�this�� ����Object����--��ThreadExercise2.java
			|--wait--�д�ʱ�������--����������ʾ��ǰ�߳�һֱ�ȴ���ֱ�������߳�notify/notifyAll
						 ͬʱ���ͷŵ�ǰ�̶߳�ͬ�������������������̱߳��ŵ�����ȴ��أ�notify����󱻷ŵ�����־�ȴ��أ�
			|--notify--������ѵȴ��߳��е�ĳһ���̣߳���ֻ������notify���߳��ͷ�������wait��
							���̲߳Żᱻ����
			|--notifyAll--�������еȴ��ж��߳�
     |---��Condition��---Lock
	 		��Lock����䵱ͬ����������Condition��������ͣ������ָ���̣߳���������ģ�
			Condition condition = lock.newCondition();//��Condition������ڸ�lock������
			|--await--�õ�ǰ�̵߳ȴ���ֱ����Condition�������signal/signalAll__���кܶ���Σ�awaitUntil��awaitNanos(long)
			|--signal--����ĳһ���̣߳���ֻ�������߳��ͷ�Lock��������(await��)�����̲߳ű�����			
			|--signalAll--�����ڴ�Lock�����ϵȴ��������߳�			
     |---����������-BlockingQueue��
	 		BlockingQueue�ӿڵ�put��takeΪ����ʽ������������/ɾԪ��ʱ������/��-���������߳�
			|--ʵ���ࣺ
				|--ArrayBlockingQueue����������ʵ�ֵ���������
				|--LinkedBlockingQueue����������
				|--PriorityBlockingQueue������PriorityQueue�����Զ�����--
					ͨ�������Comparable��Ȼ�����Comparator��������
				|--SynchronousQueue��ͬ�����У���ȡ���뽻�����
				|--DelayQueue����Delay�ӿڵ�getDelay���򣨴���Ԫ�ر���ʵ��Delay�ӿڲ�ʵ��getDelay��
*���߳��顿
     |---��һ���̷߳�����������ֱ�Ӳٿ��߳��飻���û����ʾָ���߳������ĸ��߳��飬
	 	  ����̹߳�Ϊ���������߳����ڵ��߳���ThreadGroup
     |---�����߳�ʱ��ָ��new Thread(ThreadGroup group,Runnable target,String name);
     |---�����߳��飺new Thread(ThreadGroup, String name);//��������ɿ�
		  |---���÷���
			 |---activeCount���߳����л���߳���
			 |---interupt���ж��߳����������߳�
			 |---setPriority(int)�������߳������̵߳�������ȼ�
			 |---setDaemon(boolean)�������߳����н���Ϊ��̨�̣߳����߳������һ����̨�߳�û�ˣ��߳����Զ����٣�
			 |---isDaemon()���Ƿ��̨�߳���
			 |---void uncaughtException(Thread,Throwable)����������û�����쳣�������̣߳�����ô��
			      ThreadGroupʵ����Thread.UncaughtExceptionHandler��̬�ӿڵķ��������ϣ���
				  ����ÿ���߳��鱻����Ĭ�ϵ��쳣���������������̲߳������׳��쳣ʱ��
*���쳣��������
			 |---Thread���ṩ�������������쳣��������
			 		��̬��static setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh) //���߳���������߳�ʵ������Ĭ�ϵ��쳣������
					setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh)�����ø÷������߳�ʵ�������쳣������
			 |---ѡ�����쳣���������̣�
			 		ĳ���߳��׳��쳣���ȿ����߳���û�������쳣������
					���û�У�����ø��߳��������uncaughtException�������߳����и���������
					���ø��������uncaughtException�����������򿴸��߳���������������Ĭ����
					����������������쳣������ThreadDeath�Ķ����򲻴�������ӡ������Ϣ
					�������������������̡߳�
			 |---��catch����catch�����쳣���쳣�������ϴ�����һ�������ߣ����쳣��������
*���̳߳ء�
     |---������ɶ������Ҫ�ô����������ڶ̵��߳�ʱ��ͨ���̳߳�ִ�������ʡ�������̵߳ĳɱ���
							��Ч���Ʋ����߳���������ֹ�����̹߳��࣬���������½���JVM������
     |---��ô�������á�Executors��������ľ�̬������������
	       |---������ExecutorService���̳߳�
		   			 newCachedThreadPool���湦�ܵ��̳߳أ������̺߳������л���
					 newFixedThreadPool(int)���������ù̶��̵߳ĳ�--newSingleThreadExecutor()���߳�
					 newWorkingSteelPool(int���м���)�����㹻����̳߳���֧�ֲ��м��𣬲�����������ݼ��˵�CPU���ü���
	       |---������ScheduledExecutorService���̳߳�
		   			 newScheduledThreadPool(int���б�����߳���)����ָ����ʱ��ִ������
					 ����ָ���߳����ĳأ�newSingleScheduledThreadPool���������߳̿�����ʱ�ĳ�
     |---��ô�ã�		
		ExecutorSerivice��submit(Runnable��Callable����)�������������ύ���̳߳أ��̳߳���
									�п����߳�ʱִ�иö�������run�����󷵻�ֵFuture��isDone��isCancelled������ȡ�߳�ִ��״̬��
		ScheduledExecutorSerivice��
			*schedule(Runnable/Callable,long delay,TimeUnit)ָ���������ӳٺ�ִ�У�����ScheduledFuture	
			*scheduleAtFixedRate(Runnable,intialDelay,period,TimeUnit)��ָ��Ƶ�ʳ����ظ�ִ�У�
		      intialDelay��ʼִ�У�intialDelay+period���ظ�ִ�С�����
			*scheduleAtFixedDely((Runnable,intialDelay,delay,TimeUnit)ÿ��ִ����ֹ����һ�ο�ʼ
			  �����ڹ̶����ӳ�delay
     |---£���ĵ㲽��
	             |---�����̳߳�	 		
	             |---����Runnable/Callable������Ϊִ������ 		
	             |---������submit���̳߳أ����̳߳ض������submit������--����ҪThread startʲô�ģ��̳߳ػ��Զ�ִ��	
	             |---���ύ��������shutdown�ر��̳߳�	
*Java7 ForkJoinPool(ExecutorServiceʵ���ࣩ�����ö��ϵͳ���м�����������������С����
				�ڸ���processor�����㣬���ϲ����ؽ����������Ҫ�ɷֽ�ForkJoinTask��
*������ࡿ
     |---ThredLocal<T>:���ڸ������̼߳�����ݹ���ͬ����Ϊ������̶߳Թ�����Դ�������ʣ�
									ͨ�����������ʵĹ�����Դ���ƶ�ݣ�ÿ�����̶�����Դ���������Բ���Ҫͬ��
	             |---ʹ�ã�������Ϊ������������ͣ��ڸ�ֵ��ȡֵʱ����set(T), T get()����	 								
     |---Collections��̬��������װ����Ϊ�̰߳�ȫ�ļ���
	 		synchronizedCollection(Collection<>)--��װΪ�̰߳�ȫ�ļ���
			synchronizedXXX--Set��SortSet��List��Map��SortMap
     |---�̰߳�ȫ�ļ�����
	      ConCurrentXXX֧�ֲ������߳�д�����
		  �����̷߳����Թ������Ͽ���ConcurrentLinkedQueue--���̸߳�Ч���ʣ����ɺ�null��
		  �����߳�д�����ConcurrentHashMap---֧��16�߳�ͬʱд�룩
		  CopyOnWriteXXX��xxArraySet�ײ��װ��xxArrayList��--�ʺ϶���������д������Ƶ���������飬���ܲ�

*@author Hartley
*@version 1.0.0
*/

class  ThreadTest
{

	//��Thread��Runnable���̲߳��� 
	public static void threadTest1()
	{
		for (int i=0;i<100 ;i++ )
		{
			System.out.println(Thread.currentThread().getName() +" : "+i);//��ȡ��ǰ������

			//��i==20ʱ���������߳�
			if (i==20)
			{
				/*//����Thread�������߳�
				new FirstThread().start();//������һ���߳�
				new FirstThread().start();//�����ڶ����߳�
				*/

				//��Runnable����
				SecondThread target = new SecondThread();
				Thread t1 = new Thread(target,"�߳�һ");
				t1.start();//����ֱ�ӵ���run����������ֻ�ǵ�����ͨ����-���̣߳�
				new Thread(target,"�̶߳�").start();//֧������~ 
			}
		}
	}
	
	//��Callable���̲߳���---����lambda���ʽ�������ڲ��ࣩ����Callable
	public static void threadTest2()
	{
		
		//Future�ӿڵ�ʵ����FutureTask��װCallable����Ȼ��Ϳ��Թ���Threadִ��
		FutureTask<Integer> target = new FutureTask<>( (Callable<Integer>)()->{//����ǿת
			int i=0;
			for (;i<500 ;i++ )
			{
				System.out.println(Thread.currentThread().getName()+" : "+i);
			}
			return i;//call()���������÷���ֵ
		});
		
		for (int i = 0;i<100 ;i++ )
		{
			System.out.println(Thread.currentThread().getName()+" : "+i);

			if (i==20)
			{
				//���������߳�??ʵ������ֻ��һ���߳��ڹ�������������
				new Thread(target,"�߳�һ").start();//����Runnable
				new Thread(target,"�̶߳�").start();
			}
		}

		try
		{
			System.out.println("Call�����ķ���ֵ��"+target.get());
			//get�������׳��ж��쳣,�Լ������쳣����getΪ����ʽ������û�����ݾ����ȡ���
		}
		catch (InterruptedException | ExecutionException e)
		{
			e.printStackTrace();
		}		
	}

	//�߳̿��Ʒ��� ֮ ��join�̡߳�
	public static void joinTest()
	{
		for (int i=0;i<100 ;i++ )
		{
			System.out.println(Thread.currentThread().getName() +" : "+i);//��ȡ��ǰ������

			//��i==20ʱ���������߳�
			if (i==20)
			{
				//��Runnable����
				SecondThread target = new SecondThread();
				Thread t1 = new Thread(target,"�߳�һ");
				t1.start();//����ֱ�ӵ���run����������ֻ�ǵ�����ͨ����-���̣߳�

				try
				{
					t1.join();//�߳�һ��ӣ�������߳�һ~---�����ж��쳣
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
				new Thread(target,"�̶߳�").start();
				//֧������~ ��Ϊ����target��ʵ�������������߳�һ�����ɺ��û�̶߳����¶���
			}
		}
	}

	//��Daemon��̨���̡�
	public static void daemonTest()
	{
		for (int i=0;i<10 ;i++ )
		{
			System.out.println(Thread.currentThread().getName() +" : "+i);//��ȡ��ǰ������

			if (i==3)
			{
				FirstThread t = new FirstThread();//ѭ�������900
				t.setDaemon(true);//��������Ϊ��̨�߳�
				t.start();//�������̣߳�����
				System.out.println("t�ǲ���Daemon? "+t.isDaemon() );
				//��̨�߳���Ϊǰ̨���̵�Dead����֮dead��û�������900
			}
		}
	}

	//��sleep����+�ò�+�����߳����ȼ���
	public static void sleepTest()
	{
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);//�����߳����ȼ���Ϊ���
		for (int i=0;i<100 ;i++ )
		{
			System.out.println(Thread.currentThread().getName() +" : "+i);//��ȡ��ǰ������

			if (i==20)
			{
				SecondThread target = new SecondThread();//ѭ�������50-1
				Thread t1 = new Thread(target,"����");
				t1.setPriority(Thread.MIN_PRIORITY);//��t1�߳����ȼ���Ϊ���
				t1.start();
				t1.yield();//�ò�һ��~
				/*		
				try
				{
					t1.sleep(1000);//���߳�˯һ�룬�����׳��ж��쳣???����ִ������sleep������
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				*/
			}
		}
	}

	//�쳣������
	public static void exhandleTest()
	{
		//��2���߳��������uncaughtException����
		ThreadGroup group = Thread.currentThread().getThreadGroup();
		//��3���߳������õ�Ĭ���쳣������
		Thread.setDefaultUncaughtExceptionHandler( (thread,throwable)->
					System.out.println("�߳�Ĭ���쳣��������"+throwable.getMessage()+"�쳣"));
		//��1���߳�ֱ�Ӷ�Ӧ���쳣������
		
		Thread.currentThread().setUncaughtExceptionHandler( (thread,throwable)->
					System.out.println(thread.getName()+"�̲߳�����"+throwable.getMessage()+"�쳣"));
		/**/
		int a = 9;
		int b = a/0;//�����쳣

		System.out.println("over");
	}

	//�̳߳�
	public static void ThreadPoolTest()
	{
		ExecutorService pool = Executors.newFixedThreadPool(6);//�����̶���С���̳߳�
		Runnable task = ()->{
			for (int i =0 ; i<100 ;i++ )
			{
				System.out.println(Thread.currentThread().getName()+"  : "+i);
				//���߳������д�pool��֪���߳�Ϊ�̳߳��е�~
			}
		};

		Future result = pool.submit(task);//���̳߳��ύ����
		pool.submit(task);
		
		
		pool.shutdown();//�ر��̳߳أ�����
		System.out.println("task�����û��"+result.isDone());//false---ʵʱ�ж��߳����û
		try
		{
			Thread.sleep(1000);
			System.out.println("task�����û��"+result.isDone());//true
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
	}

	//Java7��Fork JoinPool
	//��ӡ����-�޷���ֵ
	public static void forkJoinPoolTest() throws InterruptedException
	{
		ForkJoinPool pool = new ForkJoinPool();//����ForkJoin��

		PrintTask task = new PrintTask(0,300);//���������ӡ0-300
		pool.submit(task);
		
		pool.awaitTermination(3,TimeUnit.SECONDS);//shutdownǰ����������趨ʱ��ȴ�ForkJoin����������
		pool.shutdown();//�رճ�
	}

	//add����-�з���ֵ
	public static void forkJoinPoolTest2() throws InterruptedException,ExecutionException
	{
		int[] arr = new int[300];
		Random random = new Random();//�����ֵ
		int total = 0;//�߸�ֵ�߼����ܺ�
		for (int i=0;i<300 ;i++ )
		{
			arr[i] = random.nextInt(20);//20���������
			total+=arr[i];
		}
		System.out.println("arr��ȷ�ܺ�Ϊ��"+total);

		//����ForkJoin��
		//ForkJoinPool pool = new ForkJoinPool();
		ForkJoinPool pool = ForkJoinPool.commonPool();//����ͨ�ó�
		AddTask task = new AddTask(arr,0,arr.length);//�����������
		Future<Integer> future = pool.submit(task);//�����ύ��pool
		System.out.println("���м�����Ϊ: "+future.get());

		pool.shutdown();//�رճ�
	}
	//************�������***************
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

//�Զ���----��Thread�߳��ࡿ=======================================
class FirstThread extends Thread
{
	private int i;//iΪʵ������---���̼䲻����~

	//��дThread��run����
	public void run()
	{
		for (;i<900 ;i++ )
		{
			System.out.println(this.getName() +" : "+i);//���ڼ̳���Thread�࣬����ֱ�ӻ�ȡ������
		}
	}
}


//�Զ���----��Runnable�߳��ࡿ=======================================
class SecondThread implements Runnable
{
	private int i;//iΪʵ������---��Ϊ��ͬһ��target ���Թ���~

	//��дThread��run����
	public void run()
	{
		for (;i<500 ;i++ )
		{
			System.out.println(Thread.currentThread().getName() +" : "+i);
			//��Runnable�ӿ�ʵ���߳�--ֻ���þ�̬������ȡ��ǰ�̶߳���
		}
	}
}

//���д�ӡ����--�޷���ֵ����fork���м���Ϳ�=============================
class PrintTask extends RecursiveAction//�̳пɷֽ�����ĳ����࣬��дcompute����
{
	private final static int THRESHOLD = 50;//ÿ�������߳�ֻ��ӡ50��
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
		//ֻ�е�start��end��50���ڲŴ�ӡ
		if (end - start < THRESHOLD)
		{
			for (int i = start ;i<end ;i++ )
			{
				System.out.println(Thread.currentThread().getName() +" : "+i);
			}			
		}else{
			//����ݹ鴴������
			int mid = (start + end)>>1;
			PrintTask left = new PrintTask(start,mid);
			PrintTask right = new PrintTask(mid,end);

			left.fork();//���м���С����
			right.fork();
		}
	}

}

//���мӺ�������fork���м��� + join�ϲ�������==========================
class AddTask extends RecursiveTask<Integer>//ע�Ⲣ�м����з���ֵ��ӷ���
{
	private final static int THRESHOLD = 50;//ÿ�������߳�ֻ��50��
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
	protected Integer compute()//�з���ֵ��~
	{
		//ֻ�е�start��end��50���ڲżӺ�
		if (end - start < THRESHOLD)
		{
			int sum = 0;
			for (int i = start ;i<end ;i++ )
			{
				sum+=arr[i];
			}			
			return sum;

		}else{
			//����ݹ鴴������
			int mid = (start + end)>>1;
			AddTask left = new AddTask(arr,start,mid);//�˴�mid���ᱻȡ
			AddTask right = new AddTask(arr,mid,end);

			left.fork();//���м���С����
			right.fork();

			return left.join() + right.join();
		}
	}

}
