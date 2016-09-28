package science.freeabyss.hulk.basic.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 同一流中，两对象实例共享的实例，反序列化后具有相同的地址。
 * Created by abyss on 09/28/16.
 */
class  House implements Serializable {
}
class Animal implements Serializable {
    private String name;
    private House preferredHouse;

    Animal(String nm, House house) {
        name = nm;
        preferredHouse = house;
    }

    @Override
    public String toString() {
        return name + "[" + super.toString() + "], " + preferredHouse + "\n";
    }
}
public class MyWorld {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        House house = new House();
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Bosco the dog", house));
        animals.add(new Animal("Ralph the hamster", house));
        animals.add(new Animal("Molly the cat", house));
        List<Animal> banimals = new ArrayList<>();
        banimals.add(animals.get(0));
        System.out.println("animals :" + animals);

        ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(buf1);
        out.writeObject(animals);
        out.writeObject(animals);
        out.writeObject(banimals);

        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        ObjectOutputStream out1 = new ObjectOutputStream(buf2);
        out1.writeObject(animals);

        ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()));

        List<Animal> animals1 = (List<Animal>) in1.readObject(),
                animals2 = (List<Animal>) in1.readObject(),
                animals3 = (List<Animal>) in2.readObject(),
                animals4 = (List<Animal>) in1.readObject();
        System.out.println("animals1 : " + animals1);
        System.out.println("animals2 : " + animals2);
        System.out.println("animals4 : " + animals4);
        System.out.println("animals3 : " + animals3);

    }
}
