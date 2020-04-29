/**
*ʵ�����򣺹鲢���ѡ�XX(���õݹ飬�������ÿ���ж�->����ʱ�临�Ӷȣ�
*@author Hartley
*@version v1.0.0
*/
public class PracticalSort  
{
	

/**
*�鲢����(���з��ε�˼�룬��������Ϊ���ȷֱ����������ߵ����ֵ��Ȼ�������ŷ���������
*ͨ���ݹ�master��ʽ�ó���ʱ�临�Ӷ�O(N*logN)
*/
	//����һ��Ĭ��ֻ�������������
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
		//������ֹ���
		if (L==R)
		{
			return ;
		}
		//�������Ϊ������
		//int mid = (L +R)/2;
		int mid = L+((R-L)>>1);//����ȫ~
		mergeSort(arr,L,mid);
		mergeSort(arr,mid+1,R);

		merge(arr,L,mid,R);
		//return merge(arr,L,R);

	}
	//�����ŵķ�ʽ����ϲ�
	public static void merge(int[] arr,int L,int mid,int R)
	{
		//�������飡
		int[] help = new int[R-L+1];
		int pos_a = L;
		int pos_b = mid +1;
		int i=0;
		for (;pos_a<=mid && pos_b<=R ; )
		{
			help[i++] = arr[pos_a]>arr[pos_b]? arr[pos_b++]:arr[pos_a++];
		}
		//����һ��Խ��� ��һ��ֱ����䵽����������
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


	//���������ֵ
	public static int getMax(int[] arr,int L,int R)
	{
		//������ֹ���
		if (L==R)
		{
			return arr[L];
		}
		//�������Ϊ������
		//int mid = (L +R)/2;
		int mid = L- ((L - R)>>1);//����ȫ~
		int maxLeft = getMax(arr,L,mid);
		int maxRight = getMax(arr,mid+1,R);
		
		return (int)Math.max(maxLeft,maxRight);

	}
	//����������ָ��λ�õ�Ԫ��
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
	//��ӡ����
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

	//�������
	public static void main(String[] args) 
	{
		int[] arr = {2,4,1,7,9,3,6};
		System.out.print("��ʼ����Ϊ��");
		printArray(arr);
/*
		bubbleSort(arr);
		System.out.print("ð�������");
		printArray(arr);
*/
/*		switchSort(arr);
		System.out.print("ѡ�������");
		printArray(arr);
*/
/*		insertSort(arr);
		System.out.print("���������");
		printArray(arr);
*/
		/*
		int temp = 3+((2+3)>>1);//ע����λ�������ȼ�����������
		System.out.print("��λ��"+temp);
		*/
		mergeSort(arr);
		System.out.print("�ϲ������");
		printArray(arr);
	}
}
