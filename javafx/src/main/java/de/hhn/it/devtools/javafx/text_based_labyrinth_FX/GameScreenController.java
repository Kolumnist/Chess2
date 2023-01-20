package de.hhn.it.devtools.javafx.text_based_labyrinth_FX;


import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class GameScreenController {


    private GameMainScreen mainScreen;
    private MenuScreen menuScreen;
    private InteractScreen interactScreen;
    private InventoryScreen inventoryScreen;
    private RoomInventoryScreen roomInventoryScreen;

    private Pane paneController;
    private AnchorPane screen;



    public GameScreenController(final Pane pane) {
        mainScreen = new GameMainScreen(this);
        menuScreen = new MenuScreen(this);
        interactScreen = new InteractScreen(this);
        inventoryScreen = new InventoryScreen(this);
        roomInventoryScreen = new RoomInventoryScreen(this);

        paneController = pane;
        paneController.getChildren().clear();
    }


    public GameMainScreen getMainScreen() {
        return mainScreen;
    }

    public MenuScreen getMenuScreen() {
        return menuScreen;
    }

    public InteractScreen getInteractScreen() {
        return interactScreen;
    }

    public InventoryScreen getInventoryScreen() {
        return inventoryScreen;
    }

    public RoomInventoryScreen getRoomInventoryScreen() {
        return roomInventoryScreen;
    }


    public void changeScreen(String from, String to) throws UnknownTransitionException {
        if (from.isEmpty()) {
            throw new UnknownTransitionException("From was zero.", from, to);
        }
        if (to.isEmpty()) {
            throw new UnknownTransitionException("To was zero.", from, to);
        }

        paneController.getChildren().clear();
        switch (to) {
            case MenuScreen.SCREEN_NAME:
                screen = menuScreen;
                menuScreen.update();
                break;
            case GameMainScreen.SCREEN_NAME:
                screen = mainScreen;
                mainScreen.update();
                break;
            case InteractScreen.SCREEN_NAME:
                screen = interactScreen;
                interactScreen.update();
                break;
            case InventoryScreen.SCREEN_NAME:
                screen = inventoryScreen;
                inventoryScreen.update();
                break;
            case RoomInventoryScreen.SCREEN_NAME:
                screen = roomInventoryScreen;
                roomInventoryScreen.update();
                break;
            default:
                throw new UnknownTransitionException("Screen transition was not successful", from, to);

        }
        paneController.getChildren().add(screen);
    }

}
