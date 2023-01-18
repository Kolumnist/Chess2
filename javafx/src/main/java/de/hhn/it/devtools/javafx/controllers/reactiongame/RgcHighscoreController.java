package de.hhn.it.devtools.javafx.controllers.reactiongame;

import de.hhn.it.devtools.javafx.controllers.ReactionGameController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class RgcHighscoreController implements Initializable {

  private RgcScreenController screenController;
  @FXML
  private Label highscore1;

  @FXML
  private Label highscore2;

  @FXML
  private Label highscore3;

  @FXML
  private Label highscore4;

  @FXML
  private Label highscore5;

  @FXML
  private Label highscore6;


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
    screenController =
        (RgcScreenController) singletonAttributeStore.getAttribute(ReactionGameController.RGC_SCREEN_CONTROLLER);

    try {
      readHighscores();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void readHighscores() throws IOException {
    TreeMap<String, String> highScoreTreeMap = new TreeMap<>();
    Properties properties = new Properties();

    properties.load(
        new FileInputStream("javafx/src/main/resources/reactiongame/highscore.list"));

    for (String key :
      properties.stringPropertyNames()) {
      highScoreTreeMap.put(key, properties.get(key).toString());
    }

    ArrayList<Label> labels = new ArrayList<>() {
      {
        add(highscore1);
        add(highscore2);
        add(highscore3);
        add(highscore4);
        add(highscore5);
        add(highscore6);
      }
    };

    NavigableMap<String, String> reverseHighscoreList = highScoreTreeMap.descendingMap();
    ArrayList<String> scoreList = new ArrayList<>(reverseHighscoreList.keySet());

    ArrayList<String> nameList = new ArrayList<>(reverseHighscoreList.values());


    for (int i = 0; i < (Math.min(scoreList.size(), 7)); i++) {
      Label label = labels.get(i);
      label.setText(scoreList.get(i) + " - " + nameList.get(i));
    }
  }

  @FXML
  void onBackToMenuBtn() {
    try {
      screenController.switchTo("RgcMenu");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
