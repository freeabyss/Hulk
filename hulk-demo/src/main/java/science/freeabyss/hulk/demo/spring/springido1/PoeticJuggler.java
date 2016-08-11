package science.freeabyss.hulk.demo.spring.springido1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by abyss on 3/25/16.
 */
public class PoeticJuggler extends Juggler {
    private Poem poem;

    public PoeticJuggler(Poem poem) {
        super();
        this.poem = poem;
    }

    public PoeticJuggler(int beanBags, Poem poem) {
        super(beanBags);
        this.poem = poem;
    }

    @Override
    public void perform() throws PerformanceException {
        super.perform();
        System.out.println("While reciting...");
        poem.recite();
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("science/freeabyss/hulk/demo/spring/src/main/java/springido1/spring-ido1.xml");
        PoeticJuggler poeticJuggler = (PoeticJuggler) ctx.getBean("poeticDuke");
        try {
            poeticJuggler.perform();
        } catch (PerformanceException e) {
            e.printStackTrace();
        }
    }
}
