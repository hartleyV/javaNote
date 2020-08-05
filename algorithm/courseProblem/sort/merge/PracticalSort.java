package algorithm.courseProblem.sort.merge;

/**
*实用排序：归并、快速排序、堆排序(运用递归，充分利用每次判断->降低时间复杂度）
*@author Hartley
*@version v1.0.0
*/
public class PracticalSort  
{
	

/**
********************************【归并排序】**********************************************
*(运行分治的思想，将问题拆解为：先分别求左右两边的最大值，然后用外排法最终排序）
*通过递归master公式得出：时间复杂度O(N*logN);额外空间复杂度O(N)---辅助数组help
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

//可以用归并排序衍生到求：小和、逆序对问题；
	
/**
********************************【快速排序（常用！！】**********************************************
*随机选择一个数a作为基准，将一组数按该数分为小于a、等于a和大于a三段，然后返回中间部分的角标
*时间复杂度O(N*logN)--随机指定划分值，引入概率，长期期望为此;额外空间复杂度O(N)--最差时（最好时为O(1)）
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
		//终止条件
		if(L<R)
		{
			//随机指定划分值 (1 3)- 
			int index = L + (int)( (Math.random()) *(R-L)+1);
			swap(arr,index,R);
			int[] equal = partition(arr,L,R);
			quickSort(arr,L,equal[0] - 1);
			quickSort(arr,equal[1] + 1,R);
		}
	}
	//快速排序关键：荷兰国旗问题--划分
	public static int[] partition(int[] arr,int L,int R)
	{
		//分别指向小于区和大于区的指针
		int small = L-1;
		int large = R+1;
		//取最后一项为划分值
		int p = arr[R];
		while(L < large)
		{
			if (arr[L] < p)
			{
				swap(arr,++small,L++);
			}else if (arr[L] > p)
			{
				//划到大于区，由于交换过来的数值没有与划分值比较，所以“指针”L不动
				swap(arr,--large,L);
			}else
			{
				//等于区，不交换，指针L直接移到下一个数
				L++;
			}
		}
		return new int[]{small+1,large-1};
	}
	
/**
********************************【堆排序（堆结构很重要！】**********************************************
*用数组表示堆（完全二叉树--由左到右依次排满），heapInsert形成大粗堆，然后堆化处理
*--将大粗堆根与尾部元素交换，二叉树size-1，再根部与其子部比较，调整为大粗堆后继续交换，直至size<0截至
*时间复杂度O(N*logN)--;额外空间复杂度O(1)
*/
	//先生成大根堆，再砍根，再调整，再砍根，直至砍完即达成排序
	public static void heapSort(int[] arr)
	{
		for (int i=0;i<arr.length ;i++ )
		{
			insertHeap(arr,i);
		}
		System.out.print("排完大根树后");     
		printArray(arr);

		int size = arr.length;

		while(size>0)
		{
			swap(arr,0,--size);
			//System.out.println(size);
			heapify(arr,0,size);
		}

	}
	//构造堆--由下到上的过程：从零开始依次与父节点比较，如果插入节点较大，就依次交换上去
	public static void insertHeap(int[] arr,int index)
	{
		while( arr[index] > arr[ (index - 1)/2] )
		{
			swap(arr,index,(index-1)/2); 
			index = (index-1)/2;

			/*
			//不需要该语句，因为转换int时 (0-1)/2 = -0.5 整数部分为 -0
			if (index==0)
			{
				return;
			}
			*/
		}
		
	}
	//调整堆--由上到下的过程：依次与子节点比较，若子节点较大，根节点处元素就依次换下去
	public static void heapify(int[] arr,int index,int size)
	{
		//父节点的子左节点 (完全二叉树）
		int left = index*2 + 1;
		//进入循环说明有子节点
		while( left < size)
		{
			//最大子节点角标：比较子左节点与子右节点，记住角标（注意left+1判断越界）
			int large = (left + 1)<size && arr[left] < arr[left+1] ? left+1 : left;
			//交换前比较当前节点index与其最大子节点大小，
			large = arr[large] > arr[index] ? large : index;
			//如果子节点不比父节点大，退出循环
			if (large == index)
			{
				break;
			}
			swap(arr,large,index);
			//重置父节点
			index = large;
			left = large*2 + 1;
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
		int[] arr = {5,6,9,9,9,4,8,9,1,9,9};
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
/*
		mergeSort(arr);
		System.out.print("合并排序后：");
		printArray(arr);
*/
/*
		//荷兰国旗问题
		int[] equal = partition(arr,0,arr.length-1);
		printArray(arr);
		printArray(equal);
*/
/*
		//快速排序
		quickSort(arr);
		printArray(arr);
*/

		//插入堆
		//insertHeap(arr,arr.length-1);
		//堆调整
		//heapify(arr,0,arr.length);
		heapSort(arr);
		System.out.print("堆排序完成：");
		printArray(arr);

	}


	
}
