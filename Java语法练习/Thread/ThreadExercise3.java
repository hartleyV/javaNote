//import java.util.*;
import java.util.concurrent.*;
/**
*�������а�����������-������
ȡ�ն���ʱ����������ǰ�̣߳��ȴ������м���Ԫ���ټ���ȡ��
��������ʱ��Ҳ��������ǰ�̣߳��ȴ�������ȡ��Ԫ���ټ�������
*@author Hartley
*@version 1.0.0
*/

class  ThreadExercise3
{
	//************�������***************
	public static void main(String[] args) throws InterruptedException
	{
		BlockingQueue<String> bq = new ArrayBlockingQueue<>(1);//������СΪ1���������У����������ѹ���
		new Consumer("������С��",bq).start();
		//new Consumer("������С��",bq).start();
		new Producer("�����߰���",bq).start();
		/*
		BlockingQueue<Integer> que = new ArrayBlockingQueue<>(1);//�����СΪ1����������
		que.put(2);
		que.put(2);//����������
		*/
	}
}

//������
class Consumer extends Thread
{
	BlockingQueue<String> bq;
	public Consumer(String name , BlockingQueue<String> bq)
	{
		super(name);
		this.bq = bq;
	}

	public void run()
	{
		for (int i=0;i<200 ;i++ )
		{
			try
			{
				//Thread.sleep(200);//����ۿ�
				String ele = bq.take();
				System.out.println(getName()+" ������ "+ele);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
		}
		
	}

}

//������
class Producer extends Thread
{
	BlockingQueue<String> bq;
	public Producer(String name , BlockingQueue<String> bq)
	{
		super(name);
		this.bq = bq;
	}

	public void run()
	{
		String[] src = {"��������","�ռ�","����"};

		for (int i=0;i<200 ;i++ )
		{
			String cur = src[i%3];//ѭ������
			try
			{
				//Thread.sleep(200);
				bq.put(cur);
				System.out.println(getName()+" ������ "+cur);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
		}
		
	}

}