import java.util.concurrent.locks.*;

//import java.util.concurrent.*;
//import java.util.concurrent.locks.*;
/**
*�߳�ͨ����ϰ����
�����������˻�--��Ǯ������ȡ��������������ȡ��������������ģ�ͣ�
		  ����Synchronized����--ͬ������������ ���� Lock--Condition������á���

*���⣺�����ס����----�Ƕ��̼߳�������
��Ϊ������Ӧ�û��������ߣ�ȴֻ�����������ߡ�����������notifyAll����BlockingQueue���
*@author Hartley
*@version 1.0.0
*/

class  ThreadExercise2 
{
	//************�������***************
	public static void main(String[] args) 
	{
		Account jack = new Account("687",0);
		new DrawThread("ȡǮ ",jack,300).start();//ȡ10��
		new DepositThread("��Ǯ",jack,300).start();//��10��
		//new DepositThread("��ǮС��",jack,300).start();
		//���������Ǯ�ߴ�Ǯ��һ��ȡǮ������������--����������
	}
}
//ȡǮ�߳�
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

	//Run����
	public void run()
	{
		for (int i=0;i<10 ;i++ )
		{
			account.draw(draw);
		}
	}
}

//��Ǯ�߳�
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

	//Run����
	public void run()
	{
		for (int i=0;i<10 ;i++ )
		{
			account.deposit(deposit);
		}
	}
}
//�˻���
class Account
{
	//==================================================
	private final Lock lock = new ReentrantLock();//��ʾ������������
	private final Condition condition = lock.newCondition();//���ָ��Lock�����Ӧ��Condition
	//==================================================

	private String ID;
	private boolean flag;//��������Ƿ��Ǯ��
	private double balance;
	public Account(String ID,double balance)
	{
		
		flag=false;
		this.ID = ID;
		this.balance = balance;
	}

	//��Ǯ��ͬ������
	//public synchronized void deposit(double deposit)
	public  void deposit(double deposit)
	{	
		System.out.println("��Ǯ����־"+Thread.currentThread().getName()+" "+flag);
		
		lock.lock();//����

		if (flag == true)//�Ѿ���Ǯһ����
		{
			try
			{
				condition.await();//�õ�ǰ�̵߳ȴ�����lock�����ϣ��������ͷ�condition���󣿣���
				//wait();//�õ�ǰ�߳̽������ȴ����У����ͷ�ͬ��������--Account����
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			finally
			{
				lock.unlock();//���������Ҫ�ͷ���
			}
			
		}else{
			//û��ȡ�ػ�
			balance += deposit;
			System.out.println("��ϲ�� "+Thread.currentThread().getName()+"���ɹ����� "+deposit+"$");
			System.out.println("�������Ϊ "+balance+"$");

			flag =true;//�ñ��Ϊ�Ѵ�Ǯ

			condition.signalAll();//����������lock�����ϵȴ��Ľ���

			//notify();//�ᵼ���Լ������Լ���������
			//notifyAll();//��֪ͨ����ȡǮ��--ʹ�������ȴ��ص��߳̽�������־�ȴ���
			//���߳̽���--�ͷ���---֪ͨ�Ľ��̿���������~
			
		}
	}

	//ȡǮ��ͬ������
	public synchronized void draw(double draw)
	{	
		lock.lock();
		System.out.println("ȡǮ����־"+Thread.currentThread().getName()+" "+flag);
		if(flag == false)//��ʾ��û��Ǯ��
		{
			try
			{
				condition.await();
				//wait();//ȡǮ�ȵȴ����ȴ�����˵
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
			System.out.println("��ϲ�� "+Thread.currentThread().getName()+"���ɹ�ȡ�� "+draw+"$");
			System.out.println("�������Ϊ "+balance+"$");
			
			flag = false;//����flag

			condition.signalAll();

			//notifyAll();//���Ѵ�ȡ����
		}

	}
}