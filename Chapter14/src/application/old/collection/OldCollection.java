package application.old.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OldCollection {

    public static void demo() {
        List<Integer> cowList = new ArrayList<Integer>();
        cowList.add(4);
        cowList.add(2);
        Iterator<Integer> it = cowList.iterator();
        cowList.add(6);
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
    }
}
