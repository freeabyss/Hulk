package science.freeabyss.hulk.demo.spring.springido1;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by abyss on 3/25/16.
 */
public class Instrumentalist implements Performer {
    public Instrumentalist() {
    }

    @Override
    public void perform() throws PerformanceException {
        System.out.println("Playing " + song + " : ");
        instrument.play();
    }

    private String song;

    public void setSong(String song) {
        this.song = song;
    }

    public String getSong() {
        return song;
    }

    public String screamSong(){
        return song;
    }

    private Instrument instrument;

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public static void main(String[] args) throws PerformanceException {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("science/freeabyss/hulk/demo/spring/src/main/java/springido1/spring-ido1.xml");
        Performer performer = (Performer) ctx.getBean("kenny2");
        performer.perform();
    }
}
