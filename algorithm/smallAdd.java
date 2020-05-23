/**
*用归并排序衍生到求：小和、逆序对问题；
*@author Hatley
*/
class smallAdd 
{
	public static int smallAdd(int[] arr)
	{
		if(arr == null )
		{
			return -1;//错误
		}
		return smallAdd(arr,0,arr.length-1);
	}
	public static int smallAdd(int[] arr,int L,int R)
	{
		if(L == R)
		{
			return 0;
		}
		int mid = L + ((R - L)/2);
		int smallLeft = smallAdd(arr,L,mid);
		int smallRight = smallAdd(arr,mid+1,R);
		return  smallLeft+  smallRight + merge(arr,L,mid,R);
	}

	public static int merge(int[] arr,int L,int mid,int R)
	{
		int[] help = new int[R - L +1];
		int sum = 0;
		int i = 0;
		int p1 = L;
		int p2 = mid+1;
		while(p1<=mid && p2<=R)
		{
			//小数求和重点！依据索引看分好块的右边有多少个比当前左边值大的个数
			sum += arr[p1]<arr[p2] ? arr[p1]*(R-p2+1): 0;
			help[i++] = arr[p1]<arr[p2] ? arr[p1++]: arr[p2++];
		}

		//合并剩余部分
		while(p1<=mid)
		{
			help[i++] = arr[p1++];
		}
		while(p2<=R)
		{
			help[i++] = arr[p2++];
		}

		for (i = 0;i<help.length ;i++ )
		{
			arr[i+L] = help[i];
		}

		return sum;
	}
	public static void main(String[] args) 
	{
		int[] arr = {3,5,2,7,1,5};
		//3+10+6 = 19
		System.out.println(smallAdd(arr));
	}
}
