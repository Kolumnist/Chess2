package de.hhn.it.devtools.javafx.controllers.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.CharacterDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheet;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionType;
import de.hhn.it.devtools.apis.ttrpgsheets.DiceDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DiceType;
import de.hhn.it.devtools.apis.ttrpgsheets.StatDescriptor;
import de.hhn.it.devtools.javafx.ttrpgsheets.CharacterSheetFileIo;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerDiceViewController extends CharacterViewController {
  private static final Logger logger = LoggerFactory.getLogger(CharacterViewController.class);

  @FXML
  private Label diceLabel;
  @FXML
  private MenuButton diceMenuButton;
  @FXML
  private TextField playerNameTextField;

  CharacterSheetFileIo characterSheetFileIo;
  SimpleStringProperty playerNameProperty;
  SimpleStringProperty diceTypeProperty;
  SimpleIntegerProperty diceResultProperty;

  public PlayerDiceViewController(CharacterSheet characterSheet) {
    super(characterSheet);
    characterSheetFileIo = new CharacterSheetFileIo();
    playerNameProperty = new SimpleStringProperty("");
    diceTypeProperty = new SimpleStringProperty();
    diceResultProperty = new SimpleIntegerProperty();
  }

  @FXML
  void initialize() {
    // ########## Description binding ##########
    // Player name
    playerNameTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.PLAYER_NAME, newValue));
    playerNameTextField.textProperty().bindBidirectional(playerNameProperty);

    // ########## Dice binding ##########
    diceMenuButton.textProperty().bind(diceTypeProperty);
    diceLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(diceResultProperty.get()), diceResultProperty));
  }

  @FXML
  void onLoad(ActionEvent event) {
    logger.info("Load pressed");
    characterSheetFileIo.setParent(((Node) event.getSource()).getScene().getWindow());
    CharacterDescriptor loadedCharacter = characterSheetFileIo.loadCharacterFile();
    if (loadedCharacter != null) {
      characterSheet.unwrapCharacter(loadedCharacter);
    }
  }

  @FXML
  void onSave(ActionEvent event) {
    logger.info("Save pressed");
    String name = characterSheet.getDescriptionDescriptor(DescriptionType.CHARACTER_NAME).getText();
    characterSheetFileIo.setParent(((Node) event.getSource()).getScene().getWindow());
    characterSheetFileIo.saveCharacterFile(name.isBlank() ? "My Character" : name,
            characterSheet.wrapCharacter());
  }

  @FXML
  void onRollDice(ActionEvent event) {
    logger.info("Roll dice pressed");
    characterSheet.rollDice();
  }

  @FXML
  void onSetD2(ActionEvent event) {
    logger.info("Set D2 pressed");
    characterSheet.changeDiceType(DiceType.D2);
  }

  @FXML
  void onSetD4(ActionEvent event) {
    logger.info("Set D4 pressed");
    characterSheet.changeDiceType(DiceType.D4);
  }

  @FXML
  void onSetD6(ActionEvent event) {
    logger.info("Set D6 pressed");
    characterSheet.changeDiceType(DiceType.D6);
  }

  @FXML
  void onSetD8(ActionEvent event) {
    logger.info("Set D8 pressed");
    characterSheet.changeDiceType(DiceType.D8);
  }

  @FXML
  void onSetD10(ActionEvent event) {
    logger.info("Set D10 pressed");
    characterSheet.changeDiceType(DiceType.D10);
  }

  @FXML
  void onSetD12(ActionEvent event) {
    logger.info("Set D12 pressed");
    characterSheet.changeDiceType(DiceType.D12);
  }

  @FXML
  void onSetD20(ActionEvent event) {
    logger.info("Set D20 pressed");
    characterSheet.changeDiceType(DiceType.D20);
  }

  @FXML
  void onSetD100(ActionEvent event) {
    logger.info("Set D100 pressed");
    characterSheet.changeDiceType(DiceType.D100);
  }
  @Override
  public void statChanged(StatDescriptor stat) {

  }

  @Override
  public void descriptionChanged(DescriptionDescriptor description) {
    if (description.getDescriptionType() == DescriptionType.PLAYER_NAME) {
      Platform.runLater(() -> playerNameProperty.set(description.getText()));
    }
  }

  @Override
  public void diceChanged(DiceDescriptor dice) {
    Platform.runLater(() -> {
      diceResultProperty.set(dice.getResult());
      switch (dice.getDiceType()) {
        case D2 -> diceTypeProperty.set("D2");
        case D4 -> diceTypeProperty.set("D4");
        case D6 -> diceTypeProperty.set("D6");
        case D8 -> diceTypeProperty.set("D8");
        case D10 -> diceTypeProperty.set("D10");
        case D12 -> diceTypeProperty.set("D12");
        case D20 -> diceTypeProperty.set("D20");
        case D100 -> diceTypeProperty.set("D100");
        default -> {
        }
      }
    });
  }
}
