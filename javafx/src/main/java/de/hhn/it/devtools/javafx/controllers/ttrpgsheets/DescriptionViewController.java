package de.hhn.it.devtools.javafx.controllers.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionType;
import de.hhn.it.devtools.apis.ttrpgsheets.DiceDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.StatDescriptor;
import java.util.HashMap;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DescriptionViewController extends CharacterViewController {

  @FXML
  private TextField ageTextField;
  @FXML
  private TextField characterClassTextField;
  @FXML
  private TextField characterNameTextField;
  @FXML
  private TextField eyeColorTextField;
  @FXML
  private TextField hairColorTextField;
  @FXML
  private TextField heightTextField;
  @FXML
  private TextField nickNameTextField;
  @FXML
  private TextArea otherTextField;
  @FXML
  private TextField raceTextField;
  @FXML
  private TextField skinColorTextField;
  @FXML
  private TextField weightTextField;

  HashMap<DescriptionType, SimpleStringProperty> description2Property;

  public DescriptionViewController() {
    description2Property = new HashMap<>();
    for (DescriptionType descriptionType : DescriptionType.values()) {
      description2Property.put(descriptionType, new SimpleStringProperty(""));
    }
  }

  @FXML
  void initialize() {
    // ########## Descriptions binding ##########
    // Character name
    characterNameTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.CHARACTER_NAME, newValue));
    characterNameTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.CHARACTER_NAME));
    // Nickname
    nickNameTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.NICKNAME, newValue));
    nickNameTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.NICKNAME));
    // Age
    ageTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.AGE, newValue));
    ageTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.AGE));
    // Race
    raceTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.RACE, newValue));
    raceTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.RACE));
    // Height
    heightTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.HEIGHT, newValue));
    heightTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.HEIGHT));
    // Weight
    weightTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.WEIGHT, newValue));
    weightTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.WEIGHT));
    // Skin color
    skinColorTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.SKIN_COLOR, newValue));
    skinColorTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.SKIN_COLOR));
    // Hair color
    hairColorTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.HAIR_COLOR, newValue));
    hairColorTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.HAIR_COLOR));
    // Eye color
    eyeColorTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.EYE_COLOR, newValue));
    eyeColorTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.EYE_COLOR));
    // Character class
    characterClassTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.CHARACTER_CLASS, newValue));
    characterClassTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.CHARACTER_CLASS));
    // Other
    otherTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.OTHER, newValue));
    otherTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.OTHER));
  }

  @Override
  public void statChanged(StatDescriptor stat) {

  }

  @Override
  public void descriptionChanged(DescriptionDescriptor description) {
    Platform.runLater(() ->
            description2Property.get(description.getDescriptionType()).set(description.getText()));
  }

  @Override
  public void diceChanged(DiceDescriptor dice) {

  }
}
