package science.freeabyss.hulk.spring.beans;

/**
 * Created by abyss on 08/13/16.
 */
public class Guitar implements Instrument {
    @Override
    public void play() {
        System.out.println("Guitar");
    }
}
