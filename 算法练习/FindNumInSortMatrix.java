/**
*�����ж��ĺ�˳���M*N�����������Ƿ����
Ҫ��ʱ��O(M+N)  ����ռ�O(1)

˼·��
1    2   3  <- ���þ������򣬴����ϻ����¿�ʼ�Ƚϣ��ж��н�����ֱ���ҵ�Ŀ��
4   5   6       
7   8   9
*@author Hartley
*@version 1.0.0
*/

class  FindNumInSortMatrix
{
	//************�������***************
	public static void main(String[] args) 
	{
		int targetNum = 1000;
		int[][] arr =  { { 1, 2, 3, 4 }, 
							 { 5, 6, 7, 8 }, 
							 { 9, 10, 11, 12 },
							 { 13, 14, 15, 16 } };
		boolean hasNum = isContains(arr,targetNum);
		System.out.println(hasNum);
	}

	public static boolean isContains(int[][] arr,int targetNum)
	{
		
		int endR = arr.length-1;
		int endC = arr[0].length-1;
		int curR = 0;//�����Ͻ���ʼ�Ƚ�
		int curC = endC;
		
		//�ڵ�ǰλ��С��numʱcurR++
		//�ڵ�ǰλ�ô���numʱcurC--
		//����һ������Խ�磬��ֹͣ
		while (curR != endR+1 && curC != 0-1 )
		{
			if (arr[curR][curC] == targetNum)
			{
				System.out.println("Has find��"+targetNum+" at ("+curR+" , "+curC+")");
				//break;//�ҵ����˳�ѭ��������
				return true;
			}else if (arr[curR][curC]>targetNum)
			{
				curC--;
			}else
			{
				curR++;
			}
		}
		return false;
	}
}
