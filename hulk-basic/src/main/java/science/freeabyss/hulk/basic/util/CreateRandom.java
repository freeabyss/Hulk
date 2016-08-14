package science.freeabyss.hulk.basic.util;

import java.util.*;

/**
 * 用于生成随机集合,数组等
 * Created by abyss on 05/03/16.
 */
public class CreateRandom {
    /**
     * 随机生成size数量的map
     */
    public static Map<String, Object> getRandomHashMap(int size) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            map.put(getRandomString(5), getRandomString(10));
        }
        return map;
    }

    public static List<String> getRandomArrayList(int size) {
        List<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(getRandomString(10));
        }
        return list;
    }

    public static String getRandomString(int length) {
        byte[] c = new byte[length];
        for (int i = 0; i < length; i++) {
            c[i] = (byte) ('a' + getRandom(26));
        }
        return new String(c);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getRandomString(10));

        }
    }

    /**
     * 随机生成 [min,max)之间的整数
     */
    public static int getRandomInt(int min, int max) {
        if (max < min) {
            throw new IllegalArgumentException("max < min");
        }
        return getRandom(max - min) + min;
    }

    /**
     * 随机生成 [0,max)之间的整数
     */
    public static int getRandom(int max) {
        return new Random(System.nanoTime()).nextInt(max);
    }

    /**
     * 随机生成 [min,max)之间的浮点数
     */
    public static double getRandomDouble(double min, double max) {
        if (max < min) {
            throw new IllegalArgumentException("max < min");
        }
        Random random = new Random(System.nanoTime());
        return Math.abs(random.nextFloat() * (max - min)) + min;
    }
}
