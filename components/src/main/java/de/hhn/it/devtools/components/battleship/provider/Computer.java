package de.hhn.it.devtools.components.battleship.provider;

import de.hhn.it.devtools.apis.battleship.*;

import java.util.Random;
import java.util.Map;

public class Computer extends Player {

    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(Computer.class);

    Random random = new Random();


    private void placeShipSmall(int num) throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        int x = random.nextInt(5);
        int y = random.nextInt(5);
        if(random.nextInt(2) == 1){
            CmpBattleshipService.service.rotateShip(this, getOwnedShips().get(num));
        }

        try {
            CmpBattleshipService.service.placeShip(this, getOwnedShips().get(num), x, y);
        } catch (IllegalPositionException e){
            placeShipSmall(num);
        }
    }

    private void placeShipMediumLarge(int num, int size) throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        int x = random.nextInt(5);
        Position position;
        if(x == 0){
            position = placeTop(size);
        }
        else if(x == 1){
            position = placeLeft(size);
        }
        else if(x == 2){
            position = placeBottom(size);
        }
        else if(x == 3){
            position = placeRight(size);
        }
        else {
            position = placeMiddle(size);
        }

        if(random.nextInt(2) == 1){
            CmpBattleshipService.service.rotateShip(this, getOwnedShips().get(num));
        }

        try {
            CmpBattleshipService.service.placeShip(this, getOwnedShips().get(num), position.getX(), position.getY());
        } catch (IllegalPositionException e){
            placeShipMediumLarge(num, size);
        }
    }

    public void comShipPlacement(int size) throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {

        for(int num = 0; num < getOwnedShips().size(); num++){
            if(size == 5){
                placeShipSmall(num);
            }
            else if(size == 10 || size == 15){
                placeShipMediumLarge(num, size);
            }
        }
    }

    public Position placeTop(int size){
        int x = random.nextInt(size);
        int y = random.nextInt(size/3);
        return new Position(x, y);
    }

    public Position placeBottom(int size){
        int x = random.nextInt(size);
        int y = random.nextInt(size - size/3, size);
        return new Position(x, y);
    }

    public Position placeLeft(int size){
        int x = random.nextInt(size/3);
        int y = random.nextInt(size);
        return new Position(x, y);
    }

    public Position placeRight(int size){
        int x = random.nextInt(size - size/3);
        int y = random.nextInt(size);
        return new Position(x, y);
    }

    public Position placeMiddle(int size){
        int x = random.nextInt(size/3, size - size/3);
        int y = random.nextInt(size/3, size - size/3);
        return new Position(x, y);
    }
/*
    public boolean checkSurroundings(Position position, boolean isVertical, int shipSize, int fieldSize){
        int x = position.getX();
        int y = position.getY();
        if(isVertical){
            if(y > 0){
                if (this.getShipField().getPanelMarker(x, y - 1) == PanelState.SHIP){
                    return true;
                }
            }

            if(x == 0){
                for(int i = y; i < y + shipSize; i++){
                    if (this.getShipField().getPanelMarker(x + 1, i) == PanelState.SHIP){
                        return true;
                    }
                }
            } else if(x == fieldSize - 1){
                for(int i = y; i < y + shipSize; i++){
                    if (this.getShipField().getPanelMarker(x - 1, i) == PanelState.SHIP){
                        return true;
                    }
                }
            } else {
                for(int i = y; i < y + shipSize; i++){
                    if (this.getShipField().getPanelMarker(x + 1, i) == PanelState.SHIP){
                        return true;
                    }
                    if (this.getShipField().getPanelMarker(x - 1, i) == PanelState.SHIP){
                        return true;
                    }
                }
            }

            if(y + shipSize - 1 < fieldSize - 1){
                if (this.getShipField().getPanelMarker(x, y + shipSize) == PanelState.SHIP){
                    return true;
                }
            }
        } else {
            if(x > 0){
                if (this.getShipField().getPanelMarker(x - 1, y) == PanelState.SHIP){
                    return true;
                }
            }

            if(y == 0){
                for(int i = x; i < x + shipSize; i++){
                    if (this.getShipField().getPanelMarker(i, y + 1) == PanelState.SHIP){
                        return true;
                    }
                }
            } else if(y == fieldSize - 1){
                for(int i = x; i < x + shipSize; i++){
                    if (this.getShipField().getPanelMarker(i, y - 1) == PanelState.SHIP){
                        return true;
                    }
                }
            } else {
                for(int i = x; i < x + shipSize; i++){
                    if (this.getShipField().getPanelMarker(i, y + 1) == PanelState.SHIP){
                        return true;
                    }
                    if (this.getShipField().getPanelMarker(i, y - 1) == PanelState.SHIP){
                        return true;
                    }
                }
            }

            if(x + shipSize - 1 < fieldSize - 1){
                if (this.getShipField().getPanelMarker(x + shipSize, y) == PanelState.SHIP){
                    return true;
                }
            }
        }
        return false;
    }

 */
}
