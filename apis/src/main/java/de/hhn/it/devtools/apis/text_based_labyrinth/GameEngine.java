package de.hhn.it.devtools.apis.text_based_labyrinth;

public class GameEngine {


    private final Player player;
    private final Scenario scenario;
    private boolean isRunning;  //The game is running currently.
    private boolean isPrepared;  //Is prepared for a game.


    public GameEngine(Player player, Scenario scenario) {
        this.player = player;
        this.scenario = scenario;


    }


    public void runGame(){
        //Checks if the game is already running and if it is prepared.



        //With a while-loop, receive commands from the interface into the loop and act accordingly.
    }

}
