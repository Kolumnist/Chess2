package de.hhn.it.devtools.javafx.memory;

import de.hhn.it.devtools.javafx.controllers.memory.WinningPopupController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class WinningPopupView extends VBox {
  public WinningPopupView() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/memory/WinningScreen.fxml"));
    loader.setController(new WinningPopupController());
    loader.setRoot(this);
    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
