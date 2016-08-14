package science.freeabyss.hulk.basic.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by abyss on 05/03/16.
 */
public class ForEachMap {

    public static void main(String[] args) {
        testIteratorAndForEach();

    }

    public static void testIteratorAndForEach() {
        Map<String, Object> map = CreateRandom.getRandomHashMap(23943434);
        TimeTest.initTime();
        iteratorEntry(map);
        String out = TimeTest.outNanoTime("IteratorEntry:");
        TimeTest.initTime();
        eachEntry(map);
        TimeTest.outNanoTime("eachEntry:");
        System.out.println(out);
    }
    public static void iteratorEntry(Map<String, Object> map) {
        Iterator<Map.Entry<String, Object>> entrys = map.entrySet().iterator();
        while (entrys.hasNext()) {
            entrys.next();
//            System.out.println("key:" + entry.getKey() + "value:" + entry.getValue());
        }
    }

    public static void iteratorKeys(Map<String, Object> map) {
        Iterator<String> keys = map.keySet().iterator();
        while (keys.hasNext()) {
            keys.next();
//            System.out.println("key:" + keys.next());
        }
    }

    public static void iteratorValues(Map<String, Object> map) {
        Iterator<Object> values = map.values().iterator();
        while (values.hasNext()) {
            values.next();
//            System.out.println("value:" + values.next());
        }
    }

    public static void eachEntry(Map<String, Object> map) {
        Set<Map.Entry<String, Object>> entrys = map.entrySet();
        for (Map.Entry<String, Object> entry : entrys) {
            entry.getKey();
            entry.getValue();
//            System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
        }
    }

    public static void eachkeys(Map<String, Object> map) {
        Set<String> keys = map.keySet();
        for (String key : keys) {
//            System.out.println("key:" + key);

        }
    }

    public static void eachValues(Map<String, Object> map) {
        Collection<Object> values = map.values();
        for (Object o : values) {
//            System.out.println("value:" + o);

        }
    }
}
