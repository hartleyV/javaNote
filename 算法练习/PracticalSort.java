/**
*ʵ�����򣺹鲢���������򡢶�����(���õݹ飬�������ÿ���ж�->����ʱ�临�Ӷȣ�
*@author Hartley
*@version v1.0.0
*/
public class PracticalSort  
{
	

/**
********************************���鲢����**********************************************
*(���з��ε�˼�룬��������Ϊ���ȷֱ����������ߵ����ֵ��Ȼ�������ŷ���������
*ͨ���ݹ�master��ʽ�ó���ʱ�临�Ӷ�O(N*logN);����ռ临�Ӷ�O(N)---��������help
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

//�����ù鲢������������С�͡���������⣻
	
/**
********************************���������򣨳��ã�����**********************************************
*���ѡ��һ����a��Ϊ��׼����һ������������ΪС��a������a�ʹ���a���Σ�Ȼ�󷵻��м䲿�ֵĽǱ�
*ʱ�临�Ӷ�O(N*logN)--���ָ������ֵ��������ʣ���������Ϊ��;����ռ临�Ӷ�O(N)--���ʱ�����ʱΪO(1)��
*/
	public static void quickSort(int[] arr)
	{
		if (arr==null || arr.length<2)
		{
			return;
		}

		quickSort(arr,0,arr.length - 1);
	}
	public static void quickSort(int[] arr,int L,int R)
	{
		//��ֹ����
		if(L<R)
		{
			//���ָ������ֵ (1 3)- 
			int index = L + (int)( (Math.random()) *(R-L)+1);
			swap(arr,index,R);
			int[] equal = partition(arr,L,R);
			quickSort(arr,L,equal[0] - 1);
			quickSort(arr,equal[1] + 1,R);
		}
	}
	//��������ؼ���������������--����
	public static int[] partition(int[] arr,int L,int R)
	{
		//�ֱ�ָ��С�����ʹ�������ָ��
		int small = L-1;
		int large = R+1;
		//ȡ���һ��Ϊ����ֵ
		int p = arr[R];
		while(L < large)
		{
			if (arr[L] < p)
			{
				swap(arr,++small,L++);
			}else if (arr[L] > p)
			{
				//���������������ڽ�����������ֵû���뻮��ֵ�Ƚϣ����ԡ�ָ�롱L����
				swap(arr,--large,L);
			}else
			{
				//����������������ָ��Lֱ���Ƶ���һ����
				L++;
			}
		}
		return new int[]{small+1,large-1};
	}
	
/**
********************************�������򣨶ѽṹ����Ҫ����**********************************************
*�������ʾ�ѣ���ȫ������--������������������heapInsert�γɴ�ֶѣ�Ȼ��ѻ�����
*--����ֶѸ���β��Ԫ�ؽ�����������size-1���ٸ��������Ӳ��Ƚϣ�����Ϊ��ֶѺ����������ֱ��size<0����
*ʱ�临�Ӷ�O(N*logN)--;����ռ临�Ӷ�O(1)
*/
	//�����
	public static void heapSort(int[] arr)
	{

	}
	//�����--���µ��ϵĹ��̣������븸�ڵ�Ƚϣ��������ڵ�ϴ󣬾����ν�����ȥ
	public static void insertHeap(int[] arr,int index)
	{
		while( arr[index] > arr[ (index - 1)/2] )
		{
			swap(arr,index,(index-1)/2);
			index = (index-1)/2;
			System.out.println(index);

			/*
			//����Ҫ����䣬��Ϊת��intʱ (0-1)/2 = -0.5 ��������Ϊ -0
			if (index==0)
			{
				return;
			}
			*/
		}
		
	}
	//������--���ϵ��µĹ��̣��������ӽڵ�Ƚϣ����ӽڵ�ϴ󣬸��ڵ㴦Ԫ�ؾ����λ���ȥ
	public static void heapify(int[] arr,int index,int size)
	{
		//���ڵ������ڵ� (��ȫ��������
		int left = index*2 + 1;
		while( left < size)
		{
			//�Ƚ�����ڵ������ҽڵ㣬��ס�Ǳ꣨ע��left+1�ж�Խ�磩
			int large = (left + 1)<size && arr[left] < arr[left+1] ? left+1 : left;
			System.out.println(large);
			if (arr[large] > arr[index])
			{
				swap(arr,large,index);
				left = large*2 + 1;
			}
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
		int[] arr = {2,4,6,8,55,12};
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
/*
		mergeSort(arr);
		System.out.print("�ϲ������");
		printArray(arr);
*/
/*
		//������������
		int[] equal = partition(arr,0,arr.length-1);
		printArray(arr);
		printArray(equal);
*/
/*
		//��������
		quickSort(arr);
		printArray(arr);
*/

		//�����
		//insertHeap(arr,1);
		//�ѵ���
		heapify(arr,0,arr.length);
		printArray(arr);

	}


	
}
