package science.freeabyss.hulk.demo.spring.springido2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by abyss on 3/25/16.
 */
public class Player {


    private Instrument instrument;

    @Autowired
    @Qualifier("s1")
    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
    public void play() {
        instrument.play();
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("science/freeabyss/hulk/demo/spring/src/main/java/springido2/spring-ido2.xml");
        Player player = (Player) ctx.getBean("player");
        player.play();
    }
}
