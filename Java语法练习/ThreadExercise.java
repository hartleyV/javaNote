/**
*ͬ�����䰸�������߳�ȡǮ
*@author Hartley
*@version 1.0.0
*/

class  ThreadExercise
{
	//************�������***************
	public static void main(String[] args) 
	{
		Account jack = new Account("007",100);
		DrawMoneyThread a = new DrawMoneyThread("С��",jack,80);
		DrawMoneyThread b = new DrawMoneyThread("С��",jack,80);
		b.setPriority(Thread.MAX_PRIORITY);
		a.start();
		b.start();
		
	}
}

//ȡǮ���߳��ࣺ�˻�����ȡǮ����
//�߼�����Ǯ���ڵ�ǰ����Ǯʱ��ȡǮʧ��
class DrawMoneyThread extends Thread
{
	private Account account;
	private int draw;
	public DrawMoneyThread(String name,Account account,int draw)
	{
		super(name);//�����߳�
		this.account = account;
		this.draw = draw;
	}
	//�߳�ִ����
	public void run() 
	{
		synchronized(account)
		{
			if (draw<=account.getBalance() )
		{
			System.out.println("�� "+getName()+" �³���"+draw+" $");
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
			System.out.println("��ǰjack���Ϊ��"+account.getBalance());
		}else{
			System.out.println("��B "+getName()+" ��ȥ��ש������~");
			//System.out.println("��ǰjack���Ϊ��"+account.getBalance());
		}	
	}
		}
		
}

//�˻��ࣺ�û�ID�����
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

	//���˺ŵ�ID��ϣֵ���ֲ�ͬ����
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