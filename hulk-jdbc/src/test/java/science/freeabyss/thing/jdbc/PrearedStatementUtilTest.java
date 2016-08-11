package science.freeabyss.thing.jdbc;

import org.junit.Test;

/**
 * Created by abyss on 07/15/16.
 */
public class PrearedStatementUtilTest {
    @Test
    public void select() {
        PrearedStatementUtil.select("select * from Article");
    }
}
