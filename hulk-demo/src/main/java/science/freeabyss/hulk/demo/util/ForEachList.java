package science.freeabyss.hulk.demo.util;

import java.util.Iterator;
import java.util.List;

/**
 * Created by abyss on 05/03/16.
 */
public class ForEachList {
    public static void main(String[] args) {
        List<String> list = CreateRandom.getRandomArrayList(3478374);
        TimeTest.initTime();
        forEachList(list);
        TimeTest.outNanoTime("forEachList:");

        TimeTest.initTime();
        iteratorList(list);
        TimeTest.outNanoTime("iteratorList:");

        TimeTest.initTime();
        forList(list);
        TimeTest.outNanoTime("forList:");
    }

    public static void forEachList(List<String> list) {
        for (String element : list) {

        }
    }

    public static void iteratorList(List<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
    }

    public static void forList(List<String> list) {
        for (int i = 0, size = list.size(); i < size; i++) {
            list.get(i);
        }
    }
}
