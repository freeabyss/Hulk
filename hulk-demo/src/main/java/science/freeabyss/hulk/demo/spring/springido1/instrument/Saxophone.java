package science.freeabyss.hulk.demo.spring.springido1.instrument;

import science.freeabyss.hulk.demo.spring.springido1.Instrument;

/**
 * Created by abyss on 3/25/16.
 */
public class Saxophone implements Instrument {
    @Override
    public void play() {
        System.out.println("TOOT TOOT TOOT");
    }
}
