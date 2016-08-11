package science.freeabyss.hulk.demo.generic.coffee;

/**
 * Created by abyss on 3/24/16.
 */
public class Coffee {
    private static long counter = 0;
    private final long id = counter++;
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}
