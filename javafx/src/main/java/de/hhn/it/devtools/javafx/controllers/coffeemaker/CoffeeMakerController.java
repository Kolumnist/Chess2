package de.hhn.it.devtools.javafx.controllers.coffeemaker;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerDescriptor;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerListener;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerService;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.Quantity;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.Recipe;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.State;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.io.IOException;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;



public class CoffeeMakerController extends AnchorPane implements CoffeeMakerListener {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(CoffeeMakerController.class);

  @FXML
  Label locationLabel;
  @FXML
  Label modelLabel;
  @FXML
  Label statusLabel;

  @FXML
  Button onButton;
  @FXML
  Button offButton;
  @FXML
  Button cleanItButton;
  @FXML
  Button brewButton;

  private BooleanProperty onButtonProperty;
  private BooleanProperty offButtonProperty;
  private BooleanProperty cleanItButtonProperty;
  private BooleanProperty brewButtonProperty;

  private CoffeeMakerService coffeeMakerService;
  private CoffeeMakerDescriptor coffeeMakerDescriptor;

  public CoffeeMakerController(final CoffeeMakerService coffeeMakerService,
                               final CoffeeMakerDescriptor coffeeMakerDescriptor) {
    logger.info("Creating CoffeeMakerController for {}", coffeeMakerDescriptor);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/coffeemaker"
            + "/CoffeeMakerControl.fxml"));


    loader.setRoot(this);
    loader.setController(this);


    try {
      loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // bind enable/disable of buttons to BooleanProperty objects
    onButtonProperty = new SimpleBooleanProperty(false);
    onButton.disableProperty().bind(onButtonProperty);

    offButtonProperty = new SimpleBooleanProperty(true);
    offButton.disableProperty().bind(offButtonProperty);

    cleanItButtonProperty = new SimpleBooleanProperty(true);
    cleanItButton.disableProperty().bind(cleanItButtonProperty);

    brewButtonProperty = new SimpleBooleanProperty(true);
    brewButton.disableProperty().bind(brewButtonProperty);

    this.coffeeMakerService = coffeeMakerService;
    this.coffeeMakerDescriptor = coffeeMakerDescriptor;

    locationLabel.setText(coffeeMakerDescriptor.getLocation());
    modelLabel.setText(coffeeMakerDescriptor.getModel());
    statusLabel.setText(coffeeMakerDescriptor.getState().toString());
    setState(coffeeMakerDescriptor.getState());
    try {
      coffeeMakerService.addCallback(coffeeMakerDescriptor.getId(), this);
    } catch (IllegalParameterException e) {
      logger.error("Cannot add Callback for {} to {}", coffeeMakerDescriptor, coffeeMakerService);
    }

  }

  private void raiseExceptionToUI(final Exception e, final String header) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(header);
    alert.setContentText(e.getMessage());
    alert.showAndWait();
  }

  public void onSwitchOn(ActionEvent event) {
    logger.debug("Switch on pressed.");
    try {
      coffeeMakerService.switchOn(coffeeMakerDescriptor.getId());
    } catch (IllegalParameterException | IllegalStateException e) {
      raiseExceptionToUI(e, "Error in SWITCHON");
    }
  }

  public void onSwitchOff(ActionEvent event) {
    logger.debug("Switch off pressed.");
    try {
      coffeeMakerService.switchOff(coffeeMakerDescriptor.getId());
    } catch (IllegalParameterException | IllegalStateException e) {
      raiseExceptionToUI(e, "error in SWITCHOFF");
    }
  }

  public void onCleanIt(ActionEvent event) {
    logger.debug("CleanIt pressed.");
    try {
      coffeeMakerService.cleanIt(coffeeMakerDescriptor.getId());
    } catch (IllegalParameterException | IllegalStateException e) {
      raiseExceptionToUI(e, "error in CLEANIT");
    }
  }

  public void onBrew(ActionEvent event) {
    logger.debug("Brew pressed.");
    try {
      coffeeMakerService.brewCoffee(coffeeMakerDescriptor.getId(),
              new Recipe(Quantity.LARGE, Quantity.NONE, Quantity.NONE, Quantity.NONE));
    } catch (IllegalParameterException | IllegalStateException e) {
      raiseExceptionToUI(e, "error in BREW");
    }
  }

  @Override
  public void newState(final State state) {
    Platform.runLater(() -> setState(state));
  }

  private void setState(State state) {
    statusLabel.setText(state.toString());
    switch (state) {
      case OFF:
        onButtonProperty.set(false);
        offButtonProperty.set(true);
        cleanItButtonProperty.set(true);
        brewButtonProperty.set(true);
        break;
      case READY:
        onButtonProperty.set(true);
        offButtonProperty.set(false);
        cleanItButtonProperty.set(false);
        brewButtonProperty.set(false);
        break;
      case BREWING:
        onButtonProperty.set(true);
        offButtonProperty.set(true);
        cleanItButtonProperty.set(true);
        brewButtonProperty.set(true);
        break;
      case CLEANING:
        onButtonProperty.set(true);
        offButtonProperty.set(true);
        cleanItButtonProperty.set(true);
        brewButtonProperty.set(true);
        break;
      case HEATING:
        onButtonProperty.set(true);
        offButtonProperty.set(true);
        cleanItButtonProperty.set(true);
        brewButtonProperty.set(true);
        break;
      default:
        logger.error("Illegal state: " + state);
    }
  }


}
