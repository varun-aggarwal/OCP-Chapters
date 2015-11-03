package application.concurrency.lock.readwrite;

import application.concurrency.lock.readwrite.domain.Player;

public class PlayGame extends Thread {

    private Player player;

    // all instances are passed the same counter
    public PlayGame(Player player) {
        this.player = player;
    }

    public void run() {
        player.makeNewScore();
        Game.leaderBoard.addScore(player);
    }

}
