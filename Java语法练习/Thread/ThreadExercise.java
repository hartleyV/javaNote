import java.util.concurrent.locks.ReentrantLock;//ReetrantLock可重入锁//同一个线程可重复进入
/**
*同步经典案例：多线程取钱
*@author Hartley
*@version 1.0.0
*/

class  ThreadExercise
{
	//************程序入口***************
	public static void main(String[] args) 
	{
		Account jack = new Account("007",100);
		DrawMoneyThread a = new DrawMoneyThread("小明",jack,80);
		DrawMoneyThread b = new DrawMoneyThread("小绿",jack,80);
		b.setPriority(Thread.MAX_PRIORITY);
		b.start();
		a.start();
		
	}
}

//取钱的线程类：账户对象，取钱数额
//逻辑：当钱少于当前手里钱时，取钱失败
class DrawMoneyThread extends Thread
{
	private Account account;
	private int draw;
	public DrawMoneyThread(String name,Account account,int draw)
	{
		super(name);//命名线程
		this.account = account;
		this.draw = draw;
	}
	//线程执行体
	public void run() 
	{
		//调用账户的同步方法draw--将this作为同步监视器，会锁定当前调用的账户对象
		account.draw(draw);
		/*
		synchronized(account)
		{
			if (draw<=account.getBalance() )
		{
			System.out.println("给 "+getName()+" 吐出："+draw+" $");
			/*
			try
			{
				Thread.sleep(1);
			}
			catch (InterruptedException e)
			{
			}
			*/
			/*
			account.setBalance(account.getBalance() - draw);
			System.out.println("当前jack余额为："+account.getBalance());
		}else{
			System.out.println("穷B "+getName()+" 多去搬砖再来把~");
			//System.out.println("当前jack余额为："+account.getBalance());
		}	
	}*/
		}
		
}

//账户类：用户ID、余额
class Account
{
	private String accountID;
	private double balance;
	//首先获取Lock对象，true表示公平锁
	private final ReentrantLock lock = new ReentrantLock(true);
	public Account(String accountID,double balance)
	{
		this.accountID = accountID;
		this.balance = balance;
	}

	public double getBalance()
	{
		return this.balance;
	}

/*
//多线程下不安全，弃用
	public void setBalance(double balance)
	{
		this.balance = balance;
	}
*/
	//增加取款方法，并将其声明为同步方法（隐式锁定调用对象）
	//public synchronized void draw(double draw)
	public  void draw(double draw)
	{
		lock.lock();//用同步锁锁定 

		try
		{
			if (balance - draw >=1e-6)
			{
				System.out.println("给 "+Thread.currentThread().getName()+" 吐出："+draw+" $");
				balance -=draw; 
				System.out.println("当前账户余额为："+balance );
			}else{
				System.out.println("你太穷了 "+Thread.currentThread().getName()+" 多去搬砖再来把~");
			}		
		}
		finally
		{
			lock.unlock();//修改完成，解锁
		}
		
	}

	//有账号的ID哈希值区分不同对象
	public int hashCode()
	{
		return accountID.hashCode();
	}
	public boolean equals(Object obj)
	{
		if(this==obj)
		{
			return true;
		}
		if(obj!=null && obj.getClass() == Account.class )
		{
			Account tmp = (Account) obj;
			return this.hashCode() == tmp.hashCode();
		}
		
		return false;
	}
}