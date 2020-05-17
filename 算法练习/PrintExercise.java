/**
*数组、矩阵打印练习题===宏观调度思路，摒弃局部坐标变换
案例【1】：转圈打印一数字数组
1    2   3
4   5   6       =>    1  2  3  6  9  8  7  4  5  
7   8   9
思路：确定外为左上和右下坐标，由外向内剥洋葱，

案例【2】：之字形打印--额外空间复杂度为O(1)
1    2   3
4   5   6       =>     1  2  4  7  5  3  6  8  9  
7   8   9
思路：每次确定副/主对角线端点坐标，向上/向下打印对角元素，端点坐标按照规则移动
*@author Hartley
*@version 1.0.0
*/

class  PrintExercise
{
		//************程序入口***************
	public static void main(String[] args) 
	{
		//printMatrix();
		printZigzag();
		//System.out.println("Hello World!");
	}


	//【1】转圈打印矩阵（二维数组）===宏观架构
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
		//一层一层剥开，左上与右下坐标错位时，停止
		while ( (downRow>=upRow) && (downCol>=upCol) )
		{
			printEdge(arr,upRow++,upCol++,downRow--,downCol--);
		}
	}

	//将每一步抽象为：打印外部一圈
	public static void printEdge(int[][] arr,int upRow,int upCol,int downRow,int downCol)
	{
		//单独一行或者一列
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
			//剥洋葱--剥出一个完整圈
			while (curCol != downCol)//外上圈一行
			{
				System.out.print(arr[curRow][curCol++] + " , ");
			}
			while (curRow != downRow)//外右圈一行
			{
				System.out.print(arr[curRow++][curCol] + " , ");
			}
			while (curCol != upCol)//外下圈一行
			{
				System.out.print(arr[curRow][curCol--] + " , ");
			}
			while (curRow != upRow)//外左圈一行
			{
				System.out.print(arr[curRow--][curCol] + " , ");
			}
		}
	}

	//【2】对角线依次打印矩阵--
	public static void printZigzag()
	{
		int[][] arr  ={ { 1, 2, 3, 4 }, 
							 { 5, 6, 7, 8 }, 
							 { 9, 10, 11, 12 },
							 { 13, 14, 15, 16 } };
		int leftRow = 0;//左下坐标
		int leftCol = 0;
		int upRow = 0;//右上坐标
		int upCol = 0;
		boolean fromUp = false;
		int endRow = arr.length-1;//矩阵边界
		int endCol = arr[0].length-1;
		//当左下坐标碰到height边界时下移变右移，直至碰到width边界
		//当右上坐标碰到width边界时右移变下移，直至碰到height边界
		//所以中止条件为左下坐标的列越界 或 右上坐标的行越界
		while (upRow!=endRow+1)
		{
			
			printLevel(arr,leftRow,leftCol,upRow,upCol,fromUp);
			
			//判断两坐标移动方向
			//【根据转折点--左下角坐标的行等于endRow + 右上角坐标的列等于endCol
			//用三目运算符简化代码】		
			leftCol  =  leftRow == endRow ? leftCol+1 : leftCol ;
			//注意这个顺序很重要，边界处，等下面条件变为等于，下一次循环才执行该句
			leftRow = leftRow == endRow ? leftRow : leftRow+1;//转折点前 行数增加
			
			upRow = upCol == endCol ? upRow+1 : upRow;
			upCol = upCol == endCol ? upCol : upCol+1;//转折点前列数增加
			
			//System.out.println("[ left=("+leftRow+" , "+leftCol+")"+"up=("+upRow+" , "+upCol+") ]");
			fromUp = !fromUp;
		}

	}
	//对角线打印
	public static void printLevel(int[][] arr,int leftRow,int leftCol,int upRow,int upCol,
												boolean fromUp)
	{
		if (fromUp)//从右上向左打印
		{
			while(upCol >= leftCol)
			{		
				System.out.print(arr[upRow++][upCol--] +" ");
			}
		}else//从左端点向右上打印
		{
			while(leftCol <= upCol)
			{
				System.out.print(arr[leftRow--][leftCol++] +" ");
			}
		}
	}
}
