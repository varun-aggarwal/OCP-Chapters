package application.concurrency.lock.readwrite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import application.concurrency.lock.readwrite.domain.Player;

public class LeaderBoard {

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    private List<Player> highScores = new ArrayList<>();

    public void addScore(Player currentPlayer) {
        Lock lock = rwl.writeLock();
        lock.lock();
        try {
            int currentHighScore = getCurrentHighScore();
            if (highScores.size() < 3) {
                highScores.add(currentPlayer);
            } else if (currentHighScore < currentPlayer.getScore()) {
                highScores.set(highScores.size() - 1, currentPlayer);
            } else {
                return;
            }
            Collections.sort(highScores, Collections.reverseOrder());
        } finally {
            lock.unlock();
        }
    }

    private int getCurrentHighScore() {
        if (highScores.isEmpty()) {
            return 0;
        }
        Player player = highScores.get(highScores.size() - 1);
        return player.getScore();
    }

    public List<Player> getHighScores() {
        Lock lock = rwl.readLock();
        lock.lock();
        try {
            return Collections.unmodifiableList(highScores);
        } finally {
            lock.unlock();
        }
    }
}
