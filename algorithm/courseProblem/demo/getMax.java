package algorithm.courseProblem.demo;

/**
*迭代法求最值
*@author Hatley
*/
class getMax
{
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

	public static void main(String[] args) 
	{
		int[] arr = {3,5,2,7,1,5};
		//3+10+6 = 19
		System.out.println(getMax(arr,0,arr.length-1));
	}
}
