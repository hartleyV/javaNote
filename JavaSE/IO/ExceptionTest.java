import java.io.*;
/**
*异常机制：将不可预期的异常处理代码与正常业务逻辑处理代码分离
*-|| 关键字
*----|| try（必须有）:存放可能产生异常的代码，某行语句异常，其后面代码不会得到执行
*----|| catch（其一）:后面接异常类型 和 相应处理的代码块。原则【先小异常，再大异常】。捕获多异常类型用 | 隔开（捕获多异常时，其异常变量会被隐式声明final，不可改）
*----|| finally（其一）:在catch后一般用于回收try块用到的资源，异常机制会保证finally块一定会被执行（除非调用了退出虚拟机的方法 System.exit(1)
*----|| throws:在方法签名中声明，方法可能会产生的异常
*----|| throw:抛出一个实际异常（可以自定义的
*-|| 异常类型：Checked（必须处理->提醒作用）& Runtime（无需处理->推荐）
*----|| 【Checked】异常处理：
*-----------|| 1.当前方法知道如何解决该异常时：用【try catch】语句捕获该异常，在catch块内处理该异常
*-----------|| 2.不知道怎么处理，需要在方法声明处抛出该异常 【throws】 XXException（若最终抛给JVM则会打印StackTrace信息，结束程序）
*----|| 【Checked机制缺陷】：
*-----------|| 必须显示捕获处理->复杂；
*-----------|| 重写父类中抛异常的方法->抛异常会受限制（只能抛出父类中该方法抛出异常的本身或其子类异常）
*----|| 【Runtime】异常
*-----------|| 比较灵活，可以显示处理，也可以不管不顾，So一般自定义异常类会继承RuntimeException
*-|| 异常机制的思路：如果某段代码出错，则会抛出一个异常对象，寻找对应类型的catch块（有则执行第一个对应的catch块，没有则就此退出）
*-|| 自动关闭资源的try语句（用来解决finally回收资源时，代码臃肿）：
*----|| 书写：try(创建待使用的资源){使用资源}
*----|| 对应资源要实现AutoClosable或Closable接口中的close方法（AutoClosable中抛出Exception异常，其子类可随意抛；前者的子接口中close方法抛出的是IOException，其子类只可抛IO异常）
*-|| catch与throw联动：应用后台记录异常日志；根据异常向调用者返回消息
*-||异常链：捕获原始异常记录下来给程序员，转译一个业务异常抛给用户（Throwable的子类的构造器都可接收cause-原始异常对象作为参数//封装原始异常）
*-||异常处理目标：
*----|| 代码尽量清晰，不混乱
*----|| 捕获并保存到诊断信息
*----|| 通知合适的人员
*----|| 采取合适的方法结束异常

*@author Hartley
*@version 1.0.0
*/

class  ExceptionTest
{
	public static void test()
	{
		int[] arr = new int[10];
		String str =null;
		Integer num=0;	
		
		FileInputStream fis = null;

		try
		{
			/*		
			System.out.println("【1】");
			num = arr[10];
			System.out.println("【2】");
			num = 10/0;
			System.out.println("【3】");
			str.toString();*/
			
			fis = new FileInputStream("mag.txt");//试图打开一个不存在的文件，会引发IO异常

			//return ;//顺序--先执行finally块（如果finally里面有return则直接返回，此处不执行）--再执行这个return
			System.exit(1);//虚拟机直接退出，不会执行finally块了

		}
		
		catch(IOException ioe)//IO异常
		{
			System.out.println(ioe.getMessage() );//打印出错原因
		}
		catch (IndexOutOfBoundsException | ArithmeticException e)//数组越界异常
		{	
			System.out.println("数组越界或算术异常");
			//e = new NullPointerException("hh");//捕获多异常时，异常对象会默认用final修饰，不可改
		}
		/*catch (IndexOutOfBoundsException e)//数组越界异常
		{	
			System.out.println("数组越界异常");
			System.out.println("异常的详细描述："+e.getMessage());
		}
		catch(ArithmeticException e)
		{
			System.out.println("算术异常");
			System.out.println("异常的详细描述："+e.getMessage());
		}*/
		catch(NumberFormatException e)
		{
			System.out.println("数据格式转换异常");//随意输入？
			
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();//打印异常的跟踪栈信息
			System.out.println("空指针异常");
			System.out.println("异常的详细描述："+e.getMessage());
		}
		catch(Exception e)//Exception是其他异常的父类，所以需要放到最后；在比较异常类型时是根据 e instanceOf Exception，
		{
			System.out.println("未知异常");
			e = new RuntimeException();//单个异常时，异常变量可以更改
		}

		finally
		{
			//回收资源
			if(fis != null)
			{
				try
				{
					fis.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			System.out.println("I am finally");
		}
	}

	//测试自动回收资源的try语句
	public static void autoCloseTest() 
	{
		try(
			//在try括号里面声明创建自动回收的资源（隐式finally块）
			BufferedReader br = new BufferedReader(
													new FileReader("config.ini"))//BR实现了Closeable接口，try语句会自动关闭他
			)
			{
				//对资源进行操作
				System.out.println(br.readLine());
			}
		catch(Exception e)//如果没有catch处理，则需要在方法前声明throws XXException
		{
			e.printStackTrace();
		}
		
	}

	//增强的throw语句--编译器会细致检查异常的实际类，而不是以Exception一以贯之
	public static void throwTest() throws FileNotFoundException
	{
		try
		{
			new FileOutputStream("xx.txt");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;//增强的throw语句，抛出后要在方法处声明可能抛出的异常--（增强：按实际异常，而不是笼统Exception
		}
	}
	//************程序入口***************
	public static void main(String[] args) throws FileNotFoundException
	{
		// test();
		//autoCloseTest();
		throwTest();
		//抛一个自定义的Runtime类型的异常；可以不处理~
		//throw new DiyException("I am Diy_Exception");
	}
}

//自定义异常类
class DiyException extends RuntimeException
{
	//无参构造器
	DiyException(){}
	//含参数->msg
	DiyException(String msg)
	{
		super(msg);
	}
}