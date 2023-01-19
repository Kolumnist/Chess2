package de.hhn.it.devtools.javafx.controllers.memory;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import de.hhn.it.devtools.apis.memory.PictureCardListener;
import de.hhn.it.devtools.apis.memory.State;
import de.hhn.it.devtools.components.memory.provider.SfsMemoryService;
import de.hhn.it.devtools.javafx.controllers.MemoryServiceController;
import de.hhn.it.devtools.javafx.memory.CardView;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


/**
 * Controller for a memory card.
 */
public class CardController implements PictureCardListener {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(CardController.class);
  private static int cardMatched = 0;
  private static int howManyAreTurned = 0;
  private final PictureCardDescriptor pictureCardDescriptor;
  private final MemoryScreenController screenController;
  private final SfsMemoryService memoryService;
  private final CardView view;
  private final Label name;
  private final ImageView picture;
  private final Pane cover;


  /**
   * Constructor for a Card Controller.
   *
   * @param pictureCardDescriptor the picture card descriptor the controller
   *                              hould be constructed for
   */
  public CardController(PictureCardDescriptor pictureCardDescriptor, CardView view) {
    this.screenController = (MemoryScreenController) MemoryAttributeStore.getReference()
            .getAttribute(MemoryServiceController.SCREEN_CONTROLLER);
    this.memoryService = (SfsMemoryService) MemoryAttributeStore.getReference()
            .getAttribute(MemoryServiceController.MEMORY_SERVICE);
    cardMatched = 0;
    this.pictureCardDescriptor = pictureCardDescriptor;
    this.view = view;
    this.name = view.name;
    this.picture = view.picture;
    this.cover = view.cover;

    if (isNameCard(pictureCardDescriptor)) {
      initNameCard();
    } else {
      initPictureCard();
    }

    try {
      memoryService.addCallback(pictureCardDescriptor.getId(), this);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }

    logger.info("Card Controller - created: id = " + pictureCardDescriptor.getId());
  }

  /**
   * Handles what happens if the card is clicked.
   *
   * @param event if the card is clicked
   */
  public void onMouseClicked(MouseEvent event) {
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

  /**
   * Initializes name card with name.
   */
  private void initNameCard() {
    name.setText(pictureCardDescriptor.getName());
  }

  /**
   * Initializes picture card with picture.
   */
  private void initPictureCard() {
    try {
      picture.setImage(new Image(getPathByPictureReference(pictureCardDescriptor.getPictureRef())));
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sets the card visible.
   */
  private void showCard() {
    cover.setStyle("-fx-background-color: Transparent; -fx-background-radius: 5;"
            + " -fx-border-radius: 5; -fx-border-color: Red; -fx-border-width: 3");
  }

  /**
   * Set the card hidden.
   */
  private void hideCard() {
    cover.setStyle("-fx-background-color: Gray; -fx-background-radius: 5;"
            + " -fx-border-radius: 5; -fx-border-color: BLACK;");
  }

  /**
   * Determines if card is name or picture card.
   *
   * @param pictureCardDescriptor descriptor of the card
   * @return true if it's a name card
   */
  private boolean isNameCard(PictureCardDescriptor pictureCardDescriptor) {
    return pictureCardDescriptor.getPictureRef() == -1;
  }

  /**
   * Determines the path of the picture files from the picture reference.
   *
   * @param pictureReference reference to the picture
   * @return String of the file path
   * @throws IllegalParameterException if the picture reference is not found
   */
  private String getPathByPictureReference(int pictureReference) throws IllegalParameterException {
    //workaround because of unchecked cast from object to hashmap
    HashMap<?, ?> x = (HashMap<?, ?>) MemoryAttributeStore
            .getReference().getAttribute(MemoryServiceController.PATH_REFERENCES);
    HashMap<Integer, String> pathReferences = new HashMap<>(x.size());
    for (Map.Entry<?, ?> entry : x.entrySet()) {
      pathReferences.put((Integer) entry.getKey(), (String) entry.getValue());
    }
    //
    if (!pathReferences.containsKey(pictureReference)) {
      throw new IllegalParameterException("PictureReference not found");
    }
    return pathReferences.get(pictureReference);
  }

  @Override
  public void currentState(State state) {
    new Thread(() -> {
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
                + " -fx-border-radius: 5; -fx-border-color: Lime; -fx-border-width: 3");
            screenController.enableGameGrid();
            view.setDisable(true);
            screenController.setGameScreenMessage("Cards match!");
          });
          if (++cardMatched == 20) {
            cardMatched = 0;
            Platform.runLater(screenController::gameWon);
          }
          howManyAreTurned = 0;
        }
        default -> { }
      }
    }).start();
  }
}
