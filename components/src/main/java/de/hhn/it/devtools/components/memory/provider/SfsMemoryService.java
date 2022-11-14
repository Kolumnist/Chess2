package de.hhn.it.devtools.components.memory.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.*;

import java.util.*;

/**
 * Implementation of MemoryService interface.
 */
public class SfsMemoryService implements MemoryService {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SfsMemoryService.class);

  private Map<Integer, PictureCard> cards;
  private Map<Integer, String> pictureReferences;

  private List<TimerListener> listeners;

  public SfsMemoryService() {
    cards = new HashMap<>();
    pictureReferences = new HashMap<>();
  }

  private PictureCard getPictureCardById(int id) throws IllegalParameterException {
    logger.info("getPictureCardById: id = {}", id);
    if (!cards.containsKey(id)) {
      throw new IllegalParameterException("PictureCard with id " + id + " does not exist.");
    }
    return cards.get(id);
  }

  @Override
  public void newGame(Difficulty difficulty) throws IllegalParameterException {

  }

  @Override
  public void startTimer() {

  }

  @Override
  public void closeGame() {

  }

  @Override
  public void changeDifficulty(Difficulty difficulty) throws IllegalParameterException {
    closeGame();
    newGame(difficulty);
  }

  @Override
  public void addCallback(int id, PictureCardListener listener) throws IllegalParameterException {
    logger.info("addCallback: id = {}, listener = {}", id, listener);
    PictureCard card = getPictureCardById(id);
    card.addCallback(listener);

  }

  @Override
  public void removeCallback(int id, PictureCardListener listener) throws IllegalParameterException {
    logger.info("removeCallback: id = {}, listener = {}");
    PictureCard card = getPictureCardById(id);
    card.removeCallback(listener);
  }

  @Override
  public void addCallback(TimerListener listener) throws IllegalParameterException {
    logger.info("addCallback: listener = {}", listener);
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }

    if (listeners.contains(listener)) {
      throw new IllegalParameterException("Listener already registered.");
    }

    listeners.add(listener);
  }

  @Override
  public void removeCallback(TimerListener listener) throws IllegalParameterException {
    logger.info("removeCallback: listener = {}");
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }

    if (!listeners.contains(listener)) {
      throw new IllegalParameterException("Listener is not registered:" + listener);
    }

    listeners.remove(listener);
  }

  @Override
  public List<PictureCardDescriptor> getPictureCards() {
    List<PictureCardDescriptor> descriptors = new ArrayList<>();
    cards.values().forEach((pictureCard -> descriptors.add(pictureCard.getPictureCard())));
    return descriptors;
  }

  @Override
  public PictureCardDescriptor getPictureCard(int id) throws IllegalParameterException {
    PictureCard card = getPictureCardById(id);
    return card.getPictureCard();
  }

  @Override
  public void turnCard(int id) throws IllegalParameterException {
    logger.info("turnCard: id = {}", id);
    for (PictureCard c : cards.values()) {
      if (c.getPictureCard().getState().equals(State.VISIBLE)) {
        if (c.getPictureCard().getId() == id) {
          throw new IllegalParameterException("Card is already visible.");
        }
        PictureCard card = getPictureCardById(id);
        card.turnCard();

        if (c.getPictureCard().getPictureRef() == -1 && card.getPictureCard().getPictureRef() == -1
                || c.getPictureCard().getName().equals("null") && card.getPictureCard().getName().equals("null")) {
          c.turnCard();
          card.turnCard();
        } else {
          if (c.getPictureCard().getPictureRef() == -1) {
            if (!matchCards(card, c)) {
              card.turnCard();
              c.turnCard();
            }
          } else if (card.getPictureCard().getPictureRef() == -1) {
            if (!matchCards(c, card)) {
              card.turnCard();
              c.turnCard();
            }
          }
        }
      }
    }
  }

  private boolean matchCards(PictureCard picture, PictureCard name) throws IllegalParameterException {
    if(pictureReferences.containsKey(picture.getPictureCard().getPictureRef())) {
      String picCard = pictureReferences.get(picture.getPictureCard().getPictureRef()).toLowerCase();
      return name.getPictureCard().getName().toLowerCase().equals(picCard);
    } else {
      throw new IllegalParameterException("PictureCard has no appropriate picture reference.") ;
    }
  }
}
