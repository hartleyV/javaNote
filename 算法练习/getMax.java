/**
*����������ֵ
*@author Hatley
*/
class getMax
{
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

	public static void main(String[] args) 
	{
		int[] arr = {3,5,2,7,1,5};
		//3+10+6 = 19
		System.out.println(smallAdd(arr));
	}
}
