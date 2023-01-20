package de.hhn.it.devtools.javafx.battleship;

import de.hhn.it.devtools.apis.battleship.BattleshipListener;
import de.hhn.it.devtools.apis.battleship.BattleshipService;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application {
    public static Stage mainStage = new Stage();
    GameMenu gameMenu;
    static String BATTLESHIP_SERVICE = "Battleship.service";
    static String BATTLESHIPS_SHIPSLEFT = "Battleship.ShipsLeft";
    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
    CmpBattleshipService service;
    ShipsLeft shipsLeft;

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        service = new CmpBattleshipService();
        singletonAttributeStore.setAttribute(BATTLESHIP_SERVICE,service);
        //singletonAttributeStore.setAttribute(BATTLESHIPS_SHIPS LEFT, );
        gameMenu = new GameMenu();

    }



}
