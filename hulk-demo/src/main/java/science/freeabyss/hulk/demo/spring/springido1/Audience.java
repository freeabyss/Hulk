package science.freeabyss.hulk.demo.spring.springido1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by abyss on 3/27/16.
 */
public class Audience {
    public  void takeSeats(){
        System.out.println("The audience is taking their seats.");
    }
    public void turnOffCellPhones() {
        System.out.println("The audience is turning off their cellphones");
    }

    public void applaud() {
        System.out.println("CLAP CLAP CLAP CLAP CLAP");
    }
    public void demandRefund(){
        System.out.println("Boo! We want our money back!");
    }

public void watchPerformance(ProceedingJoinPoint joinPoint) {
    try {
        System.out.printf("通知之前");
        // 执行被通知的方法
        joinPoint.proceed();
        System.out.println("通知之后");
    } catch (Throwable throwable) {
        throwable.printStackTrace();
    }
}
    public static void main(String[] args) throws PerformanceException {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("science/freeabyss/hulk/demo/spring/src/main/java/springido1/spring-ido1.xml");
        Performer juggler = (Performer) ctx.getBean("duke");
        juggler.perform();

    }
}
