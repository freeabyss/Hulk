package science.freeabyss.hulk.junit.util;

/**
 * Created by abyss on 07/15/16.
 */
public class MessageUtil {
    private String message;

    //Constructor
    //@param message to be printed
    public MessageUtil(String message) {
        this.message = message;
    }

    // prints the message
    public String printMessage() {
        System.out.println(message);
        return message;
    }
}
