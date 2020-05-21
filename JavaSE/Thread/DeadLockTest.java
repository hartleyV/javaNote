package Thread;

/**
*死锁：两线程相互等待对方释放同步监视器时
通过sleep--让A睡觉，B调用A的同步方法需要对A对象加锁，但A处于阻塞，监视器B不会释放
A醒来再调用B的同步方法，需要对B对象加锁，也要等监视B得到释放，互相等--
*@author Hartley
*@version 1.0.0
*/

class  DeadLockTest implements Runnable
{
	A a;
	B b;
	public DeadLockTest()
	{
		Thread.currentThread().setName("主进程");
		a = new A();
		b = new B();
	}

	//实现run方法
	public void run()
	{
		a.init(b);//【3】运行子线程，A的init为同步方法，会将对象a锁住
	}
	//************程序入口***************
	public static void main(String[] args) 
	{
		DeadLockTest target = new DeadLockTest();
		Thread sub = new Thread(target,"子线程");
		sub.start();
		target.b.init(target.a);//【1】B的init为同步方法，会将对象b锁住
	}
}

class A 
{
	public synchronized void init(B b)//也需为同步方法！！
	{
		System.out.println(Thread.currentThread().getName()+"执行了A的init方法");
/*
		try
		{
			Thread.sleep(200);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
*/		
		b.t();//【4】需要拿到b的监视器，但是他在睡觉，仍没有释放，所以子线程阻塞ing
		//此时a的监视器也没有释放【注意】这个b跟睡觉那个B要是同一个对象!!
	}
	//同步方法
	public synchronized void t()
	{
		System.out.println(Thread.currentThread().getName()+"A的同步方法");
	}
}

class B 
{
	public synchronized void init(A a)
	{
		System.out.println(Thread.currentThread().getName()+"执行了B的init方法");
		
		try
		{
			Thread.sleep(200);//【2】主线程睡觉了，会执行子线程的run方法，
			//注意此时对象B的锁没有释放
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		a.t();//【5】主线程睡醒了，A的t方法为同步方法，所以要拿到A对象进行锁定，但是A没有释放
		//所以主线程也阻塞。。。互相等待对方释放锁（同步监视器），最终死锁了 over~
	}
	//同步方法
	public synchronized void t()
	{
		System.out.println(Thread.currentThread().getName()+"B的同步方法");
	}
}