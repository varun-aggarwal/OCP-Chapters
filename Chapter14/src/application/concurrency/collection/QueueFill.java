package application.concurrency.collection;

import java.util.concurrent.BlockingQueue;

public class QueueFill implements Runnable {

    BlockingQueue<Integer> bq;

    public QueueFill(BlockingQueue<Integer> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        try {
            bq.put((int) (Math.random() * 1000));
            System.out.println("Element in BlockingQueue has been added.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
