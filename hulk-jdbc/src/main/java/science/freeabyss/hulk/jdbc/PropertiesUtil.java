/*
 * Author zhenwen_li 2016-02-23
 * */

package science.freeabyss.hulk.jdbc;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties properTies;

    static {
        init("jdbc.properties");
    }

    public static void init(String name) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(PropertiesUtil.class.getClassLoader()
                    .getResourceAsStream(name), "UTF-8");
            properTies = new Properties();
            properTies.load(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getString(String key) {
        return properTies.getProperty(key).trim();
    }

    public static int getInteger(String key) {
        String value = getString(key);
        return Integer.parseInt(value);
    }

    public static boolean getBoolean(String key) {
        String value = getString(key);
        return new Boolean(value);
    }
}
