package de.hhn.it.devtools.javafx.reactiongame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class RgcHighScoreHandler {


  public ArrayList<RgcHighScoreSet> loadHighscoreList() {
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

    ArrayList<RgcHighScoreSet> out;

    try {
      out = (ArrayList<RgcHighScoreSet>) ois.readObject();
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

  public void writeHighscoreList(ArrayList<RgcHighScoreSet> newHighScoreList) {
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

  public void makeDemoHighscoreList() {
    ArrayList<RgcHighScoreSet> scores = new ArrayList<>();

    scores.add(new RgcHighScoreSet("Neptune", 500));
    scores.add(new RgcHighScoreSet("Jupiter", 400));
    scores.add(new RgcHighScoreSet("Mars", 300));

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
