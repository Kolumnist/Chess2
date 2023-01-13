package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;

public class Treasure extends Item {

    private int scorePoint;

    public Treasure(int id, String name, String info){
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
