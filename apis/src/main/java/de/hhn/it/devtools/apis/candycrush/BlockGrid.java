package de.hhn.it.devtools.apis.candycrush;

public class BlockGrid {
    private Block [][] blocks;


    /**
     *Create a BlockGrid with empty blocks.
     */
    public BlockGrid() {

    }

    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    public Block [][] getBlocks() {
        return blocks;
    }
    public void changeColorOfBlocks(int xPos, int yPos , String  color) {

    }
}
