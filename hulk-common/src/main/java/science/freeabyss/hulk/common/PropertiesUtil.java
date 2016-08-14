/*
 * Author zhenwen_li 2016-02-23
 * */

package science.freeabyss.hulk.common;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties properTies;

    private static PropertiesUtil propUtil = null;

    private PropertiesUtil() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("jdbc.properties"), "UTF-8");
        properTies = new Properties();
        properTies.load(inputStreamReader);

    }


    public static PropertiesUtil getPropertiesUtil() throws IOException {
        if (propUtil == null) {
            propUtil = new PropertiesUtil();
        }
        return propUtil;
    }

    public static String getString(String key) {
        if (properTies == null) {
            try {
                getPropertiesUtil();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properTies.getProperty(key).trim();
    }

}
