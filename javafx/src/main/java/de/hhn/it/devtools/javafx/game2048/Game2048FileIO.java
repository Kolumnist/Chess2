package de.hhn.it.devtools.javafx.game2048;

import java.io.*;

public class Game2048FileIO {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(Game2048FileIO.class);

  /**
   * Loads the highest score a player scored on this physical device, from a File.
   */
  public static int loadHighscore() {
    logger.info("readHighscore: no params");
    int highScore = 0;

//ClassLoader.getSystemResource()
    FileInputStream fileInputStream;
    try {
      fileInputStream = new FileInputStream(String.valueOf(ClassLoader.getSystemResource("game2048/SaveGame2048.txt")));
      ObjectInputStream objectInputStream;
      objectInputStream = new ObjectInputStream(fileInputStream);
      highScore = objectInputStream.readInt();
      objectInputStream.close();
      logger.info("readHighscore, highScore = {}", highScore);

    } catch (FileNotFoundException e) {
      createHighscore(highScore);
    } catch (IOException e) {
      logger.warn("load highScore failed, because of ObjectOutputStream Error");
    }
    return highScore;
  }

  private static void createHighscore(int highScore) {
    logger.info("createHighscore: no params");
    ObjectOutputStream oos;

    try {
      File file = new File(String.valueOf(ClassLoader.getSystemResource("game2048/SaveGame2048.txt")));
      oos = new ObjectOutputStream(
              new FileOutputStream(file.getAbsolutePath()));

      oos.writeObject(highScore);
    } catch (IOException e) {
      logger.info("createHighscore Failed");
    }
  }


  /**
   * Writes the current value of highScore in a File.
   * CAUTION!!! If old highScore in the File is greater than the new highScore,
   * the old highScore will be overwritten.
   */
  public static void saveHighscore(int highScore) {
    logger.info("saveHighscore: no params");
    FileOutputStream fileOutputStream;
    try {
      fileOutputStream = new FileOutputStream(String.valueOf(ClassLoader.getSystemResource("game2048/SaveGame2048.txt")));
      ObjectOutputStream objectOutputStream;
      objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeInt(highScore);
      objectOutputStream.flush();
      objectOutputStream.close();
    } catch (IOException e) {
      logger.warn("save highScore failed, because of ObjectOutputStream Error");
    }
  }

}