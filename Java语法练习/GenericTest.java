import java.util.*;
/**
*泛型：为了让集合记住其数据类型~（传入类型实参） 
*限制丢进的元素类型（编译时即可发现）&取出不需强制转换--代码更简洁健壮
*@author Hartley
*@version 1.0.0
*/

class  GenericTest
{
	//基本用法
	public static void test1()
	{
		List<Integer> list = new ArrayList<>();//右端省略写法~只写一个“菱形”<>
		list.add(3);
		list.add(7);
		list.add(4);
		System.out.println(list);
		list.forEach(tmp->System.out.println(tmp+1));//使用泛型后，再也不用强制转换啦
		
		//泛型**可以套娃**~
		Map<String,List<String>> courseInfo = new HashMap<>();
		List<String> jackCourse = new ArrayList<>();
		jackCourse.add("Chinese");
		jackCourse.add("Math");
		jackCourse.add("SemiConductor");
		courseInfo.put("jack",jackCourse);

		System.out.println(courseInfo );
	}
	public static void main(String[] args) 
	{
		test1();
	}
}
