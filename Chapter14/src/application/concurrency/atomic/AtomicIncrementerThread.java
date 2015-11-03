package application.concurrency.atomic;

public class AtomicIncrementerThread extends Thread {

    private AtomicCounter atomicCounter;

    // all instances are passed the same counter
    public AtomicIncrementerThread(AtomicCounter atomicCounter) {
        this.atomicCounter = atomicCounter;
    }

    public void run() {
        // "i" is local and thread-safe
        for (int i = 0; i < 10000; i++) {
            atomicCounter.increment();
        }
    }
}
