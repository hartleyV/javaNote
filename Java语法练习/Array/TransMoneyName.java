class  TransMoneyName
{
	final static String[] name = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
	final static String[] unit = {"角","元","十","佰","千","万"};
	//10,000 一万 12,345.1壹万两千三百四十伍元一角 

/**
*将数值转换为大写金额。
*思路：通过数学的模运算 加上name数组 来组成金额
*思路二：利用字符串，转成char数组，数字的ASCII码减去48即为其数值
*@param money 浮点型金额数值
*@return result 金额字符串
*/
	public static String trans(float money)
	{
		String result = "";
		//pos 从角开始 直到结束
		int pos = 0;
		//t1当前位置的数字0-9
		int t1=0;
		//前一位置的数字
		int t2 = 0;
		//当前位置的数值<money*10
		int current=0;
		while(pos>=0)
		{
			
			t1 = ( (int)(money*10)% (int)( Math.pow(10,pos+1) )- current) /(int)( Math.pow(10,pos) );
			
			current = t1 * (int)( Math.pow(10,pos) )+current;
			
			//判断是否接连零的情况
			if(t1==0)
			{
				//判断上一位是否为零
				if(t2!=0)
				result = name[t1]+result;
				
			}else{
			//提取对应中文名称和单位
				//判断当前单位是否超出万
				if(pos<=5)
				{
					result = name[t1]+unit[pos]+result;
				}else{
					//1，230，000
					result = name[t1]+unit[pos-4]+result;
				}
			
			}
			pos++;
			t2 = t1;

			//终止循环条件
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
