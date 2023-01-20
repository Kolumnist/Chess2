package de.hhn.it.devtools.components.minesweeper.provider;

import de.hhn.it.devtools.apis.minesweeper.MinesweeperCoordinates;
import de.hhn.it.devtools.apis.minesweeper.Status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * The Handler class supports the LJMinesweeper Class and has the needed logic in it.
 *
 * @author Lara Weller, Jason Herrmann
 * @version 0.5
 */

public class Handler {
  private Status[][] visualMatrix;
  private int[][] matrix;

  /**
   * Constructor, which will automatically create and fill the Field.
   *
   * @param size size of the Field
   * @param bombCount how many bombs will be created
   */
  public Handler(int size, int bombCount) {

    createLogicField(size, bombCount);
    createFieldForVisuals();
  }

  /**
   * Method to crate Logic Field.
   *
   * @param size size of the Field
   * @param bombCount how many bombs will be created
   */
  private void createLogicField(int size, int bombCount) {
    initField(size);
    fillMatrixWithBombs(bombCount);
  }

  /**
   * Initialized field and fills it with 0.
   *
   * @param size size of the Field
   */
  private void initField(int size) {
    matrix = new int[size][size];
    for (int[] row : matrix) {
      Arrays.fill(row, 0);
    }
  }

  /**
   * Fills Field with Bombs and correctly adjust neighbour Fields.
   *
   * @param bombCount how many bombs will be created
   */
  private void fillMatrixWithBombs(int bombCount) {
    //Init Bomb-List
    ArrayList<Integer> bombs = new ArrayList<>(Collections.nCopies(bombCount, -1));
    for (int i = 0; i < (matrix.length * matrix.length) - bombCount; i++) {
      bombs.add(0);
    }
    Collections.shuffle(bombs);

    //Fill with Bombs and Adjust neighbourFields
    int k = -1;
    for (int y = 0; y < matrix.length; y++) {
      for (int x = 0; x < matrix.length; x++) {
        if (bombs.get(++k) == -1) {
          matrix[x][y] = bombs.get(k);
          adjustStatusForAdjustingFields(new MinesweeperCoordinates(x, y));
        }
      }
    }
  }

  /**
   * Wrapper Class to call adjustStatusForAdjustingFields.
   *
   * @param coords Coordinate to know where the bomb was placed
   */
  public  void adjustStatusForAdjustingFieldsWrapper(MinesweeperCoordinates coords) {
    adjustStatusForAdjustingFields(coords);
  }

  /**
   * Method to Adjust Numbers around Bombs.
   *
   * @param coords Coordinate to know where the bomb was placed
   */
  private void adjustStatusForAdjustingFields(MinesweeperCoordinates coords)
      throws IndexOutOfBoundsException {
    matrix[coords.x()][coords.y()] = -1;
    for (int y = -1; y <= 1; y++) {
      for (int x = -1; x <= 1; x++) {
        try {
          if (matrix[coords.x() + x][coords.y() + y] != -1) {
            matrix[coords.x() + x][coords.y() + y]++;
          }
        } catch (IndexOutOfBoundsException e) {
          System.out.println("Went out of bounds");
        }
      }
    }
  }

  /**
   * Wrapper Class to call createFieldForVisuals.
   *
   */
  public void createFieldForVisualsWrapper() {
    createFieldForVisuals();
  }

  /**
   * Method to create Field from matrix with Statuses instead of int.
   *
   */
  private void createFieldForVisuals() {
    int size = matrix.length;
    visualMatrix = new Status[size][size];
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        visualMatrix[x][y] = intToStatus(matrix[x][y]);
      }
    }
  }

  /**
   * Wrapper Class to call intToStatus.
   *
   * @param n int which will be converted to Status
   */
  public Status intToStatusWrapper(int n) {
    return intToStatus(n);
  }


  /**
   * Method to covert int to enum Status.
   *
   * @param n int which will be converted to Status
   */
  private Status intToStatus(int n) {
    return switch (n) {
      case 0 -> Status.NOTHING;
      case 1 -> Status.ONE;
      case 2 -> Status.TWO;
      case 3 -> Status.THREE;
      case 4 -> Status.FOUR;
      case 5 -> Status.FIVE;
      case 6 -> Status.SIX;
      case 7 -> Status.SEVEN;
      case 8 -> Status.EIGHT;
      default -> Status.BOMB;
    };
  }

  /**
   * Method to return Value of clicked field.
   *
   * @param coords Coordinates to know which field was clicked
   */
  public Status clickField(MinesweeperCoordinates coords) throws IndexOutOfBoundsException {
    return visualMatrix[coords.x()][coords.y()];
  }

  /**
   * Method to return Value of marked field and change the Value to Flag / Status.
   *
   * @param coords Coordinates to know which field was clicked
   */
  public Status markField(MinesweeperCoordinates coords) throws  IndexOutOfBoundsException {
    if (visualMatrix[coords.x()][coords.y()] != Status.FLAG) {
      visualMatrix[coords.x()][coords.y()] = Status.FLAG;
      return Status.FLAG;
    }
    Status fieldStatus = intToStatus(matrix[coords.x()][coords.y()]);
    visualMatrix[coords.x()][coords.y()] = fieldStatus;
    return fieldStatus;
  }

  public int[][] getMatrix() {
    return matrix;
  }

  public void setMatrix(int[][] matrix) {
    this.matrix = matrix;
  }

  public Status[][] getVisualMatrix() {
    return visualMatrix;
  }
}