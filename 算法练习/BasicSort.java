/**
*��������ð�ݡ�ѡ�񡢲��루ʱ�临�Ӷ�ΪO��N^2) )
*@author Hartley
*@version v1.0.0
*/
public class BasicSort 
{
	//ð������(�ȽϺ�ֱ�ӽ�����
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
	//ѡ�����򣨱ȽϺ��ס�Ǳ꣬��󽻻���
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

	//��������(����ץ�ƣ��źŵ�ǰ�ƣ��������λ���ȥ
	public static void insertSort(int[] arr)
	{
		//base code
		if (arr.length<2  || arr == null)
		{
			return;
		}
		int end = arr.length-1;
		//iΪ��ǰ�Ǳ꣬jΪ�½Ǳ�
		for (int i=0; i<end;i++ )
		{
			for(int j = i+1;j>0 && arr[j-1]>arr[j];j--)
			{
				swap(arr,j-1,j);
			}
			/*
			//���Լ�д
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
