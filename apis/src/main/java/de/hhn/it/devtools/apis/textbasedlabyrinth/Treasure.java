package de.hhn.it.devtools.apis.textbasedlabyrinth;

/**
 * Class for Treasure items. Treasures will award the player points and have no other use.
 */
public class Treasure extends Item {

    private int scorePoint;

    /**
     * Constructor of Treasure.
     *
     * @param id id of treasure.
     * @param name name of treasure.
     * @param info info of treasure.
     */
    public Treasure(int id, String name, String info) {
        super(id, name, info);
        this.scorePoint = 1;
        super.isTreasure = true;
    }

    public void setScorePoint(int scorePoint) {
        this.scorePoint = scorePoint;
    }

    public int getScorePoint() {
        return scorePoint;
    }
}
