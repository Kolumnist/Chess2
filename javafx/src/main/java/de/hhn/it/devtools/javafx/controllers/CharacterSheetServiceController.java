package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.apis.ttrpgsheets.CharacterDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheet;
import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheetListener;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DiceDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.StatDescriptor;
import de.hhn.it.devtools.components.ttrpgsheets.DefaultCharacterSheet;
import de.hhn.it.devtools.javafx.controllers.ttrpgsheets.DescriptionViewController;
import de.hhn.it.devtools.javafx.controllers.ttrpgsheets.PlayerDiceViewController;
import de.hhn.it.devtools.javafx.controllers.ttrpgsheets.StatViewController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterSheetServiceController extends Controller implements CharacterSheetListener {
  private static final Logger logger = LoggerFactory
          .getLogger(CharacterSheetServiceController.class);

  @FXML
  private Pane descriptionPane;
  @FXML
  private Pane statPane;
  @FXML
  private Pane playerDicePane;

  private static final CharacterSheet characterSheet
          = new DefaultCharacterSheet(CharacterDescriptor.EMPTY);;

  private DescriptionViewController descriptionController;
  private Node descriptionNode;
  private StatViewController statController;
  private Node statNode;
  private PlayerDiceViewController playerDiceController;
  private Node playerDiceNode;

  public CharacterSheetServiceController() {
    FXMLLoader loader;
    try {
      loader = new FXMLLoader(getClass().getResource("/fxml/ttrpgsheets/DescriptionView.fxml"));
      descriptionNode = loader.load();
      descriptionController = loader.getController();
      loader = new FXMLLoader(getClass().getResource("/fxml/ttrpgsheets/StatView.fxml"));
      statNode = loader.load();
      statController = loader.getController();
      loader = new FXMLLoader(getClass().getResource("/fxml/ttrpgsheets/PlayerDiceView.fxml"));
      playerDiceNode = loader.load();
      playerDiceController = loader.getController();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static CharacterSheet getCharacterSheet() {
    return characterSheet;
  }

  @FXML
  public void initialize() {
    characterSheet.addCallback(this);
    descriptionPane.getChildren().add(descriptionNode);
    statPane.getChildren().add(statNode);
    playerDicePane.getChildren().add(playerDiceNode);
  }

  @Override
  public void statChanged(StatDescriptor stat) {
    statController.statChanged(stat);
  }

  @Override
  public void descriptionChanged(DescriptionDescriptor description) {
    descriptionController.descriptionChanged(description);
    playerDiceController.descriptionChanged(description);
  }

  @Override
  public void diceChanged(DiceDescriptor dice) {
    playerDiceController.diceChanged(dice);
  }

  @Override
  void pause() {
    logger.debug("pause: -");
  }

  @Override
  void resume() {
    logger.debug("resume: -");
  }
}
