package science.freeabyss.hulk.spring;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import science.freeabyss.hulk.spring.beans.*;

/**
 * Created by abyss on 08/13/16.
 */
public class SpringBeansTest {
    ApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("hulk-spring-beans.xml");
    }

    @Test
    public void testDuke() {
        Performer performer = (Performer) context.getBean("duke");
        performer.perform();
    }

    @Test
    public void testPoeticDuke() {
        PoeticJuggler poeticJuggler = (PoeticJuggler) context.getBean("poeticDuke");
        poeticJuggler.perform();

    }

    @Test
    public void testStage() {
        Stage stage = (Stage) context.getBean("stage");
        stage.doThing();
    }

    @Test
    public void testTicket() {
        Ticket ticket1 = (Ticket) context.getBean("ticket");
        Ticket ticket2 = (Ticket) context.getBean("ticket");
        System.out.println(ticket1);
        System.out.println(ticket2);
    }

    /**
     * 初始化和销毁操作
     */
    @Test
    public void testAuditorium() {
        Auditorium auditorium = (Auditorium) context.getBean("auditorium");
        auditorium.readBook();
    }

    /**
     * setter 注入
     */
    @Test
    public void testKenny() {
        Instrumentalist kenny = (Instrumentalist) context.getBean("kenny");
        kenny.perform();
    }
    /**
     * 装配list 集合
     */
    @Test
    public void testHank() {
        OneManBand hank = (OneManBand) context.getBean("hank");
        hank.perform();
    }
}
