package de.hhn.it.devtools.components.game2048.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.game2048.*;
import de.hhn.it.devtools.components.game2048.provider.Comparators.DownComparator;
import de.hhn.it.devtools.components.game2048.provider.Comparators.LeftComparator;
import de.hhn.it.devtools.components.game2048.provider.Comparators.RightComparator;
import de.hhn.it.devtools.components.game2048.provider.Comparators.UpComparator;

import java.util.ArrayList;

/**
 * Implementation of Game2048Service interface.
 */
public class ImplementationGame2048Service implements de.hhn.it.devtools.apis.game2048.Game2048Service {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ImplementationGame2048Service.class);
  private final ArrayList<Block> gameboard = new ArrayList<>();
  private final ArrayList<Position> freelist = new ArrayList<>();
  private int currentScore = 0;
  private static int highScore = 0;
  private boolean gameWon = false;
  private boolean gameLost = false;
  private Game2048Listener gameListener;
  // TODO: 27.12.2022 Methoden gegen falsche eingabewerte schützen/ exeptions hinzufügen
  // TODO: 05.01.2023 log4j benutzen anstatt sout in Comperator Klassen
  // TODO: 08.01.2023 Highscore abspeichern??

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
  }

  /**
   * Adds a Block to random free position
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
          columnRow.sort(new UpComparator());
          freelist.add(columnRow.get(i).getXYPosition());
          if (previousBlock == null) {
            Block movingBlock = columnRow.get(i).changeYPosition(3);
            columnRow.remove(columnRow.get(i));
            columnRow.add(movingBlock);
            previousBlock = movingBlock;
            freelist.remove(movingBlock.getXYPosition());
          } else if (previousBlock.getValue() == columnRow.get(i).getValue()) {
            columnRow.remove(columnRow.get(i));
            columnRow.remove(previousBlock);
            previousBlock = previousBlock.changeValue(previousBlock.getValue() * 2);
            columnRow.add(previousBlock);
            i--;
          } else {
            Block tmp = columnRow.get(i).changeYPosition(previousBlock.getXYPosition().getYPosition() - 1);
            columnRow.remove(columnRow.get(i));
            columnRow.add(tmp);
            previousBlock = tmp;
            freelist.remove(tmp.getXYPosition());
          }
        }
      }
      case down -> {
        for (int i = 0; i < columnRow.size(); i++) {
          columnRow.sort(new DownComparator());
          freelist.add(columnRow.get(i).getXYPosition());
          if (previousBlock == null) {
            Block movingBlock = columnRow.get(i).changeYPosition(0);
            columnRow.remove(columnRow.get(i));
            columnRow.add(movingBlock);
            previousBlock = movingBlock;
            freelist.remove(movingBlock.getXYPosition());
          } else if (previousBlock.getValue() == columnRow.get(i).getValue()) {
            columnRow.remove(columnRow.get(i));
            columnRow.remove(previousBlock);
            previousBlock = previousBlock.changeValue(previousBlock.getValue() * 2);
            columnRow.add(previousBlock);
            i--;
          } else {
            Block tmp = columnRow.get(i).changeYPosition(previousBlock.getXYPosition().getYPosition() + 1);
            columnRow.remove(columnRow.get(i));
            columnRow.add(tmp);
            previousBlock = tmp;
            freelist.remove(tmp.getXYPosition());
          }
        }
      }
      case right -> {
        for (int i = 0; i < columnRow.size(); i++) {
          columnRow.sort(new RightComparator());
          freelist.add(columnRow.get(i).getXYPosition());
          if (previousBlock == null) {
            Block movingBlock = columnRow.get(i).changeXPosition(3);
            columnRow.remove(columnRow.get(i));
            columnRow.add(movingBlock);
            previousBlock = movingBlock;
            freelist.remove(movingBlock.getXYPosition());
          } else if (previousBlock.getValue() == columnRow.get(i).getValue()) {
            columnRow.remove(columnRow.get(i));
            columnRow.remove(previousBlock);
            previousBlock = previousBlock.changeValue(previousBlock.getValue() * 2);
            columnRow.add(previousBlock);
            i--;
          } else {
            Block tmp = columnRow.get(i).changeXPosition(previousBlock.getXYPosition().getXPosition() - 1);
            columnRow.remove(columnRow.get(i));
            columnRow.add(tmp);
            previousBlock = tmp;
            freelist.remove(tmp.getXYPosition());
          }
        }
      }
      case left -> {
        for (int i = 0; i < columnRow.size(); i++) {
          columnRow.sort(new LeftComparator());
          freelist.add(columnRow.get(i).getXYPosition());
          if (previousBlock == null) {
            Block movingBlock = columnRow.get(i).changeXPosition(0);
            columnRow.remove(columnRow.get(i));
            columnRow.add(movingBlock);
            previousBlock = movingBlock;
            freelist.remove(movingBlock.getXYPosition());
          } else if (previousBlock.getValue() == columnRow.get(i).getValue()) {
            columnRow.remove(columnRow.get(i));
            columnRow.remove(previousBlock);
            previousBlock = previousBlock.changeValue(previousBlock.getValue() * 2);
            columnRow.add(previousBlock);
            i--;
          } else {
            Block tmp = columnRow.get(i).changeXPosition(previousBlock.getXYPosition().getXPosition() + 1);
            columnRow.remove(columnRow.get(i));
            columnRow.add(tmp);
            previousBlock = tmp;
            freelist.remove(tmp.getXYPosition());
          }
        }
      }
    }
    gameboard.addAll(columnRow);
  }

  /**
   * Notifies the Frontend to present the new Data
   */
  private void notifyGame2048Listener() {
    logger.info("notifyGame2048Listener: no params");
    Block[] gameboardArray = new Block[gameboard.size()];
    for (int i = 0; i < gameboard.size(); i++) {
      gameboardArray[i] = gameboard.get(i);
    }
    State currentState = new State(gameboardArray, currentScore, highScore, gameWon, gameLost);
    gameListener.newState(currentState);
  }

  /**
   * Updates currentScore and highScore
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
    }
  }

  private void isGameWon() {
    if (currentScore >= 2048) {
      gameWon = true;
    }
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


  @Override
  public void addCallback(Game2048Listener listener) throws IllegalParameterException {
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    } else if (gameListener != null) {
      throw new IllegalParameterException("There is already a Listener registered.");
    } else {
      this.gameListener = listener;
    }
  }

  @Override
  public void removeCallback(Game2048Listener listener) throws IllegalParameterException {
    if (listener == null) {
      throw new IllegalParameterException("Tried to remove null reference instead of a Listener.");
    } else if (listener != gameListener) {
      throw new IllegalParameterException("Removing Listener failed, because of wrong parameter.");
    } else {
      this.gameListener = null;
    }
  }

  public static void main(String[] args) throws IllegalParameterException {
    ImplementationGame2048Service g = new ImplementationGame2048Service();
    g.initialisation();
    g.addCallback(new Game2048Listener() {
      @Override
      public void newState(State state) {
        logger.info("newState: state = {}", state);
      }
    });
    moves(g);
    moves(g);
    moves(g);
  }

  /**
   * For Testing purposes.
   */
  private static void moves(ImplementationGame2048Service g) throws IllegalParameterException {
    // TODO: 08.01.2023 delete this Methode
    g.moveAllBlocks(MovingDirection.down);
    g.moveAllBlocks(MovingDirection.right);
    g.moveAllBlocks(MovingDirection.up);
    g.moveAllBlocks(MovingDirection.left);
  }
}
