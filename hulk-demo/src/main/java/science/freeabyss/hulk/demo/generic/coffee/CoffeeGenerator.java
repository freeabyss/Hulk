package science.freeabyss.hulk.demo.generic.coffee;

import science.freeabyss.hulk.demo.generic.Generator;

import java.util.Iterator;

/**
 * Created by abyss on 3/24/16.
 */
public class CoffeeGenerator implements Generator<Coffee>, Iterator<Coffee> {

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Coffee next() {
        return null;
    }

    @Override
    public void remove() {

    }
}