package de.hhn.it.devtools.components.connectfour.provider;

import de.hhn.it.devtools.apis.connectfour.ConnectFourListener;
import de.hhn.it.devtools.apis.connectfour.Difficulty;
import de.hhn.it.devtools.apis.connectfour.GameState;
import de.hhn.it.devtools.apis.connectfour.IllegalNameException;
import de.hhn.it.devtools.apis.connectfour.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.MatchState;
import de.hhn.it.devtools.apis.connectfour.Mode;
import de.hhn.it.devtools.apis.connectfour.Profile;
import de.hhn.it.devtools.apis.connectfour.ProfileNotFoundException;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * This class implements the component facade.
 */
public class ConnectFour implements de.hhn.it.devtools.apis.connectfour.ConnectFour {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ConnectFour.class);
  private HashMap<UUID, Profile> profiles = new HashMap<>();
  private Profile profileA;
  private Profile profileB;
  private static Profile computer;
  private GameState gameState;
  private MatchState matchState;
  private Mode mode;
  private List<ConnectFourListener> listeners;

  private final Game game = new Game(this);

  public boolean computerIsNext() {
    return game.getDiscs().getFirst().owner() == computer;
  }

  public void play() {
    logger.info("Computer is placing disc");
    int i = (int) (Math.random() * 6);
    try {
      placeDiscAt(i);
    } catch (IllegalOperationException e) {
      for (int j = 0; j < 6; j++) {
        try {
          placeDiscAt(j);
          break;
        } catch (IllegalOperationException e2) {
          logger.debug("Something went wrong...");
        }
      }
    }
  }

  /**
   * Create a new Connect Four game.
   */
  public ConnectFour() {
    // not in profiles
    try {
    } catch (Exception e) {
      logger.info("cannot load profiles");
    }
    if (profiles.size() == 0) {
      try {
        if (computer == null) {
          computer = new Profile("Computer");
        }
      } catch (IllegalNameException e) {
        logger.info("Something went wrong");
      }
      // in profiles
      /*try {
        createProfile("Alice");
      } catch (IllegalNameException e) {
        logger.info("Alice already exists...");
      }
      try {
        createProfile("Bob");
      } catch (IllegalNameException e) {
        logger.info("Bob already exists...");
      }*/
    }
  }

  /*
  Game methods
   */

  public Disc[][] getDiscs() {
    return game.getBoard();
  }

  public String getColor() {
    return game.getColor();
  }

  public String getText() {
    return game.getText();
  }

  public Profile getProfileA() {
    return profileA;
  }

  public Profile getProfileB() {
    return profileB;
  }

  public Profile getComputer() {
    return computer;
  }

  public Mode getMode() {
    return mode;
  }

  public void setMatchState(MatchState matchState) {
    this.matchState = matchState;
  }

  public MatchState getMatchState() {
    return matchState;
  }

  public void setGameState(GameState gameState) {
    this.gameState = gameState;
  }

  public GameState getGameState() {
    return gameState;
  }

  /*
  Interface Methods
   */

  @Override
  public void addCallback(ConnectFourListener listener)
      throws IllegalArgumentException, IllegalParameterException {
    logger.info("Adding callback...");
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }
    if (listeners.contains(listener)) {
      throw new IllegalParameterException("Listener already registered.");
    }
    listeners.add(listener);
  }

  @Override
  public void removeCallback(ConnectFourListener listener)
      throws IllegalArgumentException, IllegalParameterException {
    logger.info("Removing callback...");
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }
    if (!listeners.contains(listener)) {
      throw new IllegalParameterException("Listener is not registered:" + listener);
    }
    listeners.remove(listener);
  }

  @Override
  public Profile createProfile(String name) throws IllegalNameException, IllegalArgumentException {
    logger.info("Creating profile...");
    Profile profile = new Profile(name);
    profiles.put(profile.getId(), profile);
    return profile;
  }

  @Override
  public void setProfileName(UUID profileId, String name)
      throws ProfileNotFoundException, IllegalNameException, IllegalArgumentException {
    logger.info("Setting profile name of profile with ID " + profileId + " to " + name + "...");
    checkId(profileId);
    Profile profile = getProfile(profileId);
    profile.setName(name);
  }

  @Override
  public void deleteProfile(UUID profileId)
      throws ProfileNotFoundException, IllegalArgumentException {
    logger.info("Deleting profile profile with ID " + profileId + "...");
    checkId(profileId);
    profiles.remove(profileId);
  }

  @Override
  public Profile getProfile(UUID profileId) throws ProfileNotFoundException {
    checkId(profileId);
    logger.info("Getting profile profile with ID " + profileId + "...");
    return profiles.get(profileId);
  }

  @Override
  public List<Profile> getProfiles() {
    logger.info("Getting all profiles...");
    return new LinkedList<>(profiles.values());
  }

  @Override
  public void setMode(Mode mode) {
    logger.info("Setting mode to " + mode + "...");
    this.mode = mode;
  }

  @Override
  public void setDifficulty(Difficulty difficulty) {
    logger.info("Setting difficulty to " + difficulty + "...");
  }

  @Override
  public void chooseProfileA(Profile a) {
    logger.info("Choosing " + a + " as player 1...");
    profileA = a;
    game.addDisc(a, Color.RED);
  }

  @Override
  public void chooseProfileB(Profile b) {
    logger.info("Choosing " + b + " as player 2...");
    profileB = b;
    game.addDisc(b, Color.GREEN);
  }

  @Override
  public void startGame() {
    logger.info("Starting game...");
    gameState = GameState.STARTED;
    matchState = MatchState.PLAYER_A_IS_PLAYING;
  }

  @Override
  public void placeDiscAt(int column) throws IllegalOperationException {
    logger.info("Placing disk at column " + column + "...");
    game.placeDiscIn(column);
  }

  @Override
  public void quitGame() {
    logger.info("Quitting game...");
    gameState = GameState.CANCELED;
  }

  private void checkId(UUID id) throws ProfileNotFoundException {
    logger.info("Checking profile ID...");
    if (!profiles.containsKey(id)) {
      throw new ProfileNotFoundException("There is no profile with the specified ID");
    }
  }

}
