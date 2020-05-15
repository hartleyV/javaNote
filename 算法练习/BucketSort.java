import java.util.*;
/**
*Ͱ����
	����ʵ��ʱ�临�Ӷȡ��ռ���⸴�Ӷ�ΪO(N)
	������Ҫ��������
*������1��������һ���������飬��������
    |--˼·������������ֵmax����������Ϊmax+1��helper���ڸ��������Ӧ����λ��
				  ��¼��ֵ��Ƶ
*������2����������ֵ���飬Ҫ����O(N)�����������ڲ�ֵ����������
    |--˼·�������ֵ�������ֵ����Сֵ������length+1��Ͱ���п�Ͱ������������ֵ������
				  ������Ͱ���ڲ������ݵ�ǰ��ֵnum-minռ������max-min�ı�������Ӧ��Ͱ�ţ���
				  ��ÿ��Ͱ����ֵ�����α�����һ��Ͱmin��ȥ��ǰͰ��max��ֱ���������ֵ
				  
*@author Hartley
*@version 1.0.0
*/

class  BucketSort
{
	//��1��bucket����
	public static void bucketTest()
	{
		int[] arr ={4,0,5,44,23,13,47,32,67,110,13,200} ;//0-200���ڵ�
		int max = Integer.MIN_VALUE;//��������ǰ��Ҫ���������������ֵ
		for (int i = 0;i<arr.length ;i++ )
		{
			max = Math.max(max,arr[i]);
		}
		System.out.println( max );
		int[] helper = new int[max+1];//�������顰Ͱ����ΧΪ [0,max + 1)

		for (int i=0; i<arr.length;i++ )
		{
			helper[ arr[i] ]++;//��ȡ���Ƶ����ע��Ͱ���Ǵʳ��ֵĴ���
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

	//��2���������������ֵ��Ͱ�ṹ��Ӧ�ã�
	public static int getAdjoinMax()
	{
		int[] arr ={4,0,5,44,23,13,47,32,67,110,13,200} ;//û���Ǹ��������
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int len = arr.length;
		
		for (int i=0;i<arr.length ;i++ )//ȡ��������ֵ
		{
			max = Math.max(max,arr[i]);
			min = Math.min(min,arr[i]);
		}
		if (min == max)//��ֵ��ֱͬ�ӷ���0
		{
			return 0;
		}

		boolean[] hasNum = new boolean[len+1];//�жϵ�ǰλ��������ֵ
		int[] bmax = new int[len+1 ];//����Ͱ--�����ֵ
		int[] bmin = new int[len+1 ];

		for (int i=0;i<len ;i++ )
		{
			int bid = bucketId(arr[i],max,min,len);//ȡ�õ�ǰԪ������Ͱ��Ͱ��
			//System.out.println("bid: "+bid);

			bmin[bid] = hasNum[bid]? Math.min( bmin[bid],arr[i] ) : arr[i];//��ȡ/���µ�ǰͰ��Сֵ
			bmax[bid] = hasNum[bid]? Math.max( bmax[bid],arr[i] ) : arr[i];//��ȡ/���µ�ǰͰ��Сֵ
			hasNum[bid] = true;//************��Ҫ*******Ҫ���µ�ǰͰ����ֵ����
		}

		int res = 0;
		//����Ͱ�����ǰһ�� min����ǰmax�����ֵ����Ϊ�������ֵ
		for (int i=0; i<len;i++ )
		{
			if (hasNum[i])
			{
				res = Math.max( res , (bmin[i+1] - bmax[i]) );
			}
		}
		
		System.out.println("ԭʼ����Ϊ��"+Arrays.toString(arr) );
		System.out.println("��СͰ����Ϊ��"+Arrays.toString(bmin) );
		System.out.println("�����������ֵΪ��"+res);

		return res;
	}
	//���ݵ�ǰnum����������Ͱ��
	public static int bucketId(int num,int max,int min,int len)
	{
		return (int) ( (num-min)*len/(max-min) );//Ͱ�ĳ���Ϊlen+1��Ͱ�ŷ�ΧΪ0-len
	}
	//************�������***************
	public static void main(String[] args) 
	{
		//bucketTest();
		 getAdjoinMax();
	}
}
