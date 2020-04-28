/**
*实用排序：归并、堆、XX(运用递归，充分利用每次判断->降低时间复杂度）
*@author Hartley
*@version v1.0.0
*/
public class PracticalSort  
{
	

/**
*归并排序(运行分治的思想，将问题拆解为：先分别求左右两边的最大值，然后用外排法最终排序）
*通过递归master公式得出：时间复杂度O(N*logN)
*/
	public static int mergeSort(int[] arr,int L,int R)
	{
		//迭代终止情况
		if (L==R)
		{
			return arr[L];
		}
		//将问题分为两部分
		int mid = L + (L+R)>>2;
		int maxLeft = mergeSort(arr,L,mid);
		int maxRight = mergeSort(arr,mid+1,R);

		return merge(arr,L,R);

	}

	//交换数组中指定位置的元素
	public static void swap(int[] arr,int i,int j)
	{
		//base code
		if (arr!=null && (0<=i&&i<arr.length) && (0<=j&&j<arr.length) )
		{
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;

		}
	}
	//打印数组
	public static void printArray(int[] arr)
	{
		if(arr==null)
			return;

		for (int i=0;i<arr.length ;i++ )
		{
			System.out.print(arr[i] + " , ");
		}
		System.out.println("");
	}

	//程序入口
	public static void main(String[] args) 
	{
		int[] arr = {2,4,1,7,9,3,6};
		System.out.print("初始数组为：");
		printArray(arr);
/*
		bubbleSort(arr);
		System.out.print("冒泡排序后：");
		printArray(arr);
*/
/*		switchSort(arr);
		System.out.print("选择排序后：");
		printArray(arr);
*/
		insertSort(arr);
		System.out.print("插入排序后：");
		printArray(arr);
	}
}
