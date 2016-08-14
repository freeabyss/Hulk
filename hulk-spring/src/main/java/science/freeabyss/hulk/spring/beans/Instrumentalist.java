package science.freeabyss.hulk.spring.beans;

/**
 * Created by abyss on 08/13/16.
 */
public class Instrumentalist implements Performer {
    private String song;
    private Poem poem;

    @Override

    public void perform() {
        System.out.println("Playing " + song + " : ");
        poem.recite();
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public Poem getPoem() {
        return poem;
    }

    public void setPoem(Poem poem) {
        this.poem = poem;
    }
}
