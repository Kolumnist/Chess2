package de.hhn.it.devtools.javafx.reactiongame;

import de.hhn.it.devtools.apis.reactiongame.HighscoreTupel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class RgcHighScoreHandler {

  public static ArrayList<HighscoreTupel> highscoreList;

  public static ArrayList<HighscoreTupel> readHighscoreList() {
    ObjectInputStream ois;
    try {
      ois = new ObjectInputStream(
          new FileInputStream("javafx/src/main/resources/reactiongame/highscore.list"));
    } catch (IOException e) {
      makeDemoHighscoreList();
      try {
        ois = new ObjectInputStream(
            new FileInputStream("javafx/src/main/resources/reactiongame/highscore.list"));
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    }

    ArrayList<HighscoreTupel> out;

    try {
      out = (ArrayList<HighscoreTupel>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }

    try {
      ois.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return out;
  }

  public static void writeHighscoreList(ArrayList<HighscoreTupel> newHighScoreList) {
    ObjectOutputStream oos;
    try {
      oos = new ObjectOutputStream(
          new FileOutputStream("javafx/src/main/resources/reactiongame/highscore.list"));
      oos.writeObject(newHighScoreList);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try {
      oos.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void makeDemoHighscoreList() {
    ArrayList<HighscoreTupel> scores = new ArrayList<>();

    scores.add(new HighscoreTupel("Neptune", 500));
    scores.add(new HighscoreTupel("Jupiter", 400));
    scores.add(new HighscoreTupel("Mars", 300));

    Collections.sort(scores);

    ObjectOutputStream oos;

    try {
      oos = new ObjectOutputStream(
          new FileOutputStream("javafx/src/main/resources/reactiongame/highscore.list"));

      oos.writeObject(scores);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
