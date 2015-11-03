package application.concurrency.collection;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Collection {

    public static void demo() {
        CopyOnWriteArrayList<Integer> cowList = new CopyOnWriteArrayList<>();
        cowList.add(4);
        cowList.add(2);
        Iterator<Integer> it = cowList.iterator();
        cowList.add(6);
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println("");
    }
}
