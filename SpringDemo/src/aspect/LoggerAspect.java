package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
//切面类

/**
 * AOP面向切面编程
 * 切面类（辅助功能）：该类是在核心业务前后分别打印日志信息
 * @author Hartley
 * @create 2020/5/21
 */
//AOP编程的注解方法【2】
@Aspect//【2】表示这是一个切面
@Component//【2】表示这是个bean
public class LoggerAspect {
    //【2】对PrProductService中所有方法进行切面操作
    @Around(value = "execution(* service.ProductService.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        //之前打印日志
        System.out.println("start log:" + joinPoint.getSignature().getName());
        //执行核心业务代码的地方
        Object object = joinPoint.proceed();
        //执行之后打印日志
        System.out.println("end log:" + joinPoint.getSignature().getName());
        return object;
    }
}
