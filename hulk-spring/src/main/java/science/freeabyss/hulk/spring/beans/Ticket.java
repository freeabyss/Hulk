package science.freeabyss.hulk.spring.beans;

/**
 * Created by abyss on 08/13/16.
 */
public class Ticket {
    static int count = 0;
    int number = 0;

    public Ticket() {
        number = count++;
    }

    @Override
    public String toString() {
        return "Ticket number :" + number;
    }
}
