package de.hhn.it.devtools.apis.textbasedlabyrinth;

public interface GameService {

    void startGame();

    void moveSouth();

    void moveNorth();

    void moveWest();

    void moveEast();

    void interaction();

    void searchRoom();
}
