package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.CardSetDescriptor;
import de.hhn.it.devtools.apis.memory.DeckListener;
import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.apis.memory.MemoryService;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import de.hhn.it.devtools.components.memory.provider.SfsMemoryService;
import de.hhn.it.devtools.javafx.controllers.memory.MemoryAttributeStore;
import de.hhn.it.devtools.javafx.controllers.memory.MemoryScreenController;
import de.hhn.it.devtools.javafx.controllers.memory.StartScreenController;
import de.hhn.it.devtools.javafx.controllers.template.UnknownTransitionException;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * Memory Service Main Controller.
 */
public class MemoryServiceController extends Controller implements Initializable, DeckListener {
  public static final String SCREEN_CONTROLLER = "screen.controller";
  public static final String MEMORY_SERVICE = "memory.service";
  public static final String DIFFICULTY = "current.difficulty";
  public static final String PATH_REFERENCES = "path.references";
  public static final String CURRENT_DECK = "current.deck";
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(MemoryServiceController.class);
  @FXML
  AnchorPane backgroundAnchorPane;
  MemoryScreenController screenController;
  MemoryService memoryService;
  HashMap<Integer, String> pathReferences;

  public MemoryServiceController() {
    logger.debug("MemoryService Controller created.");
  }

  /**
   * Called to initialize a controller after its root element has been
   * completely processed.
   *
   * @param location  The location used to resolve relative paths for the root object, or
   *                  <code>null</code> if the location is not known.
   * @param resources The resources used to localize the root object, or <code>null</code> if
   *                  unknown.
   */
  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    screenController = new MemoryScreenController(backgroundAnchorPane);
    MemoryAttributeStore memoryAttributeStore = MemoryAttributeStore.getReference();
    memoryAttributeStore.setAttribute(SCREEN_CONTROLLER, screenController);
    memoryService = new SfsMemoryService();
    memoryAttributeStore.setAttribute(MEMORY_SERVICE, memoryService);
    memoryAttributeStore.setAttribute(DIFFICULTY, Difficulty.EASY);
    pathReferences = new HashMap<>();
    memoryAttributeStore.setAttribute(PATH_REFERENCES, pathReferences);
    try {
      memoryService.addCallback(this);
    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }
    try {
      memoryService.addCardSet(fetchCardSets("easy"));
      memoryService.addCardSet(fetchCardSets("medium"));
      memoryService.addCardSet(fetchCardSets("hard"));
      memoryService.addCardSet(fetchCardSets("veryhard"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      screenController.switchTo(StartScreenController.SCREEN);
    } catch (UnknownTransitionException e) {
      e.printStackTrace();
    }
  }

  /**
   * Loads the card set for the chosen difficulty from files.
   *
   * @param difficulty the chosen difficulty
   * @return CardSetDescriptor of the chosen difficulty
   */
  public CardSetDescriptor fetchCardSets(String difficulty) {
    //File folder = new File(Objects.requireNonNull(getClass().getClassLoader()
    //  .getResource("/fxml/memory/pictures/sets/" + difficulty + "/")).getFile());
    File folder = new File("javafx/src/main/resources/fxml/memory/pictures/sets/" + difficulty);
    HashMap<Integer, String> pictureReferences = new HashMap<>();
    PictureCardDescriptor[] pictureCardDescriptors = new PictureCardDescriptor[5 * 4];
    int cnt = 0;
    int i;
    int j;
    File f;
    ArrayList<Integer> occupied = new ArrayList<>();
    //Randomizes Cards
    do {
      do {
        j = getRandomNumber(Objects.requireNonNull(folder.listFiles()).length);
      } while (occupied.contains(j));
      occupied.add(j);
      f = Objects.requireNonNull(folder.listFiles())[j];
      do {
        i = new Random().nextInt();
      } while (i < 0 || pathReferences.containsKey(i) || pictureReferences.containsKey(i));
      pictureCardDescriptors[cnt++] = new PictureCardDescriptor(i, null); //PictureCard
      pictureCardDescriptors[cnt++] = new PictureCardDescriptor(-1, f.getName()
          .replace(".png", "").replace(".jpg", "")); //NameCard
      pathReferences.put(i, f.getAbsolutePath());
      pictureReferences.put(i, f.getName().replace(".png", "").replace(".jpg", ""));
    } while (pictureCardDescriptors.length != cnt);
    return new CardSetDescriptor(screenController
            .getDifficultyFromString(difficulty), pictureCardDescriptors, pictureReferences);
  }

  /**
   * Creates random number.
   *
   * @param n upper limit for random number
   * @return int random number between 0 and n
   */
  private int getRandomNumber(int n) {
    Random random = new Random();
    return random.nextInt(n);
  }

  @Override
  public void currentDeck(PictureCardDescriptor[] deck) {
    MemoryAttributeStore.getReference().setAttribute(CURRENT_DECK, deck);
  }
}
