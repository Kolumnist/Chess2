package de.hhn.it.devtools.javafx.game2048;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class Game2048FileIO {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(Game2048FileIO.class);

  /**
   * Loads the highest score a player scored on this physical device, from a File.
   */
  public static int loadHighscore() {
    logger.info("readHighscore: no params");
    int highScore = 0;
    FileInputStream fileInputStream = null;
    try {
      fileInputStream = new FileInputStream("javafx\\src\\main\\java\\de\\hhn\\it\\devtools\\javafx\\game2048\\SaveGame2048.txt");
      ObjectInputStream objectInputStream;
      objectInputStream = new ObjectInputStream(fileInputStream);
      highScore = objectInputStream.readInt();
      objectInputStream.close();
      logger.info("readHighscore, highScore = {}", highScore);

    } catch (FileNotFoundException e) {
      logger.info("File does not exist yet");
    } catch (IOException e) {
      logger.warn("load highScore failed, because of ObjectOutputStream Error");
    }
    return highScore;
  }



  /**
   * Writes the current value of highScore in a File.
   * CAUTION!!! If old highScore in the File is greater than the new highScore,
   * the old highScore will be overwritten.
   */
  public static void saveHighscore(int highScore) {
    logger.info("saveHighscore: no params");
    FileOutputStream fileOutputStream = null;
    try {
      fileOutputStream = new FileOutputStream("javafx\\src\\main\\java\\de\\hhn\\it\\devtools\\javafx\\game2048\\SaveGame2048.txt");
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

}