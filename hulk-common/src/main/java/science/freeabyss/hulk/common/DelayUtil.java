package science.freeabyss.hulk.common;

/**
 * Created by abyss on 08/14/16.
 */
public class DelayUtil {

    public static void delayMillis(long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
