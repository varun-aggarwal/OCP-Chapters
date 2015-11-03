package application.concurrency.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {

    private AtomicInteger count = new AtomicInteger();

    public void increment() {
        count.getAndIncrement();
    }

    public int getValue() {
        return count.get();
    }
}
