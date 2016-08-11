package science.freeabyss.hulk.demo.util;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by abyss on 05/14/16.
 */
public class SystemTest {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        Set<Map.Entry<Object, Object>> entrySets = properties.entrySet();
        for (Map.Entry<Object, Object> entry : entrySets) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
