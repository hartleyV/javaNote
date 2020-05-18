import java.io.*;
/**
*IO练习题
*@author Hartley
*@version 1.0.0
*/

class  IOExercise
{
	//************程序入口***************
	public static void main(String[] args) 
	{
		String path = "C:\\Users\\workFiles\\studentApi";
		//FileUtils.list(path);
		//FileUtils.listAll(path,0);
		FileUtils.getNum(path);
	}
}

//定义工具类 
//可以列出指定路径下的所有文件及子目录
//----可以统计文件数和目录数
//----实现复制功能，copy是文件，则复制到指定路径；copy是目录，则将其所有子文件子目录复制到指定路径
class FileUtils
{
	

	//列出指定路径下的文件 & 目录
	public static void list(String path)
	{
		File file = new File(path);
		String[] lists = file.list();
		for (String list : lists )
		{
			System.out.println(list);
		}

	}
	//分别获取文件、目录数目
	public static void getNum(String path)
	{
		int fileCount = 0;
		int folderCount = 0;

		File[] files = new File(path).listFiles();
		if (files==null)
		{
			return;
		}

		for (File file : files)
		{
			if (file.isDirectory())
			{
				folderCount++;
			}else{
				fileCount++;
			}
		}
		println("文件夹有 "+folderCount+" 个");
		println("文件有 "+fileCount+" 个");
		
	}
	//列出所有文件、子目录---level用于打印层级关系
	public static void listAll(String path,int level)
	{
		println("here"+level);
		File file = new File(path);
		println(getSpace(level) + file.getName() );
		File[] files = file.listFiles();
		
		if (files==null)
		{
			return;
		}

		for (int i =0; i<files.length;i++ )
		{
			
			if (files[i].isFile())
			{		
				println(getSpace(level) + files[i].getName() );
			}else{
				
				listAll(files[i].getAbsolutePath(),++level);
			}
		}
	}
	
	//获取当前层级前面的空格
	private static String getSpace(int level)
	{
		StringBuilder space = new StringBuilder();
		space.append("*");

		for (int i=0;i<level ;i++ )
		{
			space.insert(0,"*         ");
		}

		return space.toString();

	}
	public static void println(Object obj)
	{
		System.out.println(obj);
	}

}