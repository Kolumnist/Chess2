package de.hhn.it.devtools.components.minesweeper.provider;

import de.hhn.it.devtools.apis.minesweeper.*;
import de.hhn.it.devtools.apis.minesweeper.MinesweeperCoordinates;

/**
 *
 */
public class LJMinesweeperService implements MinesweeperService
{

    private int fieldSize;
    private  int bombCount;
    private int time;
    Handler handler;

    public  LJMinesweeperService(){}

    public  LJMinesweeperService(int fieldSize, int time, int bombCount){
        this.fieldSize = fieldSize;
        this.bombCount = bombCount;
        this.time = time;
        startGame(fieldSize, time, bombCount);
    }

    @Override
    public void startGame(int fieldSize, int time, int bombCount) {
        handler = new Handler(fieldSize, bombCount);
    }

    @Override
    public void restart() {
        startGame(fieldSize,time,bombCount);
    }

    @Override
    public Status clickField(MinesweeperCoordinates coords) {
        return handler.clickField(coords);
    }

    @Override
    public Status markField(MinesweeperCoordinates coords) {
        return handler.markField(coords);
    }

    public Handler getHandler() {
        return handler;
    }
}