package science.freeabyss.hulk.basic.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 注解处理器
 * Created by abyss on 3/22/16.
 */
public class UseCaseTracker {

public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
    for (Method m : cl.getDeclaredMethods()) {
        UseCase uc = m.getAnnotation(UseCase.class);
        if (uc != null) {
            System.out.println("Found Use Case:" + uc.id() + " " + uc.description());
            useCases.remove(new Integer(uc.id()));
        }
        System.out.println(m.getName());
    }
    for (int i : useCases) {
        System.out.println("Warning :Missing use case-" + i);
    }
}

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        trackUseCases(useCases, PasswordUtils.class);
    }
}
