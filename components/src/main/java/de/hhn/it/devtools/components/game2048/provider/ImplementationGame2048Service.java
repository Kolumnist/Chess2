package de.hhn.it.devtools.components.game2048.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.game2048.*;
import de.hhn.it.devtools.components.game2048.provider.Comparators.HorizontalComparator;
import de.hhn.it.devtools.components.game2048.provider.Comparators.VerticalComparator;

import java.io.*;
import java.util.ArrayList;

/**
 * Implementation of Game2048Service interface.
 */
public class ImplementationGame2048Service implements Game2048Service {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ImplementationGame2048Service.class);
  private final ArrayList<Block> gameboard;
  private final ArrayList<Position> freelist;
  private int currentScore;
  private static int highScore;
  private boolean gameWon;
  private boolean gameLost;
  private ArrayList<Game2048Listener> gameListeners;

  @Override
  public void initialisation() {
    logger.info("new Game started");
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        freelist.add(new Position(i, j));
      }
    }
    gameboard.clear();
    try {
      addBlock(2);
      addBlock(2);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }
    loadHighscore();
  }

  @Override
  public void moveAllBlocks(MovingDirection direction) throws IllegalParameterException {
    logger.info("moveAllBlocks: direction = {}", direction);
    ArrayList<Block> columnRow = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      if (direction == MovingDirection.down || direction == MovingDirection.up) {
        columnRow.addAll(getColumn(i));
      } else {
        columnRow.addAll(getRow(i));
      }
      if (columnRow.size() == 0) {
        continue;
      }
      moveBlocks(columnRow, direction);
      columnRow.clear();
    }
    addBlock(2);
    updateScores();
    isGameWon();
    notifyGame2048Listener();
  }

  @Override
  public void addCallback(Game2048Listener listener) throws IllegalParameterException {
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    } else if (gameListeners.contains(listener)) {
      throw new IllegalParameterException("There is already a Listener registered.");
    } else {
      gameListeners.add(listener);
    }
  }

  @Override
  public void removeCallback(Game2048Listener listener) throws IllegalParameterException {
    if (listener == null) {
      throw new IllegalParameterException("Tried to remove null reference instead of a Listener.");
    } else if (!gameListeners.contains(listener)) {
      throw new IllegalParameterException("Removing Listener failed, because of wrong parameter.");
    } else {
      gameListeners.remove(listener);
    }
  }

  public ImplementationGame2048Service() {
    this.gameboard = new ArrayList<>();
    this.freelist = new ArrayList<>();
    this.currentScore = 0;
    this.gameWon = false;
    this.gameLost = false;
    this.gameListeners = null;
    loadHighscore();
  }

  /**
   * Is public to enable direct manipulation of the game-board from outside this class.
   *
   * @param xyPosition x and y coordinates of new Block
   * @param value      Value the Block should have
   * @throws IllegalParameterException if Position is not a free Space or if {value % 2 == 1}
   */
  public void addBlock(Position xyPosition, int value) throws IllegalParameterException {
    logger.info("addBlock: xyPisition = {}, value = {}", xyPosition, value);
    if (freelist.contains(xyPosition)) {
      freelist.remove(xyPosition);
      gameboard.add(new Block(xyPosition, value));
    } else {
      throw new IllegalParameterException("Tried to add Block to a Space that is not free.");
    }
  }

  /**
   * Adds a Block to random free position.
   * If there was no more free space, to put a Block
   * and this Methode got called, the game is lost.
   *
   * @param value Value of the new Block
   * @throws IllegalParameterException if Position is not a free Space or if {value % 2 == 1}
   */
  private void addBlock(int value) throws IllegalParameterException {
    if (freelist.size() > 1) {
      Position position = freelist.get((int) (Math.random() * (freelist.size() - 1)));
      addBlock(position, value);
    } else if (freelist.size() == 1) {
      Position position = freelist.get(0);
      addBlock(position, value);
    } else {
      logger.info("game is Lost");
      gameLost = true;
    }
  }

  /**
   * The Blocks that are part of the column or row will be removed from the game-board.
   * Afterwards all Blocks get moved in the given direction and added to the game-board again.
   *
   * @param columnRow List of all Blocks that are part of the current column or row
   * @param direction The Blocks will be moved in this direction
   * @throws IllegalParameterException if a parameter is a null referenz
   */
  private void moveBlocks(ArrayList<Block> columnRow, MovingDirection direction) throws IllegalParameterException {
    logger.info("moveBlocks: columnRow = {}, direction = {}", columnRow, direction);
    if (direction == null) {
      throw new IllegalParameterException("direction was null reference.");
    } else if (columnRow == null) {
      throw new IllegalParameterException("column or row were null reference.");
    }
    for (Block removed : columnRow) {
      gameboard.remove(removed);
    }
    Block previousBlock = null;
    switch (direction) {
      case up -> {
        for (int i = 0; i < columnRow.size(); i++) {
          columnRow.sort(new VerticalComparator(direction));
          freelist.add(columnRow.get(i).getXYPosition());
          if (previousBlock == null) {
            Block movingBlock = columnRow.get(i).changeYPosition(3);
            previousBlock = changeColumnRow(columnRow, i, movingBlock);
          } else if (previousBlock.getValue() == columnRow.get(i).getValue()) {
            previousBlock = mergeBlocks(columnRow, i, previousBlock);
            i--;
          } else {
            Block movingBlock = columnRow.get(i).changeYPosition(previousBlock.getXYPosition().getYPosition() - 1);
            previousBlock = changeColumnRow(columnRow, i, movingBlock);
          }
        }
      }
      case down -> {
        for (int i = 0; i < columnRow.size(); i++) {
          columnRow.sort(new VerticalComparator(direction));
          freelist.add(columnRow.get(i).getXYPosition());
          if (previousBlock == null) {
            Block movingBlock = columnRow.get(i).changeYPosition(0);
            previousBlock = changeColumnRow(columnRow, i, movingBlock);
          } else if (previousBlock.getValue() == columnRow.get(i).getValue()) {
            previousBlock = mergeBlocks(columnRow, i, previousBlock);
            i--;
          } else {
            Block movingBlock = columnRow.get(i).changeYPosition(previousBlock.getXYPosition().getYPosition() + 1);
            previousBlock = changeColumnRow(columnRow, i, movingBlock);
          }
        }
      }
      case right -> {
        for (int i = 0; i < columnRow.size(); i++) {
          columnRow.sort(new HorizontalComparator(direction));
          freelist.add(columnRow.get(i).getXYPosition());
          if (previousBlock == null) {
            Block movingBlock = columnRow.get(i).changeXPosition(3);
            previousBlock = changeColumnRow(columnRow, i, movingBlock);
          } else if (previousBlock.getValue() == columnRow.get(i).getValue()) {
            previousBlock = mergeBlocks(columnRow, i, previousBlock);
            i--;
          } else {
            Block movingBlock = columnRow.get(i).changeXPosition(previousBlock.getXYPosition().getXPosition() - 1);
            previousBlock = changeColumnRow(columnRow, i, movingBlock);
          }
        }
      }
      case left -> {
        for (int i = 0; i < columnRow.size(); i++) {
          columnRow.sort(new HorizontalComparator(direction));
          freelist.add(columnRow.get(i).getXYPosition());
          if (previousBlock == null) {
            Block movingBlock = columnRow.get(i).changeXPosition(0);
            previousBlock = changeColumnRow(columnRow, i, movingBlock);
          } else if (previousBlock.getValue() == columnRow.get(i).getValue()) {
            previousBlock = mergeBlocks(columnRow, i, previousBlock);
            i--;
          } else {
            Block movingBlock = columnRow.get(i).changeXPosition(previousBlock.getXYPosition().getXPosition() + 1);
            previousBlock = changeColumnRow(columnRow, i, movingBlock);
          }
        }
      }
    }
    gameboard.addAll(columnRow);
  }

  /**
   * Notifies all Frontends to present new Data
   */
  private void notifyGame2048Listener() {
    logger.info("notifyGame2048Listener: no params");
    Block[] gameboardArray = new Block[gameboard.size()];
    for (int i = 0; i < gameboard.size(); i++) {
      gameboardArray[i] = gameboard.get(i);
    }
    State currentState = new State(gameboardArray, currentScore, highScore, gameWon, gameLost);
    gameListeners.forEach((listener) -> listener.newState(currentState));
  }

  /**
   * Updates currentScore and highScore.
   * Highscore gets saved if necessary.
   *
   * @throws IllegalStateException if currentScore or highScore are negative
   */
  private void updateScores() throws IllegalStateException {
    currentScore = 0;
    for (Block gameBlock : gameboard) {
      currentScore += gameBlock.getValue();
    }
    if (currentScore < 0) {
      throw new IllegalStateException("Summ of Values of all Blocks in game-board is < 0");
    }
    if (highScore < 0) {
      throw new IllegalStateException("Highscore is < 0");
    }
    if (currentScore > highScore) {
      highScore = currentScore;
      saveHighscore();
    }
  }

  /**
   * Checks if there is a Block with value >= 2048,
   * if so the game is won
   */
  private void isGameWon() {
    for (Block gameBlock : gameboard) {
      if (gameBlock.getValue() >= 2048) {
        gameWon = true;
        break;
      }
    }
  }

  /**
   * Loads the highest score a player scored on this physical device, from a File.
   */
  private void loadHighscore() {
    logger.info("readHighscore: no params");
    highScore = 0;
    FileInputStream fileInputStream = null;
    try {
      fileInputStream = new FileInputStream("SaveGame2048.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    if (fileInputStream != null) {
      ObjectInputStream objectInputStream;
      try {
        objectInputStream = new ObjectInputStream(fileInputStream);
        highScore = objectInputStream.readInt();
        objectInputStream.close();
        logger.info("readHighscore, highscore = {}", highScore);
      } catch (IOException e) {
        logger.warn("load highScore failed, because of ObjectOutputStream Error");
        e.printStackTrace();
      }
    } else {
      logger.warn("load highScore failed, because File related Error");
    }
  }

  /**
   * Writes the current value of highScore in a File.
   * CAUTION!!! If old highScore in the File is greater than the new highScore,
   * the old highScore will be overwritten.
   */
  private void saveHighscore() {
    logger.info("safeHighscore: no params");
    FileOutputStream fileOutputStream = null;
    try {
      fileOutputStream = new FileOutputStream("SaveGame2048.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    if (fileOutputStream != null) {
      ObjectOutputStream objectOutputStream;
      try {
        objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeInt(highScore);
        objectOutputStream.flush();
        objectOutputStream.close();
      } catch (IOException e) {
        logger.warn("save highScore failed, because of ObjectOutputStream Error");
        e.printStackTrace();
      }
    } else {
      logger.warn("save highScore failed, because File related Error");
    }
  }

  /**
   * Adds moved block to columnRow, updates freelist, returns moved Block as previousBlock
   */
  private Block changeColumnRow(ArrayList<Block> columnRow, int i, Block movingBlock) {
    Block previousBlock;
    columnRow.remove(columnRow.get(i));
    columnRow.add(movingBlock);
    previousBlock = movingBlock;
    freelist.remove(movingBlock.getXYPosition());
    return previousBlock;
  }

  private Block mergeBlocks(ArrayList<Block> columnRow, int i, Block previousBlock) {
    columnRow.remove(columnRow.get(i));
    columnRow.remove(previousBlock);
    previousBlock = previousBlock.changeValue(previousBlock.getValue() * 2);
    columnRow.add(previousBlock);
    return previousBlock;
  }

  /**
   * Searches the game-board for Blocks of a particular Column.
   *
   * @param currentColumn index of the current Column
   * @return List of Blocks with {xPosition == currentColumn}
   * @throws IllegalParameterException if currentCollumn is outside Boundaries
   */
  private ArrayList<Block> getColumn(int currentColumn) throws IllegalParameterException {
    if (currentColumn > 3 || currentColumn < 0) {
      throw new IllegalParameterException("Tried to get column outside the game-board boundaries");
    }
    ArrayList<Block> column = new ArrayList<>();
    for (Block block : gameboard) {
      if (block.getXYPosition().getXPosition() == currentColumn) {
        column.add(block);
      }
    }
    return column;
  }

  /**
   * Searches the game-board for Blocks of a particular Row.
   *
   * @param currentRow index of the current Column
   * @return List of Blocks with {yPosition == currentColumn}
   * @throws IllegalParameterException if currentRow is outside Boundaries
   */
  private ArrayList<Block> getRow(int currentRow) throws IllegalParameterException {
    if (currentRow > 3 || currentRow < 0) {
      throw new IllegalParameterException("Tried to get row outside the game-board boundaries");
    }
    ArrayList<Block> row = new ArrayList<>();
    for (Block block : gameboard) {
      if (block.getXYPosition().getYPosition() == currentRow) {
        row.add(block);
      }
    }
    return row;
  }
}
