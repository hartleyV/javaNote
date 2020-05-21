import java.util.concurrent.locks.*;

//import java.util.concurrent.*;
//import java.util.concurrent.locks.*;
/**
*线程通信练习二：
案例：银行账户--存钱后立即取出，不能连续存取（生产者消费者模型）
		  采用Synchronized方法--同步监视器调用 或者 Lock--Condition对象调用。。

*问题：会堵塞住？？----是多线程假死现象
因为生产者应该唤醒消费者，却只唤醒了生产者。。。可以用notifyAll或者BlockingQueue解决
*@author Hartley
*@version 1.0.0
*/

class  ThreadExercise2 
{
	//************程序入口***************
	public static void main(String[] args) 
	{
		Account jack = new Account("687",0);
		new DrawThread("取钱 ",jack,300).start();//取10次
		new DepositThread("存钱",jack,300).start();//存10此
		//new DepositThread("存钱小绿",jack,300).start();
		//如果两个存钱者存钱，一个取钱，会引起阻塞--并非死锁！
	}
}
//取钱线程
class DrawThread extends Thread
{
	private Account account;
	private double draw;
	public DrawThread(String name,Account account,double draw)
	{
		super(name);
		this.account = account;
		this.draw = draw;
	}

	//Run方法
	public void run()
	{
		for (int i=0;i<10 ;i++ )
		{
			account.draw(draw);
		}
	}
}

//存钱线程
class DepositThread extends Thread
{
	private Account account;
	private double deposit;
	public DepositThread(String name,Account account,double deposit)
	{
		super(name);
		this.account = account;
		this.deposit = deposit;
	}

	//Run方法
	public void run()
	{
		for (int i=0;i<10 ;i++ )
		{
			account.deposit(deposit);
		}
	}
}
//账户类
class Account
{
	//==================================================
	private final Lock lock = new ReentrantLock();//显示创建可重入锁
	private final Condition condition = lock.newCondition();//获得指定Lock对象对应的Condition
	//==================================================

	private String ID;
	private boolean flag;//用来标记是否存钱了
	private double balance;
	public Account(String ID,double balance)
	{
		
		flag=false;
		this.ID = ID;
		this.balance = balance;
	}

	//存钱的同步方法
	//public synchronized void deposit(double deposit)
	public  void deposit(double deposit)
	{	
		System.out.println("存钱处标志"+Thread.currentThread().getName()+" "+flag);
		
		lock.lock();//加锁

		if (flag == true)//已经存钱一次了
		{
			try
			{
				condition.await();//让当前线程等待（在lock对象上？？），释放condition对象？？？
				//wait();//让当前线程进入对象等待池中，并释放同步监视器--Account对象
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			finally
			{
				lock.unlock();//程序结束后要释放锁
			}
			
		}else{
			//没存取呢还
			balance += deposit;
			System.out.println("恭喜你 "+Thread.currentThread().getName()+"，成功存了 "+deposit+"$");
			System.out.println("宁的余额为 "+balance+"$");

			flag =true;//置标记为已存钱

			condition.signalAll();//唤醒其他在lock对象上等待的进程

			//notify();//会导致自己唤醒自己。。假死
			//notifyAll();//并通知可以取钱啦--使进入对象等待池的线程进入锁标志等待池
			//该线程结束--释放锁---通知的进程可以运行了~
			
		}
	}

	//取钱的同步方法
	public synchronized void draw(double draw)
	{	
		lock.lock();
		System.out.println("取钱处标志"+Thread.currentThread().getName()+" "+flag);
		if(flag == false)//表示还没存钱呢
		{
			try
			{
				condition.await();
				//wait();//取钱先等待，等存完再说
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			finally
			{
				lock.unlock();
			}
		}else{
			balance -= draw;
			System.out.println("恭喜你 "+Thread.currentThread().getName()+"，成功取了 "+draw+"$");
			System.out.println("宁的余额为 "+balance+"$");
			
			flag = false;//更新flag

			condition.signalAll();

			//notifyAll();//唤醒存取进程
		}

	}
}