package science.freeabyss.hulk.demo.annotation.database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abyss on 3/22/16.
 */
public class TableCreator {
    public static void main(String[] args) throws ClassNotFoundException {
        createTable("annotation.database.Member");
    }

    public static void createTable(String className) throws ClassNotFoundException {
        Class<?> cl = Class.forName(className);
        DBTable dbTable = cl.getAnnotation(DBTable.class);
        if (dbTable == null) {
            return;
        }
        String tableName = dbTable.name();
        if (tableName.length() < 1) {
            tableName = cl.getName().toUpperCase();
        }
        List<String> columnDefs = new ArrayList<>();
        for (Field field : cl.getDeclaredFields()) {
            String columnName;
            Annotation[] anns = field.getDeclaredAnnotations();
            if (anns == null || anns.length < 1) {
                continue;
            }
            if (anns[0] instanceof SQLInteger) {
                SQLInteger sInt = (SQLInteger) anns[0];
                if (sInt.name().length() < 1) {
                    columnName = field.getName().toUpperCase();
                } else {
                    columnName = sInt.name();
                }
                columnDefs.add(columnName + " INT" + getConstraints(sInt.constraints()));
            }
            if (anns[0] instanceof SQLString) {
                SQLString sStr = (SQLString) anns[0];
                if (sStr.name().length() < 1) {
                    columnName = field.getName().toUpperCase();
                } else {
                    columnName = sStr.name();
                }
                columnDefs.add(columnName + " VARCHAR(" + sStr.value() + ") " + getConstraints(sStr.constraints()));
            }
        }
        StringBuilder createCommand = new StringBuilder();
        createCommand.append("CREATE TABLE " + tableName + " (");
        for (String columnDef : columnDefs) {
            createCommand.append("\n ").append(columnDef).append(",");
        }
        String tableCreate = createCommand.substring(0, createCommand.length() - 1) + ");";
        System.out.println(tableCreate);

    }

    public static String getConstraints(Constraints con) {
        String constraints = "";
        if (!con.allowNull()) {
            constraints += " NOT NULL";
        }
        if (con.primaryKey()) {
            constraints += " PRIMARY KEY";
        }
        if (con.unique()) {
            constraints += " UNIQUE";
        }
        return constraints;
    }
}
