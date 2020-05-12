//import java.util.*;
import java.util.concurrent.*;
/**
*阻塞队列案例：消费者-生产者
取空队列时：会阻塞当前线程，等待队列中加入元素再继续取出
放满队列时：也会阻塞当前线程，等待队列中取出元素再继续放入
*@author Hartley
*@version 1.0.0
*/

class  ThreadExercise3
{
	//************程序入口***************
	public static void main(String[] args) throws InterruptedException
	{
		BlockingQueue<String> bq = new ArrayBlockingQueue<>(1);//创建大小为1的阻塞队列，且生产消费共享
		new Consumer("消费者小张",bq).start();
		//new Consumer("消费者小李",bq).start();
		new Producer("生产者阿绿",bq).start();
		/*
		BlockingQueue<Integer> que = new ArrayBlockingQueue<>(1);//定义大小为1的阻塞队列
		que.put(2);
		que.put(2);//会阻塞进程
		*/
	}
}

//消费者
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
				//Thread.sleep(200);//方便观看
				String ele = bq.take();
				System.out.println(getName()+" 消费了 "+ele);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
		}
		
	}

}

//生产者
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
		String[] src = {"东坡肘子","烧鸡","熊掌"};

		for (int i=0;i<200 ;i++ )
		{
			String cur = src[i%3];//循环往复
			try
			{
				//Thread.sleep(200);
				bq.put(cur);
				System.out.println(getName()+" 生产了 "+cur);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
		}
		
	}

}