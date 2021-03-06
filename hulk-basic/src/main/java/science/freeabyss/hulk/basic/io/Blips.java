package science.freeabyss.hulk.basic.io;

import java.io.*;

/**
 * Externalizable 的使用
 * Created by abyss on 09/27/16.
 */
class Blip1 implements Externalizable {
    public Blip1() {
        System.out.println("Blip1 Constructor");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip1.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip1.readExternal");
    }
}

class Blip2 implements Externalizable {
    public Blip2() {
        System.out.println("Blip2 Constructor");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip2.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip2.readExternal");
    }
}

public class Blips {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Constructing objects");
        Blip1 b1 = new Blip1();
        Blip2 b2 = new Blip2();

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("hulk-out/Blips.out"));
        System.out.println("Saving objects:");
        out.writeObject(b1);
        out.writeObject(b2);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("hulk-out/Blips.out"));
        System.out.println("Recovering b1:");
        b1 = (Blip1) in.readObject();
        System.out.println("Recovering b2:");
        b2 = (Blip2) in.readObject();
    }
}
