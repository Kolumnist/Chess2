package de.hhn.it.devtools.javafx.controllers.memory;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import de.hhn.it.devtools.apis.memory.PictureCardListener;
import de.hhn.it.devtools.components.memory.provider.SfsMemoryService;
import de.hhn.it.devtools.javafx.controllers.MemoryServiceController;
import java.util.HashMap;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;



public class CardController extends Pane {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(CardController.class);
  private static int cardMatched = 0;
  private static int howManyAreTurned = 0;
  private final PictureCardDescriptor pictureCardDescriptor;
  private final MemoryScreenController screenController;
  private final SfsMemoryService memoryService;

  private final ImageView picture;
  private final Label name;
  private final Pane cover;

  public CardController(PictureCardDescriptor pictureCardDescriptor) {
    this.screenController = (MemoryScreenController) MemoryAttributeStore.getReference()
            .getAttribute(MemoryServiceController.SCREEN_CONTROLLER);
    this.memoryService = (SfsMemoryService) MemoryAttributeStore.getReference()
            .getAttribute(MemoryServiceController.MEMORY_SERVICE);
    this.pictureCardDescriptor = pictureCardDescriptor;
    this.setPrefSize(150, 120);
    this.setMaxSize(150, 120);
    this.setStyle("-fx-background-color: WHITE; -fx-border-radius: 5;"
            + " -fx-background-radius: 5; -fx-border-width: 3; -fx-border-color: black;");
    this.setOnMouseClicked(this::onMouseClicked);

    name = new Label();
    name.setLayoutX(20);
    name.setLayoutY(40);
    picture = new ImageView();
    picture.setFitHeight(120);
    picture.setFitWidth(150);

    if (isNameCard(pictureCardDescriptor)) {
      initNameCard();
    } else {
      initPictureCard();
    }

    cover = new Pane();
    cover.setPrefSize(150, 120);
    cover.setStyle("-fx-background-color: Gray; -fx-background-radius: 5;"
            + " -fx-border-radius: 5; -fx-border-color: BLACK;");

    PictureCardListener listener = state -> {
      new Thread(new Runnable() {
        @Override
        public void run() {
          switch (state) {
            case HIDDEN -> {
              logger.info("Card " + pictureCardDescriptor.getId() + " hidden.");
              try {
                Platform.runLater(() -> screenController.setGameScreenMessage("Checking cards..."));
                Thread.sleep(1500);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              Platform.runLater(() -> {
                hideCard();
                screenController.setGameScreenMessage("Cards don't match!");
                screenController.enableGameGrid();
              });
              howManyAreTurned = 0;
            }
            case MATCHED -> {
              logger.info("Card " + pictureCardDescriptor.getId() + " matched.");
              Platform.runLater(() -> {
                cover.setStyle("-fx-background-color: Transparent;-fx-background-radius: 5;"
                        + " -fx-border-radius: 5; -fx-border-color: Green;");
                screenController.enableGameGrid();
                screenController.setGameScreenMessage("Cards match!");
              });
              if (++cardMatched == 20) {
                Platform.runLater(screenController::gameWon);
              }
              howManyAreTurned = 0;
            }
            default -> { }
          }
        }
      }).start();
    };

    try {
      memoryService.addCallback(pictureCardDescriptor.getId(), listener);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }

    this.getChildren().add(picture);
    this.getChildren().add(name);
    this.getChildren().add(cover);
    name.setAlignment(Pos.CENTER);
    logger.info("Card Controller - created: id = " + pictureCardDescriptor.getId());
  }

  private void onMouseClicked(MouseEvent event) {
    logger.info("Clicked Card " + pictureCardDescriptor.getId());
    showCard();
    if (++howManyAreTurned == 2) {
      screenController.disableGameGrid();
    }
    try {
      memoryService.turnCard(pictureCardDescriptor.getId());

    } catch (IllegalParameterException e) {
      howManyAreTurned = 1;
      screenController.enableGameGrid();
    }
  }

  private boolean isNameCard(PictureCardDescriptor pictureCardDescriptor) {
    return pictureCardDescriptor.getPictureRef() == -1;
  }

  private void initNameCard() {
    name.setText(pictureCardDescriptor.getName().replace(".png", "").replace(".jpg", ""));
  }

  private void initPictureCard() {
    try {
      picture.setImage(new Image(getPathByPictureReference(pictureCardDescriptor.getPictureRef())));
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }
  }

  private String getPathByPictureReference(int pictureReference) throws IllegalParameterException {
    HashMap<Integer, String> pathReferences = (HashMap<Integer, String>) MemoryAttributeStore
            .getReference().getAttribute(MemoryServiceController.PATH_REFERENCES);
    if (!pathReferences.containsKey(pictureReference)) {
      throw new IllegalParameterException("PictureReference not found");
    }
    return pathReferences.get(pictureReference);
  }

  private void showCard() {
    cover.setStyle("-fx-background-color: Transparent; -fx-background-radius: 5;"
            + " -fx-border-radius: 5; -fx-border-color: Red;");
  }

  private void hideCard() {
    cover.setStyle("-fx-background-color: Gray; -fx-background-radius: 5;"
            + " -fx-border-radius: 5; -fx-border-color: BLACK;");
  }

}
