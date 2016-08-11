package science.freeabyss.hulk.demo.spring.springido1;

/**
 * Created by abyss on 3/25/16.
 */
public class Sonnet29 implements Poem {

    private static String[] LINES = {
            "When in disgrace with fortune and men's eyes,",
            "I all alone beweep my outcast state",
            "And trouble deaf heaven with my bootless cries",
            "And look upon myself and curse my fate,",
            "Wishing me like to one more rich in hope",
            "Featured like him, like him with friends possess'd",
            "Desiring this man's art and that man's scope",
            "With what I most enjoy contented least",
            "Yet in these thoughts myself almost despising",
            "Haply I think on thee, and then my state,",
            "Like to the lark at break of day arising,",
            "From sullen earth, sings hymns at heaven's gate;",
            "That then I sorn to change my state with kings."
    };

    @Override
    public void recite() {
        for (int i = 0; i < LINES.length; i++) {
            System.out.println(LINES[i]);
        }
    }
}
