package science.freeabyss.hulk.demo.spring.springido1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by abyss on 3/24/16.
 */
public class Juggler implements Performer {

    private int beanBags = 3;

    public Juggler() {
    }

    public Juggler(int beanBags) {
        this.beanBags = beanBags;
    }

    @Override
    public void perform() throws PerformanceException {
        System.out.println("JUGGLING " + beanBags + " BEANBAGS");
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("science/freeabyss/hulk/demo/spring/src/main/java/springido1/spring-ido1.xml");
        Performer performer = (Performer) ctx.getBean("duke");
        try {
            performer.perform();
        } catch (PerformanceException e) {
            e.printStackTrace();
        }
    }
}
