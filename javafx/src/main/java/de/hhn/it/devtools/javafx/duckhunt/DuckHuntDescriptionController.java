package de.hhn.it.devtools.javafx.duckhunt;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DuckHuntDescriptionController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DuckHuntDescriptionController.class);

  public static final String SCREEN = "description.screen";
  private DuckHuntScreenController screenController;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    DuckHuntAttributeStore duckHuntAttributeStore = DuckHuntAttributeStore.getReference();
    screenController =
        (DuckHuntScreenController) duckHuntAttributeStore
            .getAttribute(DuckHuntScreenController.SCREEN_CONTROLLER);
  }
}
