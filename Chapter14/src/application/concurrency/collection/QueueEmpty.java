package application.concurrency.collection;

import java.util.concurrent.BlockingQueue;

public class QueueEmpty implements Runnable {

    BlockingQueue<Integer> bq;

    public QueueEmpty(BlockingQueue<Integer> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        while (!bq.isEmpty()) {
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bq.remove();
        }
        System.out.println("Element in BlockingQueue has been removed.");
    }
}
