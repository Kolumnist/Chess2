package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.CardSetDescriptor;
import de.hhn.it.devtools.apis.memory.DeckListener;
import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import de.hhn.it.devtools.components.memory.provider.SfsMemoryService;
import de.hhn.it.devtools.javafx.controllers.memory.MemoryAttributeStore;
import de.hhn.it.devtools.javafx.controllers.memory.MemoryScreenController;
import de.hhn.it.devtools.javafx.controllers.memory.StartScreen;
import de.hhn.it.devtools.javafx.controllers.template.UnknownTransitionException;
import java.io.File;
import java.net.URL;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


public class MemoryServiceController extends Controller implements Initializable {
  public static final String SCREEN_CONTROLLER = "screen.controller";
  public static final String MEMORY_SERVICE = "memory.service";
  public static final String DIFFICULTY = "current.difficulty";
  public static final String PATH_REFERENCES = "path.references";
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(MemoryServiceController.class);
  @FXML
  AnchorPane backgroundAnchorPane;
  MemoryScreenController screenController;
  SfsMemoryService memoryService;
  HashMap<Integer, String> pathReferences;

  public MemoryServiceController() {
    logger.debug("MemoryService Controller created.");
  }

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
      memoryService.addCallback((DeckListener) deck -> {
      });
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
      screenController.switchTo(StartScreen.SCREEN);
    } catch (UnknownTransitionException e) {
      e.printStackTrace();
    }
  }

  public CardSetDescriptor fetchCardSets(String difficulty) {
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
      pictureCardDescriptors[cnt++] = new PictureCardDescriptor(-1, f.getName()); //NameCard
      pathReferences.put(i, f.getAbsolutePath());
      pictureReferences.put(i, f.getName());
    } while (pictureCardDescriptors.length != cnt);
    return new CardSetDescriptor(screenController
            .getDifficultyFromString(difficulty), pictureCardDescriptors, pictureReferences);
  }

  private int getRandomNumber(int n) {
    Random random = new Random();
    return random.nextInt(n);
  }
}
