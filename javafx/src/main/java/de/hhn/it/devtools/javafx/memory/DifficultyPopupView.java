package de.hhn.it.devtools.javafx.memory;

import de.hhn.it.devtools.javafx.controllers.memory.DifficultyPopupController;
import de.hhn.it.devtools.javafx.controllers.memory.MemoryScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DifficultyPopupView extends VBox {
  public DifficultyPopupView() {
    FXMLLoader loader = new FXMLLoader(getClass()
        .getResource("/fxml/memory/DifficultyScreen.fxml"));
    loader.setController(new DifficultyPopupController());
    loader.setRoot(this);
    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
