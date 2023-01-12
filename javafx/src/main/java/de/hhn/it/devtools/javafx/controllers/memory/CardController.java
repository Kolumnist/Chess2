package de.hhn.it.devtools.javafx.controllers.memory;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import de.hhn.it.devtools.apis.memory.PictureCardListener;
import de.hhn.it.devtools.components.memory.provider.SfsMemoryService;
import de.hhn.it.devtools.javafx.controllers.MemoryServiceController;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CardController extends Pane implements Initializable{
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(CardController.class);

    PictureCardDescriptor pictureCardDescriptor;

    private final MemoryScreenController screenController;
    private final SfsMemoryService memoryService;

    private ImageView picture;

    private Label name;

    private Pane cover;

    private void onMouseClicked(MouseEvent event) {
        logger.info("Clicked Card " + pictureCardDescriptor.getId());
        showCard();
        try {
            //memoryService.turnCard(pictureCardDescriptor.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CardController(PictureCardDescriptor pictureCardDescriptor) {
        this.screenController = (MemoryScreenController) MemoryAttributeStore.getReference().getAttribute(MemoryServiceController.SCREEN_CONTROLLER);
        this.memoryService = (SfsMemoryService) MemoryAttributeStore.getReference().getAttribute(MemoryServiceController.MEMORY_SERVICE);
        this.pictureCardDescriptor = pictureCardDescriptor;
        this.setPrefSize(150, 120);
        this.setMaxSize(150, 120);
        this.setStyle("-fx-background-color: WHITE; -fx-border-radius: 5; -fx-background-radius: 5; -fx-border-width: 3; -fx-border-color: black;");
        this.setOnMouseClicked(this::onMouseClicked);

        name = new Label();
        name.setLayoutX(60);
        name.setLayoutY(40);
        picture = new ImageView();
        picture.setFitHeight(120);
        picture.setFitWidth(150);

        if (isNameCard(pictureCardDescriptor)) {
            initNameCard();
        } else { initPictureCard(); }

        cover = new Pane();
        cover.setPrefSize(150, 120);
        cover.setStyle("-fx-background-color: Gray; -fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: BLACK;");

        PictureCardListener listener = state -> Platform.runLater(() -> {
            switch (state) {
                case HIDDEN -> {
                    logger.info("Card " + pictureCardDescriptor.getId() + " hidden.");
                    hideCard();
                }
                case MATCHED -> {
                    logger.info("Card " + pictureCardDescriptor.getId() + " matched.");
                    cover.setStyle("-fx-background-color: Transparent; -fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: Green;");
                }
            }
        });

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

    private void initNameCard(){
        name.setText(pictureCardDescriptor.getName().replace(".png", "").replace(".jpg", ""));
    }

    private void initPictureCard() {
        try {
            picture.setImage(new Image(getPathByPictureReference(pictureCardDescriptor.getPictureRef())));
        } catch (IllegalParameterException e) {
            e.printStackTrace();
        }
    }

    private void showCard() {
        cover.setStyle("-fx-background-color: Transparent; -fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: Red;");
    }

    private void hideCard() {
        cover.setStyle("-fx-background-color: Gray; -fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: BLACK;");
    }
    private String getPathByPictureReference(int pictureReference) throws IllegalParameterException {
        HashMap<Integer, String> pathReferences = (HashMap<Integer, String>) MemoryAttributeStore.getReference().getAttribute(MemoryServiceController.PATH_REFERENCES);
        if (!pathReferences.containsKey(pictureReference)) {
            throw new IllegalParameterException("PictureReference not found");
        }
        return pathReferences.get(pictureReference);
    }

    private boolean isNameCard(PictureCardDescriptor pictureCardDescriptor) {
        return pictureCardDescriptor.getPictureRef() == -1;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("CardController created.");
    }
}
