package components.minesweeper.provider;

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

    public  LJMinesweeperService(int fieldSize, int time, int bombCount){
        this.fieldSize = fieldSize;
        this.bombCount = bombCount;
        this.time = time;
        startGame();
    }

    @Override
    public void startGame() {
        handler = new Handler(fieldSize, bombCount);
    }

    @Override
    public void restart() {
        startGame();
    }

    @Override
    public Status clickField(MinesweeperCoordinates koordinaten) {
        return handler.clickField(koordinaten);
    }

    @Override
    public Status markField(MinesweeperCoordinates koordinaten) {
        return handler.markField(koordinaten);
    }

    public Handler getHandler() {
        return handler;
    }
}