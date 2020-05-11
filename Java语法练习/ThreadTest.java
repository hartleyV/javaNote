import java.util.concurrent.*;//Callable�İ�װ��FutureTask
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
     |---��ͬ������������
	 		     |---Ŀ�ģ���ֹ�̶߳�ͬһ������Դ���в�������-->�����������Ϊͬ��������
	 		     |---�÷�����synchronized(obj){ͬ�������}---objΪͬ��������
	 		     |---���̣�����ͬ��������---�޸�---�ͷ�

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
	//************�������***************
	public static void main(String[] args) 
	{
		//threadTest1();
		//threadTest2();
		//daemonTest();
		sleepTest();
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

