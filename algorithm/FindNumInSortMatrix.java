/**
*在行列都拍好顺序的M*N矩阵中找数是否存在
要求时间O(M+N)  额外空间O(1)

思路：
1    2   3  <- 利用矩阵有序，从右上或左下开始比较，判断行进方向，直至找到目标
4   5   6       
7   8   9
*@author Hartley
*@version 1.0.0
*/

class  FindNumInSortMatrix
{
	//************程序入口***************
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
		int curR = 0;//从右上角起始比较
		int curC = endC;
		
		//在当前位置小于num时curR++
		//在当前位置大于num时curC--
		//任意一个坐标越界，则停止
		while (curR != endR+1 && curC != 0-1 )
		{
			if (arr[curR][curC] == targetNum)
			{
				System.out.println("Has find："+targetNum+" at ("+curR+" , "+curC+")");
				//break;//找到后退出循环！！！
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
