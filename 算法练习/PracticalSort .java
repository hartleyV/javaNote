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
	public static int mergeSort(int[] arr,int L,int R)
	{
		//������ֹ���
		if (L==R)
		{
			return arr[L];
		}
		//�������Ϊ������
		int mid = L + (L+R)>>2;
		int maxLeft = mergeSort(arr,L,mid);
		int maxRight = mergeSort(arr,mid+1,R);

		return merge(arr,L,R);

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
		insertSort(arr);
		System.out.print("���������");
		printArray(arr);
	}
}
