package de.hhn.it.devtools.components.battleship.provider;

import de.hhn.it.devtools.apis.battleship.*;

import java.util.Objects;
import java.util.Random;
import java.util.Map;

public class Computer extends Player {

    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(Computer.class);

    Random random = new Random();

    public boolean checkSurroundings(Position position, boolean isVertical, int shipSize, int fieldSize){
        int x = position.getX();
        int y = position.getY();
        if(isVertical == true){
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

    private void placeShipSmall(Map<Player, Owner> player2OwnerMap, CmpBattleshipService service, int num) throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        int x = random.nextInt(5);
        int y = random.nextInt(5);
        boolean placeVertical = false;
        if(random.nextInt(2) == 1){
            placeVertical = true;
            service.rotateShip(player2OwnerMap.get(this), getOwnedShips().get(num));
        }
        try {
            service.placeShip(player2OwnerMap.get(this), getOwnedShips().get(num), x, y);
        } catch (IllegalPositionException e){
            placeShipSmall(player2OwnerMap, service, num);
        }

        //StackOverflowError
        //if(getOwnedShips().get(num).getPlaced()) {
        //    boolean retry = checkSurroundings(getOwnedShips().get(num).getFieldPosition(), placeVertical, getOwnedShips().get(num).getSize(), 5);
        //    if(retry){
        //        service.unPlace(player2OwnerMap.get(this), getOwnedShips().get(num));
        //        placeShipSmall(player2OwnerMap, service, num);
        //    }
        //}
    }

    public void comShipPlacement(Map<Player, Owner> player2OwnerMap, CmpBattleshipService service, int size) throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {

        for(int num = 0; num < getOwnedShips().size(); num++){
            if(size == 5){
                placeShipSmall(player2OwnerMap, service, num);
            }
        }
    }

}
