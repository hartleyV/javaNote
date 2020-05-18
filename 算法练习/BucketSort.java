import java.util.*;
/**
*桶排序
	可以实现时间复杂度、空间额外复杂度为O(N)
	但是需要分析数据
*案例【1】：给定一个数字数组，进行排序；
    |--思路：求出数组最大值max，创建长度为max+1的helper，在辅助数组对应索引位置
				  记录数值词频
*案例【2】：给定数值数组，要求在O(N)内求出最大相邻差值（不许排序）
    |--思路：求出数值数组最大值和最小值，创建length+1个桶（有空桶），所以最大差值不可能
				  出现在桶的内部，根据当前数值num-min占总区间max-min的比例，对应出桶号，求
				  出每个桶的最值，依次遍历上一个桶min减去当前桶的max，直至挑出最大值
				  
*@author Hartley
*@version 1.0.0
*/

class  BucketSort
{
	//【1】bucket排序
	public static void bucketTest()
	{
		int[] arr ={4,0,5,44,23,13,47,32,67,110,13,200} ;//0-200以内的
		int max = Integer.MIN_VALUE;//创建数组前需要分析出数据中最大值
		for (int i = 0;i<arr.length ;i++ )
		{
			max = Math.max(max,arr[i]);
		}
		System.out.println( max );
		int[] helper = new int[max+1];//辅助数组“桶”范围为 [0,max + 1)

		for (int i=0; i<arr.length;i++ )
		{
			helper[ arr[i] ]++;//获取完词频！！注意桶中是词出现的次数
		}
		
		int j = 0;
		for (int i=0;i<helper.length ;i++ )
		{
			while (helper[i]-- >0)
			{
				arr[j++] = i;
			}
			 
		}

		System.out.println( Arrays.toString(arr) );
	}

	//【2】求出相邻数组最值（桶结构的应用）
	public static int getAdjoinMax()
	{
		int[] arr ={4,0,5,44,23,13,47,32,67,110,13,200} ;//没考虑负数的情况
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int len = arr.length;
		
		for (int i=0;i<arr.length ;i++ )//取到数组最值
		{
			max = Math.max(max,arr[i]);
			min = Math.min(min,arr[i]);
		}
		if (min == max)//最值相同直接返回0
		{
			return 0;
		}

		boolean[] hasNum = new boolean[len+1];//判断当前位置有无数值
		int[] bmax = new int[len+1 ];//创建桶--存最大值
		int[] bmin = new int[len+1 ];

		for (int i=0;i<len ;i++ )
		{
			int bid = bucketId(arr[i],max,min,len);//取得当前元素所在桶的桶号
			//System.out.println("bid: "+bid);

			bmin[bid] = hasNum[bid]? Math.min( bmin[bid],arr[i] ) : arr[i];//获取/更新当前桶最小值
			bmax[bid] = hasNum[bid]? Math.max( bmax[bid],arr[i] ) : arr[i];//获取/更新当前桶最小值
			hasNum[bid] = true;//************重要*******要更新当前桶中有值啦！
		}

		int res = 0;
		//遍历桶，求出前一个 min减当前max的最大值，即为相邻最大值
		for (int i=0; i<len;i++ )
		{
			if (hasNum[i])
			{
				res = Math.max( res , (bmin[i+1] - bmax[i]) );
			}
		}
		
		System.out.println("原始数组为："+Arrays.toString(arr) );
		System.out.println("最小桶数组为："+Arrays.toString(bmin) );
		System.out.println("数组相邻最大值为："+res);

		return res;
	}
	//根据当前num计算其所在桶号
	public static int bucketId(int num,int max,int min,int len)
	{
		return (int) ( (num-min)*len/(max-min) );//桶的长度为len+1，桶号范围为0-len
	}
	//************程序入口***************
	public static void main(String[] args) 
	{
		//bucketTest();
		 getAdjoinMax();
	}
}
