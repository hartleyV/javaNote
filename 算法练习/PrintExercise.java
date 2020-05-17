/**
*���顢�����ӡ��ϰ��===��۵���˼·�������ֲ�����任
������1����תȦ��ӡһ��������
1    2   3
4   5   6       =>    1  2  3  6  9  8  7  4  5  
7   8   9
˼·��ȷ����Ϊ���Ϻ��������꣬�������ڰ���У�

������2����֮���δ�ӡ--����ռ临�Ӷ�ΪO(1)
1    2   3
4   5   6       =>     1  2  4  7  5  3  6  8  9  
7   8   9
˼·��ÿ��ȷ����/���Խ��߶˵����꣬����/���´�ӡ�Խ�Ԫ�أ��˵����갴�չ����ƶ�
*@author Hartley
*@version 1.0.0
*/

class  PrintExercise
{
		//************�������***************
	public static void main(String[] args) 
	{
		//printMatrix();
		printZigzag();
		//System.out.println("Hello World!");
	}


	//��1��תȦ��ӡ���󣨶�ά���飩===��ۼܹ�
	public static void printMatrix()
	{
		int[][] arr =  { { 1, 2, 3, 4 }, 
							 { 5, 6, 7, 8 }, 
							 { 9, 10, 11, 12 },
							 { 13, 14, 15, 16 } };
		int upRow = 0;
		int upCol = 0;
		int downRow = arr.length - 1;
		int downCol = arr[0].length - 1;
		//һ��һ����������������������λʱ��ֹͣ
		while ( (downRow>=upRow) && (downCol>=upCol) )
		{
			printEdge(arr,upRow++,upCol++,downRow--,downCol--);
		}
	}

	//��ÿһ������Ϊ����ӡ�ⲿһȦ
	public static void printEdge(int[][] arr,int upRow,int upCol,int downRow,int downCol)
	{
		//����һ�л���һ��
		if (upRow == downRow)
		{
			for (int i =upCol;i<=downCol ;i++ )
			{
				System.out.print(arr[upRow][i] + " , ");
			}
		}else if (upCol == downCol)
		{
			for (int i =upRow;i<=downRow ;i++ )
			{
				System.out.print(arr[i][upCol] + " , ");
			}
		}else
		{
			int curCol = upCol;
			int curRow = upRow;
			//�����--����һ������Ȧ
			while (curCol != downCol)//����Ȧһ��
			{
				System.out.print(arr[curRow][curCol++] + " , ");
			}
			while (curRow != downRow)//����Ȧһ��
			{
				System.out.print(arr[curRow++][curCol] + " , ");
			}
			while (curCol != upCol)//����Ȧһ��
			{
				System.out.print(arr[curRow][curCol--] + " , ");
			}
			while (curRow != upRow)//����Ȧһ��
			{
				System.out.print(arr[curRow--][curCol] + " , ");
			}
		}
	}

	//��2���Խ������δ�ӡ����--
	public static void printZigzag()
	{
		int[][] arr  ={ { 1, 2, 3, 4 }, 
							 { 5, 6, 7, 8 }, 
							 { 9, 10, 11, 12 },
							 { 13, 14, 15, 16 } };
		int leftRow = 0;//��������
		int leftCol = 0;
		int upRow = 0;//��������
		int upCol = 0;
		boolean fromUp = false;
		int endRow = arr.length-1;//����߽�
		int endCol = arr[0].length-1;
		//��������������height�߽�ʱ���Ʊ����ƣ�ֱ������width�߽�
		//��������������width�߽�ʱ���Ʊ����ƣ�ֱ������height�߽�
		//������ֹ����Ϊ�����������Խ�� �� �����������Խ��
		while (upRow!=endRow+1)
		{
			
			printLevel(arr,leftRow,leftCol,upRow,upCol,fromUp);
			
			//�ж��������ƶ�����
			//������ת�۵�--���½�������е���endRow + ���Ͻ�������е���endCol
			//����Ŀ������򻯴��롿		
			leftCol  =  leftRow == endRow ? leftCol+1 : leftCol ;
			//ע�����˳�����Ҫ���߽紦��������������Ϊ���ڣ���һ��ѭ����ִ�иþ�
			leftRow = leftRow == endRow ? leftRow : leftRow+1;//ת�۵�ǰ ��������
			
			upRow = upCol == endCol ? upRow+1 : upRow;
			upCol = upCol == endCol ? upCol : upCol+1;//ת�۵�ǰ��������
			
			//System.out.println("[ left=("+leftRow+" , "+leftCol+")"+"up=("+upRow+" , "+upCol+") ]");
			fromUp = !fromUp;
		}

	}
	//�Խ��ߴ�ӡ
	public static void printLevel(int[][] arr,int leftRow,int leftCol,int upRow,int upCol,
												boolean fromUp)
	{
		if (fromUp)//�����������ӡ
		{
			while(upCol >= leftCol)
			{		
				System.out.print(arr[upRow++][upCol--] +" ");
			}
		}else//����˵������ϴ�ӡ
		{
			while(leftCol <= upCol)
			{
				System.out.print(arr[leftRow--][leftCol++] +" ");
			}
		}
	}
}
