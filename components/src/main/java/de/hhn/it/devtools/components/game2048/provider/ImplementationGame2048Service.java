package de.hhn.it.devtools.components.game2048.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.game2048.Block;
import de.hhn.it.devtools.apis.game2048.Game2048Listener;
import de.hhn.it.devtools.apis.game2048.Game2048Service;
import de.hhn.it.devtools.apis.game2048.MovingDirection;
import de.hhn.it.devtools.apis.game2048.Position;
import de.hhn.it.devtools.apis.game2048.State;
import de.hhn.it.devtools.components.game2048.provider.comparators.HorizontalComparator;
import de.hhn.it.devtools.components.game2048.provider.comparators.VerticalComparator;
import java.util.ArrayList;

/**
 * Implementation of Game2048Service interface.
 */
public class ImplementationGame2048Service implements Game2048Service {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ImplementationGame2048Service.class);
  private ArrayList<Block> gameBoard;
  private ArrayList<Position> freelist;
  private int currentScore;
  private boolean gameWon;
  private boolean gameLost;
  private final int[] values;
  private int valueIndex;
  private ArrayList<Game2048Listener> gameListeners;

  /**
   * Basic Constructor.
   */
  public ImplementationGame2048Service() {
    this.gameBoard = new ArrayList<>();
    this.freelist = new ArrayList<>();
    this.currentScore = 0;
    this.gameWon = false;
    this.gameLost = false;
    this.gameListeners = new ArrayList<>();
    values = new int[]{2, 2, 4, 4, 0, 0};
  }

  @Override
  public void initialisation() {
    logger.info("new Game started");
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        freelist.add(new Position(i, j));
      }
    }
    gameBoard.clear();
    try {
      addBlock(2);
      addBlock(2);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }
    this.currentScore = 0;
    this.gameWon = false;
    this.gameLost = false;
    notifyGame2048Listener();
  }

  @Override
  public void moveAllBlocks(MovingDirection direction) throws IllegalParameterException {
    predictableMoveAllBlocks(direction);
    if (valueIndex >= values.length) {
      valueIndex = 0;
    }
    int value = values[valueIndex++];
    if (value > 0) {
      addBlock(value);
    }
    isGameWon();
    notifyGame2048Listener();
  }

  @Override
  public void addCallback(Game2048Listener listener) throws IllegalParameterException {
    logger.info("addCallback: listener = {}", listener);
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
    logger.info("removeCallback: listener = {}", listener);
    if (listener == null) {
      throw new IllegalParameterException("Tried to remove null reference instead of a Listener.");
    } else if (!gameListeners.contains(listener)) {
      throw new IllegalParameterException("Removing Listener failed, because of wrong parameter.");
    } else {
      gameListeners.remove(listener);
    }
  }

  /**
   * Contains core functionality of moveAllBlocks, but without adding a Block at a random Position.
   * This makes the Methode predictable, which allows better testing.
   */
  public void predictableMoveAllBlocks(MovingDirection direction) throws IllegalParameterException {
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
  }

  /**
   * Is public to enable direct manipulation of the game-board from outside this class.
   *
   * @param xyPosition x and y coordinates of new Block
   * @param value      Value the Block should have
   * @throws IllegalParameterException if Position is not a free Space or if {value % 2 == 1}
   */
  public void addBlock(Position xyPosition, int value) throws IllegalParameterException {
    logger.info("addBlock: xyPosition = {}, value = {}", xyPosition, value);
    if (freelist.contains(xyPosition)) {
      freelist.remove(xyPosition);
      gameBoard.add(new Block(xyPosition, value));
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
      gameLost = false;
    } else if (freelist.size() == 1) {
      Position position = freelist.get(0);
      addBlock(position, value);
      gameLost = false;
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
  private void moveBlocks(ArrayList<Block> columnRow, MovingDirection direction)
          throws IllegalParameterException {
    logger.info("moveBlocks: columnRow = {}, direction = {}", columnRow, direction);
    if (direction == null) {
      throw new IllegalParameterException("direction was null reference.");
    } else if (columnRow == null) {
      throw new IllegalParameterException("column or row were null reference.");
    }
    for (Block removed : columnRow) {
      gameBoard.remove(removed);
    }
    boolean mergeForbid = false;
    Block previousBlock = null;
    switch (direction) {
      case up -> {
        for (int i = 0; i < columnRow.size(); i++) {
          columnRow.sort(new VerticalComparator(direction));
          freelist.add(columnRow.get(i).getXYPosition());
          if (previousBlock == null) {
            Block movingBlock = columnRow.get(i).changeYPosition(3);
            previousBlock = changeColumnRow(columnRow, i, movingBlock);
            mergeForbid = false;
          } else if (previousBlock.getValue() == columnRow.get(i).getValue() && !mergeForbid) {
            previousBlock = mergeBlocks(columnRow, i, previousBlock);
            i--;
            mergeForbid = true;
          } else {
            Block movingBlock = columnRow.get(i).changeYPosition(
                    previousBlock.getXYPosition().getYPosition() - 1);
            previousBlock = changeColumnRow(columnRow, i, movingBlock);
            mergeForbid = false;
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
            mergeForbid = false;
          } else if (previousBlock.getValue() == columnRow.get(i).getValue() && !mergeForbid) {
            previousBlock = mergeBlocks(columnRow, i, previousBlock);
            i--;
            mergeForbid = true;
          } else {
            Block movingBlock = columnRow.get(i).changeYPosition(
                    previousBlock.getXYPosition().getYPosition() + 1);
            previousBlock = changeColumnRow(columnRow, i, movingBlock);
            mergeForbid = false;
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
            mergeForbid = false;
          } else if (previousBlock.getValue() == columnRow.get(i).getValue() && !mergeForbid) {
            previousBlock = mergeBlocks(columnRow, i, previousBlock);
            i--;
            mergeForbid = true;
          } else {
            Block movingBlock = columnRow.get(i).changeXPosition(
                    previousBlock.getXYPosition().getXPosition() - 1);
            previousBlock = changeColumnRow(columnRow, i, movingBlock);
            mergeForbid = false;
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
            mergeForbid = false;
          } else if (previousBlock.getValue() == columnRow.get(i).getValue() && !mergeForbid) {
            previousBlock = mergeBlocks(columnRow, i, previousBlock);
            i--;
            mergeForbid = true;
          } else {
            Block movingBlock = columnRow.get(i).changeXPosition(
                    previousBlock.getXYPosition().getXPosition() + 1);
            previousBlock = changeColumnRow(columnRow, i, movingBlock);
            mergeForbid = false;
          }
        }
      }
      default -> {
        logger.warn("Reaching default should be impossible");
      }
    }
    gameBoard.addAll(columnRow);
  }

  /**
   * Notifies all Frontends to present new Data.
   */
  private void notifyGame2048Listener() {
    logger.info("notifyGame2048Listener: no params");
    updateScores();
    Block[] gameBoardArray = new Block[gameBoard.size()];
    for (int i = 0; i < gameBoard.size(); i++) {
      gameBoardArray[i] = gameBoard.get(i);
    }
    State currentState = new State(gameBoardArray, currentScore, gameWon, gameLost);
    gameListeners.forEach((listener) -> listener.newState(currentState));
  }

  /**
   * Updates currentScore.
   *
   * @throws IllegalStateException if currentScore are negative
   */
  private void updateScores() throws IllegalStateException {
    currentScore = 0;
    for (Block gameBlock : gameBoard) {
      currentScore += gameBlock.getValue();
    }
    if (currentScore < 0) {
      throw new IllegalStateException("Summ of Values of all Blocks in game-board is < 0");
    }
  }

  /**
   * Checks if there is a Block with value >= 2048, if so the game is won.
   */
  private void isGameWon() {
    for (Block gameBlock : gameBoard) {
      if (gameBlock.getValue() >= 2048) {
        gameWon = true;
        break;
      }
    }
  }

  /**
   * Adds moved block to columnRow, updates freelist, returns moved Block as previousBlock.
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
   * @throws IllegalParameterException if currentColumn is outside Boundaries
   */
  private ArrayList<Block> getColumn(int currentColumn) throws IllegalParameterException {
    if (currentColumn > 3 || currentColumn < 0) {
      throw new IllegalParameterException("Tried to get column outside the game-board boundaries");
    }
    ArrayList<Block> column = new ArrayList<>();
    for (Block block : gameBoard) {
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
    for (Block block : gameBoard) {
      if (block.getXYPosition().getYPosition() == currentRow) {
        row.add(block);
      }
    }
    return row;
  }

  public ArrayList<Block> getGameBoard() {
    return gameBoard;
  }

  public ArrayList<Position> getFreelist() {
    return freelist;
  }

  /**
   * Setter are only for Testing.
   */
  public void setGameBoard(ArrayList<Block> gameBoard) {
    this.gameBoard = gameBoard;
  }

  public void setFreelist(ArrayList<Position> freelist) {
    this.freelist = freelist;
  }
}
