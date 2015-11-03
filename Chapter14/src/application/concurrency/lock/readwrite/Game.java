package application.concurrency.lock.readwrite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import application.concurrency.callable.CallablePlayGame;
import application.concurrency.lock.readwrite.domain.Player;

public class Game {

    public static LeaderBoard leaderBoard = new LeaderBoard();

    List<Player> playerList = new ArrayList<Player>();

    public Game() {
        dummyPlayerList();
    }

    private void dummyPlayerList() {
        playerList.clear();
        Player howard = new Player("Howard", 200);
        playerList.add(howard);
        Player martijn = new Player("Martijn", 200);
        playerList.add(martijn);
        Player arno = new Player("Arno", 200);
        playerList.add(arno);
        Player xiaochen = new Player("Xiaochen", 200);
        playerList.add(xiaochen);
        Player varun = new Player("Varun", 200);
        playerList.add(varun);
        Player amin = new Player("Amin", 200);
        playerList.add(amin);
    }

    public void play() {
        for (Player player : playerList) {
            Thread playerGame = new PlayGame(player);
            playerGame.start();
            try {
                playerGame.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void printPlayerScore() {
        System.out.println();
        System.out.println("=================================================");
        System.out.println("List of all Players and their Score");
        for (Player player : playerList) {
            System.out.println(player.getName() + "\t" + player.getScore());
        }
        System.out.println("=================================================");
        System.out.println("");
    }

    public void playUsingExecutorAndCallable() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (Player player : playerList) {
            Callable<Integer> playerGame = new CallablePlayGame(player);
            Future<Integer> result = executorService.submit(playerGame);
            try {
                Integer resultScore = result.get();
                System.out.println("Player scored " + resultScore);

            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Failed");
            }
        }
    }
}
