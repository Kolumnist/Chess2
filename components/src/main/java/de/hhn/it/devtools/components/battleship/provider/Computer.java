package de.hhn.it.devtools.components.battleship.provider;

import de.hhn.it.devtools.apis.battleship.*;

import java.util.Random;

public class Computer extends Player {

    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(Computer.class);

    CmpBattleshipService service;

    Random random = new Random();

    Computer (CmpBattleshipService service){
        this.service = service;
    }
    public void comBomb(Position position, Player player, int oldZ) throws IllegalGameStateException {
        int size = Field.getSize();
        boolean hit = false;
        int x = random.nextInt(size);
        int y = random.nextInt(size);


        if(getAttackField().getPanelMarker(x, y).equals(PanelState.NOSHIP)) {
            int z = -1;
            if (position == null) {
                hit = service.bombPanel(this, player, x, y);
            } else {
                z = random.nextInt(4);
                if (z == 0 && position.getX() != 0 && oldZ != 0) {
                    hit = service.bombPanel(this, player, position.getX() - 1, position.getY());
                } else if (z == 1 && position.getX() != size - 1 && oldZ != 1) {
                    hit = service.bombPanel(this, player, position.getX() + 1, position.getY());
                } else if (z == 2 && position.getY() != 0 && oldZ != 2) {
                    hit = service.bombPanel(this, player, position.getX(), position.getY() - 1);
                } else if (z == 3 && position.getY() != size - 1 && oldZ != 3) {
                    hit = service.bombPanel(this, player, position.getX(), position.getY() + 1);
                } else {

                    comBomb(position, player, oldZ);
                }
            }

            if (hit) {
                Position newHit = new Position(x, y);
                comBomb(newHit, player, z);
            }
        }
        else {
            comBomb(position, player, oldZ);
        }
    }

    private void placeShipSmall(int num) throws IllegalShipStateException, IllegalGameStateException, IllegalPositionException {
        int x = random.nextInt(5);
        int y = random.nextInt(5);
        if(random.nextInt(2) == 1){
            service.rotateShip(this, getOwnedShips().get(num));
        }

        try {
            service.placeShip(this, getOwnedShips().get(num), x, y);
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
            service.rotateShip(this, getOwnedShips().get(num));
        }

        try {
            service.placeShip(this, getOwnedShips().get(num), position.getX(), position.getY());
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
}
