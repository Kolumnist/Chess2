package de.hhn.it.devtools.javafx.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.DuckHuntListener;
import de.hhn.it.devtools.apis.duckhunt.DucksInfo;
import de.hhn.it.devtools.apis.duckhunt.GameInfo;
import de.hhn.it.devtools.apis.duckhunt.IllegalDuckIdException;
import de.hhn.it.devtools.apis.duckhunt.IllegalDuckPositionException;
import de.hhn.it.devtools.apis.duckhunt.IllegalGameInfoException;
import de.hhn.it.devtools.components.duckhunt.DuckHunt;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DuckHuntGameController implements Initializable, DuckHuntListener {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DuckHuntScreenController.class);

  public static final String SCREEN = "game.screen";

  private DuckHunt game;
  private Parent root;
  private Stage stage;

  public DuckHuntGameController() {
    try {
      root = FXMLLoader.load(getClass().getClassLoader().getResource("/fxml/duckhunt/DuckHuntGameScreen.fxml"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    stage = new Stage();
    stage.setTitle("DuckHunt");
    stage.setScene(new Scene(root, 960, 720));
    stage.show();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  @FXML
  private AnchorPane anchorPane;

  @FXML
  private ImageView backgroundImage;

  @FXML
  private ImageView bushImage;

  @FXML
  private ImageView grassImage;

  @FXML
  private ImageView groundImage;

  @FXML
  private ImageView treeImage;

  @FXML
  void backgroundClicked(MouseEvent event) {

  }

  @FXML
  void bushClicked(MouseEvent event) {

  }

  @FXML
  void grassClicked(MouseEvent event) {

  }

  @FXML
  void treeClicked(MouseEvent event) {

  }

  @Override
  public void newState(GameInfo gameInfo) throws IllegalGameInfoException {

  }

  @Override
  public void newDuckPosition(DucksInfo duckPosition) throws IllegalDuckPositionException {

  }

  @Override
  public void duckHit(int id) throws IllegalDuckIdException {

  }
}
