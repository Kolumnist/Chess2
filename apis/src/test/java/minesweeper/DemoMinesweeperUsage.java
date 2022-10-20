package minesweeper;

import java.beans.DesignMode;

import de.hhn.it.devtools.apis.minesweeper.*;

public class DemoMinesweeperUsage implements Handler{

    public static void main(String[]args){

        DemoMinesweeperUsage demo= new DemoMinesweeperUsage();

        // User chooses difficulty easy
        demo.chooseDifficulty('e');

        // Player starts a new game with fieldsize 10, 600 seconds and 20 mines
        demo.startGame(10, 600, 20);

        // Player opens color menu to choose a different color
        demo.changeColor();

        // Player selects his favorit color
        demo.choosenColor(Color.WHITE);

        // Player clicks on fields
        demo.clickField();
        demo.clickField();
        demo.clickField();
        demo.clickField();
        demo.clickField();
        demo.clickField();

        // Player marks field
        demo.markField();

        // Player goes to the menu
        demo.menu();

    }

    @Override
    public void startGame(int fieldSize, int time, int mineCount) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void chooseDifficulty(char difficulty) {
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
    public void changeColor() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void choosenColor(Color c) {
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


}
