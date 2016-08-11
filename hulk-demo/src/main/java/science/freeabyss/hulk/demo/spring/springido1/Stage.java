package science.freeabyss.hulk.demo.spring.springido1;

/**
 * Created by abyss on 3/25/16.
 */
public class Stage  {

    private Stage() {
    }

    private static class StageSingletonHolder{
        static Stage instance = new Stage();
    }
    public static Stage getInstance(){
        return StageSingletonHolder.instance;
    }
}
