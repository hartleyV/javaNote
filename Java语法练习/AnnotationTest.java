import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.*;
/**
*注解 Annotation
		在编译、类加载、运行时读取注解，并做相应处理
	|--基本的注解==在java.lang包下
			|--@Override//在重写父类方法时声明（以防方法签名出错）
			|--@Deprecated//修饰方法、类、接口--使用被修饰的类、方法时会提示过时的warnning
			|--@SuppressWarnings//可以关闭指定value的警告
			|--@SafeWarargs//用于关闭“堆污染”的警告（泛型擦除后，再把该对象赋值给带泛型的对象 ）
			|--@FunctionInterface//只有一个抽象函数的接口--Lambda
	|--在java.lang.annotation下（用来修饰Annotation）
			|--@Retention(value = xxx)指定修饰的注解符可以保留到什么时候（只传value值，可省value=）
					vlaue = RetentionPolicy.CLASS（默认值）--annotation保留在class文件中，但运行时JVM不可获取annotation信息
					vlaue = RetentionPolicy.RUNTIME---注解也保留在class文件，且运行时可通过反射读取annotation信息
					vlaue = RetentionPolicy.SOURCE---注解只保留在源文件，编译器会丢掉这种注解信息
			|--@Target(value = xxx)指定修饰的注解符可以修饰什么
					vlaue = ElementType.ANNOTATION_TYPE: 被修饰的annotation只能修饰annotation
					                                 .CONSTRUCTOR: 修饰构造器
													 .FIELD：修饰成员变量
													 .LOCAL_VARIABLE：修饰局部变量
													 .METHOD：修饰方法
													 .PACKAGE：修饰包定义
													 .PARAMETER：修饰参数
													 .TYPE：修饰类、接口（包括注解类型）、枚举定义

			|--@Document修饰的注解符可以在javadoc时被提取到文档（方法名上有注解）					
			|--@Inherited使修饰的注解符具有继承性（父类@Inherited修饰，其子类会自动被@Inherited修饰，
			|--@Interface自定义Annotation			
	|--提取Annotation信息
	|--重复注解（java8)
	|--TypeAnnotation（java8)
	|--APT工具-编译时处理annotation工具
*@author Hartley
*@version 1.0.0
*/

@SuppressWarnings(value = "unchecked")
class  AnnotationTest
{
	//************程序入口***************
	public static void main(String[] args) 
	{
		//new Carrot().color();//@Deprecated 提示过时的warnning
		List<String> list = new ArrayList();
	}
}

//自定义注解
 @interface girl
{
	String name() default "null";//可以含成员变量（不设置默认值则在使用注解时必须指定）
}

@girl
class Vegetable
{
	public void eat()
	{
	}
}

@Target(ElementType.FIELD)
@interface testable
{
}
class Carrot extends Vegetable
{
	@Override
	//public String eat()//会检查重写方法签名，报错
	public void eat()
	{
	}

	@Deprecated
	public void color()
	{
		System.out.println("Carrot is read!");
	}
}