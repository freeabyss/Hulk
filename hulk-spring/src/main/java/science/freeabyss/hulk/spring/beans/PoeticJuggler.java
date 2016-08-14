package science.freeabyss.hulk.spring.beans;

/**
 * Created by abyss on 08/13/16.
 */
public class PoeticJuggler extends Juggler {
    private Poem poem;

    // 注入Poem
    public PoeticJuggler(Poem poem) {
        super();
        this.poem = poem;
    }

    // 注入豆袋子数量和Poem
    public PoeticJuggler(int beanBags, Poem poem) {
        super(beanBags);
        this.poem = poem;
    }

    @Override
    public void perform() {
        super.perform();
        System.out.println("While reciting...");
        poem.recite();
    }
}
