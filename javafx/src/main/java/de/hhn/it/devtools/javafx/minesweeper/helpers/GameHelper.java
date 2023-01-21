package de.hhn.it.devtools.javafx.minesweeper.helpers;

import de.hhn.it.devtools.javafx.battleship.Game;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class GameHelper {
    private static int size = 5;
    private static int time = 300;
    private static int mines = 5;
    private static Timer timer;
    private static TimerTask task;
    private static int timeSpent = 0;
    private static int timeInTimer = 0;
    private static String color;
    private  static Button[][] matrix;

    public static Button[][] getMatrix() {
        return matrix;
    }

    private static Menu menu;

    public static void setMenu(Menu menu) {
        GameHelper.menu = menu;
    }
    public  static  void setMatrix(Button[][] matrix){GameHelper.matrix = matrix;}

    public static  void setTimer(Timer timer, TimerTask task){
        GameHelper.timeSpent = 0;
        GameHelper.timeInTimer = 0;
        GameHelper.timer = timer;
        GameHelper.task = task;
    }


    public static void setLevelValues(int size, int time, int mines){
        GameHelper.size = size;
        GameHelper.time = time;
        GameHelper.mines = mines;
    }

    public static void setTime(int time){
        GameHelper.time = time;
    }

    public static  void setTimeSpent(int timeSpent){
        GameHelper.timeSpent = timeSpent;
    }

    public  static  void setColor(String color){ GameHelper.color = color; }

    public static int getSize() {
        return size;
    }

    public static int getTime() {
        return time;
    }

    public static int getMines() {
        return mines;
    }

    public static Timer getTimer() {
        return timer;
    }

    public static TimerTask getTimerTask() {
        return task;
    }

    public static  int getTimeSpent(){
        return  timeSpent;
    }

    public static String getColor(){ return  color;}

    public static void setupTimer() {
        GameHelper.stopTimer();
        GameHelper.timeSpent = 0;
        //Init Timer and time
       Timer myTimer = new Timer();
        GameHelper.timeInTimer = 0;
       //Create TimerTask
       TimerTask task = new TimerTask() {
                //New Thread
                @Override
                public void run() {
                    //Handle Inifity Time
                    if (GameHelper.getTime() == -99){
                        menu.setText("Infinity");
                        return;
                    }
                    //Increment time
                    timeInTimer++;
                    //Wait till able to make changes
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //Set Text
                            if (menu != null) {
                                menu.setText(String.format("%s seconds left",GameHelper.getTime() - timeInTimer));
                            }
                            GameHelper.setTimeSpent(GameHelper.timeInTimer);
                            //Check if Time ran out
                            if(GameHelper.timeInTimer >= GameHelper.getTime()){
                                try {
                                    GameHelper.stopTimer();
                                    ViewSwitchHelper.switchTo("minesweeper/LosePopUp");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    });
                }
            };
            GameHelper.setTimer(myTimer, task);
        }

        public static void startTimer() {
            GameHelper.timer.scheduleAtFixedRate(GameHelper.task, 0,1000);
        }

    public static void stopTimer() {
        if ( GameHelper.timer != null ) {
            GameHelper.timer.cancel();
        }
    }
}
