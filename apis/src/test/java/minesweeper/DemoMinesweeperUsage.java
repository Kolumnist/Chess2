package minesweeper;

import de.hhn.it.devtools.apis.minesweeper.Difficulty;
import de.hhn.it.devtools.apis.minesweeper.MinesweeperActionListener;

/**
 * The Handler interface is for handling interactions between a player with a component
 * @author Lara Weller, Jason Herrmann
 * @version 0.1
 */

import de.hhn.it.devtools.apis.minesweeper.MinesweeperService;
    
public class DemoMinesweeperUsage implements MinesweeperService{

    private MinesweeperActionListener minesweeperActionListener;
       
    public static void main(String[]args){        
        DemoMinesweeperUsage demo = new DemoMinesweeperUsage();
        
        demo.setMinesweeperActionListener(
            new MinesweeperActionListener(){                         
                public void onAction(String event){                    
                    System.out.println("Starting the long running task.");
                }
        });
        
        // User chooses difficulty easy
        demo.chooseDifficulty(Difficulty.EASY);
        System.out.println("Changing diffictulty to easy");

        // Player decides to adjust some settings manually, 
        // then starts a new game with fieldsize 10, 600 seconds and 20 mines
        demo.startGame(10, 600, 20);
        System.out.println("Staring Game");

        // Player clicks on fields
        demo.clickField();
        System.out.println("Field Opened");
        System.out.println("No mine detected");
        demo.clickField();
        System.out.println("Field Opened");
        System.out.println("No mine detected");
        demo.clickField();
        System.out.println("Field Opened");
        System.out.println("No mine detected");
        demo.clickField();
        System.out.println("Field Opened");
        System.out.println("No mine detected");
        demo.clickField();
        System.out.println("Field Opened");
        System.out.println("No mine detected");
        demo.clickField();
        System.out.println("Field Opened");
        System.out.println("No mine detected");

        // Player marks field
        demo.markField();
        System.out.println("marked field");

        // Player goes to the menu
        demo.menu();
        System.out.println("Going back to the menu");

        demo.minesweeperActionListener.onAction("Hello");
    }

    @Override
    public void startGame(int fieldSize, int time, int mineCount) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void menu() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void restart() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void clickField() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void markField() {
        // TODO Auto-generated method stub
        
    }

	@Override
	public void chooseDifficulty(Difficulty difficulty) {
		// TODO Auto-generated method stub
		
	}
    @Override
    public void setMinesweeperActionListener(MinesweeperActionListener minesweeperActionListener) {
        // TODO Auto-generated method stub
        this.minesweeperActionListener = minesweeperActionListener;
    }
}