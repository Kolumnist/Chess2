package de.hhn.it.devtools.apis.candycrush;

public class Block {


    /**
     * The x and y position of the Block in the BlockGrid.
     */
    private int xPos;
    private int yPos;


    /**
     * The Color of the Block.
     */
    private final String color;


    /**
     *Marks a Block as fixed, which means it´s color can0t be changed.
     */



    public Block(int xPos, int yPos, String color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public String getColor() {
        return color;
    }

    /**
     * Checks the state of the Blocks.
     *
     * @param gameBoard
     * @return
     */
    public boolean checkBlocks(GameBoard gameBoard) {
        return false;
    }
}
