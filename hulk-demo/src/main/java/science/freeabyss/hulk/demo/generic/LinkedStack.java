package science.freeabyss.hulk.demo.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abyss on 3/24/16.
 */
public class LinkedStack<T> {
    private class Node {
        T item;
        Node next;

        Node() {
            item = null;
            next = null;
        }

        Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }

        boolean end() {
            return item == null && next == null;
        }
    }

    private Node top = new Node();

    public void push(T item) {
        top = new Node(item, top);
    }

    public T pop() {
        T result = top.item;
        if (!top.end()) {
            top = top.next;
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("ssss");
        list.add("6565");
        LinkedStack<String> lists = new LinkedStack<>();
        System.out.println(list.hashCode());
        System.out.println(lists.hashCode());
        System.out.println("sss".hashCode());

    }
}
