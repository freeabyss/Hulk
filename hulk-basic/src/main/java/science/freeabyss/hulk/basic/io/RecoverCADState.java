package science.freeabyss.hulk.basic.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by abyss on 09/28/16.
 */
public class RecoverCADState {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("hulk-out/CADState.out"));
        List<Class<? extends Shape>> shapeTypes1 = (List<Class<? extends Shape>>) in.readObject();
        Line.deserializeStaticState(in);
        List<Shape> shapes1 = (List<Shape>) in.readObject();

        System.out.println(shapes1);
    }
}
