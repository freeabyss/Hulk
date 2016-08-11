package science.freeabyss.hulk.junit.tcase;

import org.junit.Assert;
import org.junit.Test;
import science.freeabyss.hulk.junit.util.MessageUtil;

/**
 * Created by abyss on 07/15/16.
 */
public class TestJunit {
    String message = "Hello World";
    MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testPrintMessage() {
        Assert.assertEquals(message, messageUtil.printMessage());
    }
}
