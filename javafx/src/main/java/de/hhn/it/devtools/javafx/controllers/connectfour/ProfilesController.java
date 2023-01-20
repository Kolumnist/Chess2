package de.hhn.it.devtools.javafx.controllers.connectfour;

import de.hhn.it.devtools.apis.connectfour.Profile;
import de.hhn.it.devtools.apis.connectfour.ProfileNotFoundException;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProfilesController {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(HighscoresController.class);

  @FXML
  Pane root;

  @FXML
  VBox vBox;

  @FXML
  Button btnNewProfile;

  @FXML
  void onNewProfile() {
      try {
        Parent proot = FXMLLoader.load(getClass().getResource("/fxml/connectfour/ProfilesNewScreen.fxml"));
        Stage stage = new Stage();
        stage.setTitle("New Profile");
        stage.setScene(new Scene(proot, 426, 240));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        SceneChanger.changeScene(root, "/fxml/connectfour/ProfilesScreen.fxml");
      }
      catch (IOException e) {
        e.printStackTrace();
      }
  }

  @FXML
  void onProfile(ActionEvent event){
    Button button = (Button) event.getTarget();
    try{
      ProfilesInfoController.pinfo = Instance.getInstance().getProfile(UUID.fromString(button.getId()));
    }
    catch (ProfileNotFoundException ex){
      SceneChanger.changeScene(root, "/fxml/connectfour/ProfilesScreen.fxml");
    }
    SceneChanger.changeScene(root, "/fxml/connectfour/ProfilesInfoScreen.fxml");
  }

  @FXML
  void onBack() {
    logger.info("Going back...");
    SceneChanger.changeScene(root, "/fxml/ConnectFour.fxml");
  }

  public void addProfileButton(Profile p){
    Button next = new Button();
    next.setPrefSize(200, 40);
    next.setText(p.getName());
    next.setId(p.getId() + "");
    next.setOnAction(this::onProfile);
    vBox.getChildren().add(next);
  }

  @FXML
  public void initialize(){
    List<Profile> profiles = new LinkedList<>(Instance.getInstance().getProfiles());
    Collections.sort(profiles, new Comparator<Profile>(){
      @Override
      public int compare(Profile o1, Profile o2) {
        return o1.getName().compareTo(o2.getName());
      }
    });
    vBox.getChildren().clear();
    for(int count = 0;count < profiles.size(); count++) {
      addProfileButton(profiles.get(count));
    }
  }

}
