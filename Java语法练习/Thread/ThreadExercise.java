import java.util.concurrent.locks.ReentrantLock;//ReetrantLock��������//ͬһ���߳̿��ظ�����
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
		b.start();
		a.start();
		
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
		//�����˻���ͬ������draw--��this��Ϊͬ������������������ǰ���õ��˻�����
		account.draw(draw);
		/*
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
			/*
			account.setBalance(account.getBalance() - draw);
			System.out.println("��ǰjack���Ϊ��"+account.getBalance());
		}else{
			System.out.println("��B "+getName()+" ��ȥ��ש������~");
			//System.out.println("��ǰjack���Ϊ��"+account.getBalance());
		}	
	}*/
		}
		
}

//�˻��ࣺ�û�ID�����
class Account
{
	private String accountID;
	private double balance;
	//���Ȼ�ȡLock����true��ʾ��ƽ��
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
//���߳��²���ȫ������
	public void setBalance(double balance)
	{
		this.balance = balance;
	}
*/
	//����ȡ���������������Ϊͬ����������ʽ�������ö���
	//public synchronized void draw(double draw)
	public  void draw(double draw)
	{
		lock.lock();//��ͬ�������� 

		try
		{
			if (balance - draw >=1e-6)
			{
				System.out.println("�� "+Thread.currentThread().getName()+" �³���"+draw+" $");
				balance -=draw; 
				System.out.println("��ǰ�˻����Ϊ��"+balance );
			}else{
				System.out.println("��̫���� "+Thread.currentThread().getName()+" ��ȥ��ש������~");
			}		
		}
		finally
		{
			lock.unlock();//�޸���ɣ�����
		}
		
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