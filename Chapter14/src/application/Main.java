package application;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ForkJoinPool;

import application.concurrency.atomic.AtomicCounter;
import application.concurrency.atomic.AtomicIncrementerThread;
import application.concurrency.collection.Collection;
import application.concurrency.collection.QueueEmpty;
import application.concurrency.collection.QueueFill;
import application.concurrency.forkjoin.RandomInitRecursiveAction;
import application.concurrency.lock.readwrite.Game;
import application.concurrency.lock.readwrite.domain.Player;
import application.old.atomic.Counter;
import application.old.atomic.IncrementerThread;
import application.old.collection.OldCollection;

public class Main {

    public static void main(String... args) throws InterruptedException {
        Main application = new Main();
        application.demoOldIncrementExample();
        application.demoAtomicIncrementExample();
        application.demoReadWriteLock();
        application.demoCollection();
        application.demoBlockingQueue();
        application.demoExecutersAndCallable();
        application.demoOldWayForFillingDataArray();
        application.demoForkJoin();
    }

    private void demoExecutersAndCallable() {
        System.out.println("==========Callable and Executor example=======");
        Game game = new Game();
        game.playUsingExecutorAndCallable();
        printLeaderBoard();
        System.out.println("==============================================");
        System.out.println("");

    }

    private void demoBlockingQueue() throws InterruptedException {
        BlockingQueue<Integer> bq = new ArrayBlockingQueue<Integer>(1);
        bq.put((int) (Math.random() * 1000));
        Thread queueFill = new Thread(new QueueFill(bq));
        Thread queueEmpty = new Thread(new QueueEmpty(bq));
        queueFill.start();
        queueEmpty.start();
        queueFill.join();
        queueEmpty.join();
    }

    private void demoCollection() {
        try {
            System.out.println("==========Old way of using Arraylist==========");
            OldCollection.demo();
            System.out.println("==============================================");
            System.out.println("");
        } catch (Exception e) {
            System.out.println("Error message is " + e);
        }
        System.out.println("==========Concurrent ==== Arraylist==========");
        Collection.demo();
        System.out.println("==============================================");
        System.out.println("");

    }

    private void demoReadWriteLock() {
        Game game = new Game();
        game.play();
        printLeaderBoard();
        game.printPlayerScore();

    }

    private void demoOldIncrementExample() throws InterruptedException {
        System.out.println("==========Old way of increment ===============");
        Counter counter = new Counter();
        IncrementerThread it1 = new IncrementerThread(counter);
        IncrementerThread it2 = new IncrementerThread(counter);
        it1.start();
        it2.start();
        it1.join();
        it2.join();
        System.out.println(counter.getValue());

        System.out.println("==============================================");
        System.out.println("");
    }

    private void demoAtomicIncrementExample() throws InterruptedException {
        System.out.println("==========Atomic way of increment ===============");
        AtomicCounter atomicCounter = new AtomicCounter();
        AtomicIncrementerThread it1 = new AtomicIncrementerThread(atomicCounter);
        AtomicIncrementerThread it2 = new AtomicIncrementerThread(atomicCounter);
        it1.start();
        it2.start();
        it1.join();
        it2.join();
        System.out.println(atomicCounter.getValue());

        System.out.println("=================================================");
        System.out.println("");

    }

    private void printLeaderBoard() {
        System.out.println("==========Top players of game====================");
        List<Player> playerList = Game.leaderBoard.getHighScores();
        for (Player player : playerList) {
            System.out.println(player.getName() + "\t" + player.getScore());
        }
    }

    private void demoOldWayForFillingDataArray() {
        System.out.println("==========Sequentional Example================");
        int[] data = new int[10_000_000];
        Long startTime = System.currentTimeMillis();
        for (int i = 0; i < data.length; i++) {
            data[i] = (int) (Math.random() * 5);
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("Time Taken = " + (endTime - startTime));
        System.out.println("=================================================");
        System.out.println("");

    }

    private void demoForkJoin() {
        System.out.println("==========Fork/Join Example===================");
        int[] data = new int[10_000_000];
        Long startTime = System.currentTimeMillis();
        ForkJoinPool fjPool = new ForkJoinPool();
        RandomInitRecursiveAction action = new RandomInitRecursiveAction(data, 0, data.length);
        fjPool.invoke(action);
        Long endTime = System.currentTimeMillis();
        System.out.println("Time Taken = " + (endTime - startTime));
        System.out.println("=================================================");
        System.out.println("");

    }
}
