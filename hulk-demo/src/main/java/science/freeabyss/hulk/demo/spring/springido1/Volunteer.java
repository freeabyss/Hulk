package science.freeabyss.hulk.demo.spring.springido1;

/**
 * Created by abyss on 3/27/16.
 */
public class Volunteer implements Thinker {
    private String thoughts;

    @Override
    public void thinkOfSomething(String thoughts) {
        this.thoughts = thoughts;
    }

    public String getThoughts() {
        return thoughts;
    }
}
