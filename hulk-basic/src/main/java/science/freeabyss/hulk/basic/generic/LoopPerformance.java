package science.freeabyss.hulk.basic.generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 测试for、foreach、iterator 性能
 * Created by abyss on 08/14/16.
 */
public class LoopPerformance {
    public static void main(String[] args) {
        int m = 10000;
        int n = 100;
        double forTime = total(m, n, new forLoop());
        double foreachTime = total(m, n, new foreachLoop());
        double iteratorTime = total(m, n, new iteratorLoop());
        double streamTime = total(m, n, new streamLoop());

        System.out.println("for :" + forTime);
        System.out.println("foreach :" + foreachTime);
        System.out.println("iterator :" + iteratorTime);
        System.out.println("stream :" + streamTime);
    }


    public static double total(int m, int n, Loop loop) {
        List<String> list = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            list.add("Random" + i);
        }
        double sum = 0;

        for (int i = 0; i < n; i++) {
            long start = System.currentTimeMillis();
            loop.loop(list);
            long end = System.currentTimeMillis();
            sum += (end - start);
        }
        return sum / n;
    }

    private interface Loop {
        void loop(List<String> loop);

    }

    private static class forLoop implements Loop {

        @Override
        public void loop(List<String> loop) {
            for (int i = 0, len = loop.size(); i < len; i++) {
                System.out.println(loop.get(i));
            }
        }
    }

    private static class foreachLoop implements Loop {
        @Override
        public void loop(List<String> loop) {
            for (String s : loop) {
                System.out.println(s);
            }
        }
    }

    private static class iteratorLoop implements Loop {

        @Override
        public void loop(List<String> loop) {
            Iterator<String> iterator = loop.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }

    private static class streamLoop implements Loop {
        @Override
        public void loop(List<String> loop) {
            loop.forEach(x -> System.out.println(x));
        }
    }
}
