package domain;

import java.util.List;

import events.TrackAndFieldEvent;

public class Refree extends Thread {

    TrackAndFieldEvent trackAndFieldEvent;

    String[] playerNames = {"Varun", "Howard", "Xiaochen", "Martijn", "Amin"};

    /**
     * @return the trackAndFieldEvent
     */
    public TrackAndFieldEvent getTrackAndFieldEvent() {
        return trackAndFieldEvent;
    }

    /**
     * @param trackAndFieldEvent the trackAndFieldEvent to set
     */
    public void addEvent(TrackAndFieldEvent trackAndFieldEvent) {
        this.trackAndFieldEvent = trackAndFieldEvent;
    }

    public void run() {
        List<Player> playerList = trackAndFieldEvent.getPlayerList();
        int index = (int) (Math.random() * 4);
        while (playerList.size() < 5) {
            Player player = new Player(playerNames[index]);
            player.setEventName(trackAndFieldEvent.getName());
            trackAndFieldEvent.addPlayer(player);
        }
        Thread event = new Thread(trackAndFieldEvent);
        event.start();
    }

}
