package science.freeabyss.hulk.demo.spring.springido2;

import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by abyss on 3/25/16.
 */
@Qualifier("s1")
public class HammeredDulcimer implements Instrument {

    @Override
    public void play() {
        System.out.println("HammeredDulcimer");
    }
}
