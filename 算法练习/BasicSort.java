/**
*基本排序：冒泡、选择、插入（时间复杂度为O（N^2) )
*@author Hartley
*@version v1.0.0
*/
public class BasicSort 
{
	//冒泡排序(比较后直接交换）
	public static void bubbleSort(int[] arr)
	{
		//base code
		if (arr.length<2  || arr == null)
		{
			return;
		}
		int end = arr.length - 1;

		for (int i=0;i<=end ;i++ )
		{
			for (int j=end;j>i ;j-- )
			{
				
				if( arr[i]>arr[j])
				{					
					swap(arr,i,j);
				}
			}
		}
	}
	//选择排序（比较后记住角标，最后交换）
	public static void switchSort(int[] arr)
	{
		//base code
		if (arr.length<2  || arr == null)
		{
			return;
		}

		int end = arr.length - 1;
		int index = 0;
		for (int i=0;i<=end ;i++ )
		{
			index = end;
			for (int j=end;j>i ;j-- )
			{
				if(arr[i]>arr[j])
				{
					index = j;
				}
			}
			swap(arr,i,index);			
		}
	}

	//插入排序(类似抓牌，排号当前牌，新牌依次划过去
	public static void insertSort(int[] arr)
	{
		//base code
		if (arr.length<2  || arr == null)
		{
			return;
		}
		int end = arr.length-1;
		//i为当前角标，j为新角标
		for (int i=0; i<end;i++ )
		{
			for(int j = i+1;j>0 && arr[j-1]>arr[j];j--)
			{
				swap(arr,j-1,j);
			}
			/*
			//可以简化写
			for (int j=i+1;j>0 ;j-- )
			{
				if( arr[j-1]>arr[j] )
				{
					swap(arr,j-1,j);
				}else{
					break;
				}
			}*/
		}      
		
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
