package de.hhn.it.devtools.apis.battleship;

import java.util.ArrayList;

public class Player {
    protected Field attackField;
    protected Field shipField;
    protected ArrayList<Ship> ownedShips = new ArrayList<>();
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(Player.class);


    public void setAttackField(Field attackField){
        logger.debug("setAttackField: - {}", attackField);
        this.attackField = attackField;

    }


    public void setShipfield(Field shipField){
        logger.debug("setShipField: - {}", shipField);
        this.shipField = shipField;
    }


    public Field getAttackField(){
        return this.attackField;
    }


    public Field getShipField(){
        return this.shipField;
    }


    public void setOwnedShips(Ship addShip){
        ownedShips.add(addShip);
    }

    public ArrayList<Ship> getOwnedShips() {
        return ownedShips;
    }
    


    public int countShipType(ShipType type){
        int count = 0;
        for (Ship ownedShip : ownedShips) {
            if ( ownedShip.getShipType() == type  && !ownedShip.getPlaced()){
                count ++;
            }
        }
        return  count;
    }

    public Ship returnNextShip(ShipType type){

        for (Ship ownedShip : ownedShips) {
            if ( ownedShip.getShipType() == type  && !ownedShip.getPlaced()){
                return ownedShip;
            }
        }

        return null;
    }

    public boolean hasUnplacedShipsLeft(){
        for (Ship ownedShip : ownedShips) {
            if(!ownedShip.getPlaced()){
                return  true;
            }
        }
        return false;
    }

}
