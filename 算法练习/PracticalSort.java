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
	//重载一个默认只有数组参数方法
	public static void mergeSort(int[] arr)
	{
		if (arr ==null || arr.length<2)
		{
			return;
		}

		mergeSort(arr,0,arr.length-1);
	}
	public static void mergeSort(int[] arr,int L,int R)
	{
		//迭代终止情况
		if (L==R)
		{
			return ;
		}
		//将问题分为两部分
		//int mid = (L +R)/2;
		int mid = L+((R-L)>>1);//更安全~
		mergeSort(arr,L,mid);
		mergeSort(arr,mid+1,R);

		merge(arr,L,mid,R);
		//return merge(arr,L,R);

	}
	//用外排的方式将其合并
	public static void merge(int[] arr,int L,int mid,int R)
	{
		//辅助数组！
		int[] help = new int[R-L+1];
		int pos_a = L;
		int pos_b = mid +1;
		int i=0;
		for (;pos_a<=mid && pos_b<=R ; )
		{
			help[i++] = arr[pos_a]>arr[pos_b]? arr[pos_b++]:arr[pos_a++];
		}
		//其中一个越界后 另一个直接填充到辅助数组中
		while(pos_a<=mid)
		{
			help[i++] = arr[pos_a++];
		}
		while(pos_b<=R)
		{
			help[i++] = arr[pos_b++];
		}

		for (i= 0;i< help.length ;i++ )
		{
			arr[L+i] = help[i];
		}
	}


	//迭代求最大值
	public static int getMax(int[] arr,int L,int R)
	{
		//迭代终止情况
		if (L==R)
		{
			return arr[L];
		}
		//将问题分为两部分
		//int mid = (L +R)/2;
		int mid = L- ((L - R)>>1);//更安全~
		int maxLeft = getMax(arr,L,mid);
		int maxRight = getMax(arr,mid+1,R);
		
		return (int)Math.max(maxLeft,maxRight);

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
/*		insertSort(arr);
		System.out.print("插入排序后：");
		printArray(arr);
*/
		/*
		int temp = 3+((2+3)>>1);//注意移位运算优先级比算数符低
		System.out.print("移位后："+temp);
		*/
		mergeSort(arr);
		System.out.print("合并排序后：");
		printArray(arr);
	}
}
