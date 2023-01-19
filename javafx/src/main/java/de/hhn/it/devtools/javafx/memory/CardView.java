package de.hhn.it.devtools.javafx.memory;

import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import de.hhn.it.devtools.javafx.controllers.memory.CardController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CardView extends Pane {
  private CardController controller;
  public final Label name;
  public final ImageView picture;
  public final Pane cover;

  public CardView(PictureCardDescriptor pictureCardDescriptor) {
    this.setPrefSize(150, 120);
    this.setMaxSize(150, 120);
    this.setStyle("-fx-background-color: WHITE;"
        + " -fx-background-radius: 5;");

    name = new Label();
    name.setLayoutX(20);
    name.setLayoutY(40);
    picture = new ImageView();
    picture.setFitHeight(120);
    picture.setFitWidth(150);
    cover = new Pane();
    cover.setPrefSize(150, 120);
    cover.setStyle("-fx-background-color: Gray; -fx-background-radius: 5;"
        + " -fx-border-radius: 5; -fx-border-color: BLACK;");
    this.getChildren().add(picture);
    this.getChildren().add(name);
    this.getChildren().add(cover);
    name.setAlignment(Pos.CENTER);
    controller = new CardController(pictureCardDescriptor, this);
    this.setOnMouseClicked(controller::onMouseClicked);
  }
}
