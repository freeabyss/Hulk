package science.freeabyss.hulk.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by abyss on 08/14/16.
 */
public class ResultSetUtil {
    public static List<Map<String, Object>> toListMap(ResultSet set, String[] fields) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            while (set.next()) {
                Map<String, Object> map = new HashMap<>();
                for (String f : fields) {
                    map.put(f, set.getString(f));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void print(ResultSet set, String[] fields) {
        try {
            while (set.next()) {
                int i = set.getRow();
                System.out.printf("Row " + i + " ===>   ");
                for (String f : fields) {
                    System.out.printf(set.getString(f));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
