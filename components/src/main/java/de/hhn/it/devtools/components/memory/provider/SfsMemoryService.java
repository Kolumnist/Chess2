package de.hhn.it.devtools.components.memory.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of MemoryService interface.
 */
public class SfsMemoryService implements MemoryService {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SfsMemoryService.class);

  private Map<Integer, PictureCard> cards;
  private Map<Integer, String> pictureReferences;
  private PictureCardDescriptor[] deck;

  private DeckListener deckListener;

  private List<CardSet> cardSetStorage = new ArrayList<>();

  private SfsTimer timer;

  public SfsMemoryService() {
    cards = new HashMap<>();
    pictureReferences = new HashMap<>();
    timer = new SfsTimer(new TimerDescriptor());
  }

  public PictureCard getPictureCardById(int id) throws IllegalParameterException {
    logger.info("getPictureCardById: id = {}", id);
    if (!cards.containsKey(id)) {
      throw new IllegalParameterException("PictureCard with id " + id + " does not exist.");
    }
    return cards.get(id);
  }

  @Override
  public void newGame(Difficulty difficulty) throws IllegalParameterException {
    logger.info("newGame: difficulty = {}", difficulty);
    if (difficulty == null) {
      throw new IllegalParameterException("Difficulty does not exist.");
    }
    for (CardSet c : cardSetStorage) {
      if (c.getDifficulty() == difficulty) {
        ArrayList<PictureCardDescriptor> descriptorsArrayList =
                (ArrayList<PictureCardDescriptor>) c.getPictureCardDescriptors();
        PictureCardDescriptor[] descriptorsArray =
                new PictureCardDescriptor[descriptorsArrayList.size()];
        deck = descriptorsArrayList.toArray(descriptorsArray);
        fetchCards(descriptorsArray);
        fetchPicReferences(c.getPictureReferences());
        notifyCurrentDeck();
      }
    }
  }

  @Override
  public void startTimer() {
    logger.info("startTimer: no params");
    timer.startTime();
  }

  @Override
  public void stopTimer() {
    logger.info("stopTimer: no params");
    timer.getTimer().stopTime();
  }

  @Override
  public void resetTimer() {
    logger.info("resetTimer: no params");
    timer.getTimer().resetTime();
  }

  @Override
  public void closeGame() {
    logger.info("closeGame: no params");
    cards.clear();
    pictureReferences.clear();
    deck = null;
    SfsPictureCard.resetIdCounter();
  }

  @Override
  public void changeDifficulty(Difficulty difficulty) throws IllegalParameterException {
    logger.info("changeDifficulty: difficulty = {}", difficulty);
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
  public void addCallback(TimerListener listener) throws IllegalParameterException {
    logger.info("addCallback: listener = {}", listener);
    timer.addCallback(listener);
  }

  @Override
  public void addCallback(DeckListener listener) throws IllegalParameterException {
    logger.info("addCallback: listener = {}", listener);
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }
    if (this.deckListener == listener) {
      throw new IllegalParameterException("Listener already registered.");
    }
    this.deckListener = listener;
  }

  @Override
  public void removeCallback(int id, PictureCardListener listener)
          throws IllegalParameterException {
    logger.info("removeCallback: id = {}, listener = {}", id, listener);
    PictureCard card = getPictureCardById(id);
    card.removeCallback(listener);
  }

  @Override
  public void removeCallback(TimerListener listener) throws IllegalParameterException {
    logger.info("removeCallback: listener = {}", listener);
    timer.removeCallback(listener);
  }

  @Override
  public void removeCallback(DeckListener listener) throws IllegalParameterException {
    logger.info("removeCallback: listener = {}", listener);
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }
    if (deckListener != listener) {
      throw new IllegalParameterException("Listener is not registered.");
    }
    this.deckListener = null;
  }

  @Override
  public List<PictureCardDescriptor> getPictureCards() {
    logger.info("getPictureCards: no params");
    List<PictureCardDescriptor> descriptors = new ArrayList<>();
    cards.values().forEach((pictureCard -> descriptors.add(pictureCard.getPictureCard())));
    return descriptors;
  }

  @Override
  public PictureCardDescriptor getPictureCard(int id) throws IllegalParameterException {
    logger.info("getPictureCard: id = {}", id);
    PictureCard card = getPictureCardById(id);
    return card.getPictureCard();
  }

  @Override
  public void turnCard(int id) throws IllegalParameterException {
    logger.info("turnCard: id = {}", id);
    if (!cards.containsKey(id)) {
      throw new IllegalParameterException("PictureCard with id " + id + " does not exist.");
    }
    for (PictureCard c : cards.values()) {
      if (c.getPictureCard().getState().equals(State.VISIBLE)) {
        if (c.getPictureCard().getId() == id) {
          throw new IllegalParameterException("Card is already visible.");
        }
        PictureCard card = getPictureCardById(id);
        card.turnCard();

        if (c.getPictureCard().getPictureRef() == -1 && card.getPictureCard().getPictureRef() == -1
                || c.getPictureCard().getName() == null
                && card.getPictureCard().getName() == null) {
          c.turnCard();
          card.turnCard();
        } else {
          if (c.getPictureCard().getPictureRef() == -1) {
            if (matchCards(card, c)) {
              c.matchCard();
              card.matchCard();
            } else {
              c.turnCard();
              card.turnCard();
            }
          } else if (card.getPictureCard().getPictureRef() == -1) {
            if (matchCards(c, card)) {
              c.matchCard();
              card.matchCard();
            } else {
              c.turnCard();
              card.turnCard();
            }
          }
        }
        return;
      }
    }
    getPictureCardById(id).turnCard();
  }

  public void addCardSet(CardSet set) throws IllegalParameterException {
    logger.info("addCardSet: set = {}", set);
    if (set == null) {
      throw new IllegalParameterException("CardSet is a null references");
    }
    if (cardSetStorage.contains(set)) {
      throw new IllegalParameterException("CardSet already registered.");
    }
    cardSetStorage.add(set);
  }

  /**
   * Calls to signal the current deck update.
   */
  public void notifyCurrentDeck() {
    logger.info("notifyCurrentDeck: no params");
    deckListener.currentDeck(deck);
  }


  public void fetchCards(PictureCardDescriptor[] cardDescriptors) throws IllegalParameterException {
    //logger.info("fetchCards: cardDescriptors = {}", cardDescriptors);
    if (cardDescriptors == null) {
      throw new IllegalParameterException("The CardDescriptor Array is a null references.");
    }
    SfsPictureCard.resetIdCounter();
    for (PictureCardDescriptor c : cardDescriptors) {
      PictureCard pictureCard = new SfsPictureCard(c);
      cards.put(pictureCard.getPictureCard().getId(), pictureCard);
    }
  }

  public void fetchPicReferences(HashMap<Integer, String> picReferences)
          throws IllegalParameterException {
    logger.info("fetchPicReferences: picReferences = {}", picReferences);
    if (picReferences == null) {
      throw new IllegalParameterException("The HashMap of PictureReferences is a null reference.");
    }
    pictureReferences = (Map<Integer, String>) picReferences.clone();
  }

  public boolean matchCards(PictureCard picture, PictureCard name)
          throws IllegalParameterException {
    logger.info("matchCards: picture = {}, name = {}", picture, name);
    if (picture == null || name == null) {
      throw new IllegalParameterException("At least one of the cards is a null references.");
    }
    if (pictureReferences.containsKey(picture.getPictureCard().getPictureRef())
            || name.getPictureCard().getName() != null) {
      String picCard = pictureReferences.get(picture.getPictureCard()
              .getPictureRef()).toLowerCase();
      return (name.getPictureCard().getName().toLowerCase().equals(picCard));
    } else {
      throw new IllegalParameterException("PictureCard has no appropriate picture "
              + "reference or name is a null reference.");
    }
  }

  public List<CardSet> getCardSetStorage() {
    return cardSetStorage;
  }

  public PictureCardDescriptor[] getDeck() {
    return deck;
  }

  public Map<Integer, String> getPictureReferences() {
    return pictureReferences;
  }
}
