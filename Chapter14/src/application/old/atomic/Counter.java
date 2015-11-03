package application.old.atomic;

public class Counter {

    private int count;

    public void increment() {
        count++;
    }

    public int getValue() {
        return count;
    }
}
