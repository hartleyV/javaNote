class  TransMoneyName
{
	final static String[] name = {"��","Ҽ","��","��","��","��","½","��","��","��"};
	final static String[] unit = {"��","Ԫ","ʮ","��","ǧ","��"};
	//10,000 һ�� 12,345.1Ҽ����ǧ������ʮ��Ԫһ�� 

/**
*����ֵת��Ϊ��д��
*˼·��ͨ����ѧ��ģ���� ����name���� ����ɽ��
*˼·���������ַ�����ת��char���飬���ֵ�ASCII���ȥ48��Ϊ����ֵ
*@param money �����ͽ����ֵ
*@return result ����ַ���
*/
	public static String trans(float money)
	{
		String result = "";
		//pos �ӽǿ�ʼ ֱ������
		int pos = 0;
		//t1��ǰλ�õ�����0-9
		int t1=0;
		//ǰһλ�õ�����
		int t2 = 0;
		//��ǰλ�õ���ֵ<money*10
		int current=0;
		while(pos>=0)
		{
			
			t1 = ( (int)(money*10)% (int)( Math.pow(10,pos+1) )- current) /(int)( Math.pow(10,pos) );
			
			current = t1 * (int)( Math.pow(10,pos) )+current;
			
			//�ж��Ƿ����������
			if(t1==0)
			{
				//�ж���һλ�Ƿ�Ϊ��
				if(t2!=0)
				result = name[t1]+result;
				
			}else{
			//��ȡ��Ӧ�������ƺ͵�λ
				//�жϵ�ǰ��λ�Ƿ񳬳���
				if(pos<=5)
				{
					result = name[t1]+unit[pos]+result;
				}else{
					//1��230��000
					result = name[t1]+unit[pos-4]+result;
				}
			
			}
			pos++;
			t2 = t1;

			//��ֹѭ������
			if( current>= (int)(money*10) )
			{
				pos = -1;
			}
		}

		return result;
	}
	public static void main(String[] args) 
	{
		
		System.out.println(trans(1_230_012.5f));
	}
}
