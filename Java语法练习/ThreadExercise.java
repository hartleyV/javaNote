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
		a.start();
		b.start();
		
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
			account.setBalance(account.getBalance() - draw);
			System.out.println("当前jack余额为："+account.getBalance());
		}else{
			System.out.println("穷B "+getName()+" 多去搬砖再来把~");
			//System.out.println("当前jack余额为："+account.getBalance());
		}	
	}
		}
		
}

//账户类：用户ID、余额
class Account
{
	private String accountID;
	private double balance;
	public Account(String accountID,double balance)
	{
		this.accountID = accountID;
		this.balance = balance;
	}

	public double getBalance()
	{
		return this.balance;
	}

	public void setBalance(double balance)
	{
		this.balance = balance;
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