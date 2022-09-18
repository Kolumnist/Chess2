package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.javafx.modules.Module;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;

public class RootController extends Controller implements Initializable {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(RootController.class);

  @FXML
  private MenuBar menuBar;

  @FXML
  private ListView<String> listView;

  @FXML
  private AnchorPane modulePane;



  private Module actualModule = null;
  private Map<String, Module> moduleMap;


  public RootController() {
    logger.debug("RootController created.");
    moduleMap = new HashMap<>();
  }

  /**
   * Called to initialize a controller after its root element has been
   * completely processed.
   *
   * @param location  The location used to resolve relative paths for the root object, or
   *                  <code>null</code> if the location is not known.
   * @param resources The resources used to localize the root object, or <code>null</code> if
   */
  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    listView.getSelectionModel().selectedItemProperty().addListener(
            (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
              logger.info("Selected item: " + newValue);
              modulePane.getChildren().clear();
              Module module = moduleMap.get(newValue);
              // notify actual controller that it will pause (going to be invisible)
              actualModule.getController().pause();
              actualModule = module;
              modulePane.getChildren().add(module.getSceneGraph());
              // notify new actual controller that its content is now visible
              module.getController().resume();
            }
    );

  }

  public void addModule(Module module) {
    listView.getItems().add(module.getName());
    moduleMap.put(module.getName(), module);
    if (actualModule == null) {
      actualModule = module;
      listView.getSelectionModel().selectFirst();
    }
  }


}
