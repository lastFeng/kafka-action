/**
*
* Spring借助AspectJ的切点表达式语言来定义Spring切面：
				AspectJ指示器											描述
				arg()								限制连接点匹配参数为指定类型的执行方法
				@args()							限制连接点匹配参数由指定注解标注的执行方法
				execution()					用于匹配是连接点的执行方法
				this()							限制连接点匹配AOP代理的bean引用为指定类型的类
				target							限制连接点匹配目标对象为指定类型的类
				@target()						限制连接点匹配特定的执行对象，这些对象对应的类要具有指定类型的注解
				within							限制连接点匹配指定的类型
				@within()						限制连接点匹配指定注解所标注的类型（当使用SpringAOP时，方法定义在由指定的注解所标注的类里）
				@annotation()				限定匹配带有指定注解的连接点
				
	example：
		execution(* concert.Performance.perform(..))
		在方法    返回		方法所属类            匹配
		执行时    任意    如果触发所有方法，		任意
		触发      类型    可以使用*表示         参数
		
		
		execution(* concert.Performance.*(..)) && within(concert.*)
		
		可以使用&&、||、！进行多条件连接
*/
// 定义目标对象
package concert;
public interface Performance{
	public void perform();
}

// 定义切面
package concert;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

// 声明为切面类
@Apect
pubilc class Audience{
	@Pointcut("execution(* concert.Performance.perform(..))")
	public void performance(){}
	
	@Before("performance()")
	public void silenceCellPhones(){
		System.out.println("Silencing cell phones");
	}
	
	@Before("performance()")
	public void takeSeats(){
		System.out.println("Taking seats");
	}
	
	@AfterReturning("performance()")
	public void applause(){
		System.out.println("CLAP CLAP CLAP!!!");
	}
	
	@AfterThrowing("performance()")
	public void demandRefund(){
		System.out.println("Demanding a refund!");
	}
}

// 启用AspectJ注解的自动代理
@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class ConcertConfig{
	@Bean
	public Audience audience(){
		return new Audience();
	}
}