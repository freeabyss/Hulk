package science.freeabyss.hulk.spring.beans;

/**
 * Created by abyss on 08/13/16.
 */
public class Stage {
    private Stage() {
    }

    private static class StageSingletonHolder {
        private static Stage instance = new Stage();
    }

    public static Stage getInstance() {
        return StageSingletonHolder.instance;
    }

    public void doThing() {
        System.out.println("Stage");
    }
}
