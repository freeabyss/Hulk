package science.freeabyss.hulk.basic.util;

/**
 * Created by abyss on 05/03/16.
 */
public class TimeTest {
    private static long millisTime;
    private static long nanoTime;

    public static void initTime() {
        nanoTime = System.nanoTime();
        millisTime = System.currentTimeMillis();
    }

    public static String outNanoTime(String msg) {
        String out = msg + (System.nanoTime() - nanoTime);
        System.out.println(out);
        return out;
    }

    public static String outMillisTime(String msg) {
        String out = msg + (System.currentTimeMillis() - millisTime);
        System.out.println(out);
        return out;
    }
}
