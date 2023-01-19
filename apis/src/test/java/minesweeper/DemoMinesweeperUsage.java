package minesweeper;

import de.hhn.it.devtools.apis.minesweeper.*;

/**
 * The Handler interface is for handling interactions between a player with a component
 * @author Lara Weller, Jason Herrmann
 * @version 0.1
 */

public class DemoMinesweeperUsage implements MinesweeperService{

    private MinesweeperActionListener minesweeperActionListener;
       
    public static void main(String[]args){        
        DemoMinesweeperUsage demo = new DemoMinesweeperUsage();
        
        // User chooses difficulty easy
        System.out.println("Changing diffictulty to easy");

        // Player decides to adjust some settings manually, 
        // then starts a new game with fieldsize 10, 600 seconds and 20 mines
        demo.startGame(10, 600, 20);
        System.out.println("Staring Game");

        // Player clicks on fields
        demo.clickField(new MinesweeperCoordinates(1,2));
        System.out.println("Field Opened");
        System.out.println("No mine detected");
        demo.clickField(new MinesweeperCoordinates(2,2));
        System.out.println("Field Opened");
        System.out.println("No mine detected");
        demo.clickField(new MinesweeperCoordinates(3,2));
        System.out.println("Field Opened");
        System.out.println("No mine detected");
        demo.clickField(new MinesweeperCoordinates(4,2));
        System.out.println("Field Opened");
        System.out.println("No mine detected");
        demo.clickField(new MinesweeperCoordinates(1,3));
        System.out.println("Field Opened");
        System.out.println("No mine detected");
        demo.clickField(new MinesweeperCoordinates(1,4));
        System.out.println("Field Opened");
        System.out.println("No mine detected");

        // Player marks field
        demo.markField(new MinesweeperCoordinates(0,0));
        System.out.println("marked field");

        // Player goes to the menu
        System.out.println("Going back to the menu");

        demo.minesweeperActionListener.onAction("Hello");
    }

    @Override
    public void startGame(int fieldSize, int time, int bombCount) {

    }

    @Override
    public void restart() {

    }

    @Override
    public Status clickField(MinesweeperCoordinates coords) {
        return null;
    }

    @Override
    public Status markField(MinesweeperCoordinates coords) {
        return null;
    }
}