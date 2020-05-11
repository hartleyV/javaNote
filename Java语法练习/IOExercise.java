import java.io.*;
/**
*IO��ϰ��
*@author Hartley
*@version 1.0.0
*/

class  IOExercise
{
	//************�������***************
	public static void main(String[] args) 
	{
		String path = "C:\\Users\\workFiles\\studentApi";
		//FileUtils.list(path);
		//FileUtils.listAll(path,0);
		FileUtils.getNum(path);
	}
}

//���幤���� 
//�����г�ָ��·���µ������ļ�����Ŀ¼
//----����ͳ���ļ�����Ŀ¼��
//----ʵ�ָ��ƹ��ܣ�copy���ļ������Ƶ�ָ��·����copy��Ŀ¼�������������ļ���Ŀ¼���Ƶ�ָ��·��
class FileUtils
{
	

	//�г�ָ��·���µ��ļ� & Ŀ¼
	public static void list(String path)
	{
		File file = new File(path);
		String[] lists = file.list();
		for (String list : lists )
		{
			System.out.println(list);
		}

	}
	//�ֱ��ȡ�ļ���Ŀ¼��Ŀ
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
		println("�ļ����� "+folderCount+" ��");
		println("�ļ��� "+fileCount+" ��");
		
	}
	//�г������ļ�����Ŀ¼---level���ڴ�ӡ�㼶��ϵ
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
	
	//��ȡ��ǰ�㼶ǰ��Ŀո�
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