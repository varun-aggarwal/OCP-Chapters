package application.concurrency.lock.readwrite.domain;

public class Player implements Comparable<Player> {

    private String name;

    private int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
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

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    public void makeNewScore() {
        this.score += (Math.random() * 1000);
    }

    @Override
    public int compareTo(Player newPlayer) {
        return Integer.compare(this.score, newPlayer.score);
    }

}
