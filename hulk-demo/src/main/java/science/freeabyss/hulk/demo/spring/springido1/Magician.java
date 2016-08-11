package science.freeabyss.hulk.demo.spring.springido1;

/**
 * Created by abyss on 3/27/16.
 */
public class Magician implements MindReader {
    private String thoughts;

    @Override
    public void interceptThoughts(String thoughts) {
        System.out.printf("Intercepting volunteer's thoughts");
        this.thoughts = thoughts;
    }

    @Override
    public String getThoughts() {
        return thoughts;
    }
}
