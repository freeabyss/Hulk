package science.freeabyss.hulk.demo.spring.springido1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by abyss on 3/27/16.
 */
public class GraciousContestant implements Contestant {
    @Override
    public void receiveAward() {
        System.out.println("ReceiveAward");
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("science/freeabyss/hulk/demo/spring/src/main/java/springido1/spring-ido1.xml");
        Performer juggler = (Performer) ctx.getBean("duke");
//        juggler.
    }
}
