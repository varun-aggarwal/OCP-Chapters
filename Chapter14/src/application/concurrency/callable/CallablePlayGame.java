package application.concurrency.callable;

import java.util.concurrent.Callable;

import application.concurrency.lock.readwrite.Game;
import application.concurrency.lock.readwrite.domain.Player;

public class CallablePlayGame implements Callable<Integer> {

    private Player player;

    public CallablePlayGame(Player player) {
        this.player = player;
    }

    public void Call() {
        player.makeNewScore();
        Game.leaderBoard.addScore(player);
    }

    @Override
    public Integer call() throws Exception {
        player.makeNewScore();
        Game.leaderBoard.addScore(player);
        return player.getScore();
    }

}
