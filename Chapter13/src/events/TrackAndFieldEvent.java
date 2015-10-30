package events;

import java.util.List;
import java.util.Vector;

import domain.Player;

public class TrackAndFieldEvent implements Runnable {

    private List<Player> playerList = new Vector<Player>();

    private String name;

    public TrackAndFieldEvent(String name) {
        this.name = name;
    }

    public void addPlayer(Player player) {
        synchronized (playerList) {
            playerList.add(player);
            playerList.notify();
        }
    }

    /**
     * @return the playerList
     */
    public List<Player> getPlayerList() {
        return playerList;
    }

    /**
     * @param playerList the playerList to set
     */
    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public void run() {
        synchronized (playerList) {
            while (playerList.size() < 5) {
                try {
                    playerList.wait();
                } catch (InterruptedException ie) {
                }
            }
            System.out.println("TrackAndFieldEvent : " + name + " has been started.");
            for (Player player : playerList) {
                Thread athlete = new Thread(player);
                athlete.start();
            }
            playerList.clear();
            System.out.println("====================================================");
            System.out.println("TrackAndFieldEvent : " + name + " has been completed.");
        }
    }

}
