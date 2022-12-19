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
  private SfsCardSet currentCardSet;

  private DeckListener deckListener;

  private List<SfsCardSet> cardSetStorage = new ArrayList<>();


  private SfsTimer timer;

  public SfsMemoryService() {
    cards = new HashMap<>();
    timer = new SfsTimer(new TimerDescriptor());
  }

  @Override
  public void newGame(Difficulty difficulty) throws IllegalParameterException {
    logger.info("newGame: difficulty = {}", difficulty);
    if (difficulty == null) {
      throw new IllegalParameterException("Difficulty does not exist.");
    }
    for (SfsCardSet c : cardSetStorage) {
      if (c.getDescriptor().getDifficulty() == difficulty) {
        currentCardSet = c;
        fetchCards(c.getDescriptor().getPictureCardDescriptors());
        notifyCurrentDeck();
        return;
      }
    }
    throw new IllegalParameterException("There is no cardSet for this difficulty.");
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
    currentCardSet = null;
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
  public void removeCallback(int id, PictureCardListener listener) throws IllegalParameterException {
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
  public List<PictureCardDescriptor> getPictureCardDescriptors() {
    logger.info("getPictureCards: no params");
    List<PictureCardDescriptor> descriptors = new ArrayList<>();
    cards.values().forEach((pictureCard -> descriptors.add(pictureCard.getPictureCard())));
    return descriptors;
  }

  @Override
  public PictureCardDescriptor getPictureCardDescriptor(int id) throws IllegalParameterException {
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
    if (cards.get(id).getPictureCard().getState().equals(State.VISIBLE)) {
      throw new IllegalParameterException("Card is already visible.");
    }
    //checks if another card is already visible
    for (PictureCard c : cards.values()) {
      if (c.getPictureCard().getState().equals(State.VISIBLE)) {
        PictureCard card = getPictureCardById(id);
        card.turnCard();
        checkCards(c, card);
        return;
      }
    }
    //if no other card is visible
    getPictureCardById(id).turnCard();
  }

  @Override
  public void addCardSet(CardSetDescriptor set) throws IllegalParameterException {
    logger.info("addCardSet: set = {}", set);
    if (set == null) {
      throw new IllegalParameterException("CardSet is a null references");
    }
    if (cardSetStorage.contains(set)) {
      throw new IllegalParameterException("CardSet already registered.");
    }
    SfsCardSet cardSet = new SfsCardSet(set);
    cardSetStorage.add(cardSet);
  }

  /**
   * Returns the Picture Card for the given ID.
   *
   * @param id of the Picture Card wanted
   * @return Picture Card with the ID
   * @throws IllegalParameterException if the ID does not exist
   */
  public PictureCard getPictureCardById(int id) throws IllegalParameterException {
    logger.info("getPictureCardById: id = {}", id);
    if(id < 0) {
      throw new IllegalParameterException("PictureCard id cannot be negative.");
    }
    if (!cards.containsKey(id)) {
      throw new IllegalParameterException("PictureCard with id " + id + " does not exist.");
    }
    return cards.get(id);
  }

  /**
   * Returns the current list of card sets in the service.
   *
   * @return list of cardSets in the service
   */
  public List<SfsCardSet> getCardSetStorage() {
    return cardSetStorage;
  }

  /**
   * Returns the current card set of the service.
   *
   * @return current card set of the service
   */
  public SfsCardSet getCurrentCardSet() {
    return currentCardSet;
  }

  /**
   * Calls to signal the current deck update.
   */
  private void notifyCurrentDeck() {
    logger.info("notifyCurrentDeck: no params");
    deckListener.currentDeck(currentCardSet.getDescriptor().getPictureCardDescriptors());
  }

  /**
   * Fetches the cards to the service.
   *
   * @param cardDescriptors for the cards
   * @throws IllegalParameterException if the card descriptors do not exist
   */
  private void fetchCards(PictureCardDescriptor[] cardDescriptors) throws IllegalParameterException {
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


  /**
   * Checks if cards should be set to a match or should be turned back.
   *
   * @param a the first card
   * @param b the second card
   * @return true, if the cards are matched
   * @throws IllegalParameterException if one of the cards does not exist
   */
  private boolean checkCards(PictureCard a, PictureCard b) throws IllegalParameterException {
    if(a == null || b == null) {
      throw new IllegalParameterException("At least one of the cards is a null references");
    }
    if (checkForDifferentType(a, b) && checkOrderForMatch(a, b)) {
      a.matchCard();
      b.matchCard();
      return true;
    }
    a.turnCard();
    b.turnCard();
    return false;
  }


  /**
   * Checks if two cards are a match.
   *
   * @param picture the picture card
   * @param name the name card
   * @return true, if the cards are a match
   * @throws IllegalParameterException if at least one picture card does not exist
   */
  private boolean checkForMatch(PictureCard picture, PictureCard name) throws IllegalParameterException {
    logger.info("matchCards: picture = {}, name = {}", picture, name);
    if (picture == null || name == null) {
      throw new IllegalParameterException("At least one of the cards is a null references.");
    }
    if (currentCardSet.getDescriptor().getPictureReferences().containsKey(picture.getPictureCard().getPictureRef())
            || name.getPictureCard().getName() != null) {
      String picCard = currentCardSet.getDescriptor().getPictureReferences().get(picture.getPictureCard()
              .getPictureRef()).toLowerCase();
      return (name.getPictureCard().getName().toLowerCase().equals(picCard));
    } else {
      throw new IllegalParameterException("PictureCard has no appropriate picture "
              + "reference or name is a null reference.");
    }
  }

  /**
   * Checks if two cards are from the same type (picture card or name card).
   *
   * @param a the first card
   * @param b the second card
   * @return true, if the card have different types
   */
  private boolean checkForDifferentType(PictureCard a, PictureCard b) {
    if(a.getPictureCard().getPictureRef() != -1) {
      if(b.getPictureCard().getPictureRef() != -1) {
        return false;
      } else {
        return true;
      }
    } else if(a.getPictureCard().getName() != null) {
      if(b.getPictureCard().getName() != null) {
        return false;
      } else {
        return true;
      }
    } else {
      return false;
    }
  }

  /**
   * Checks which card is the picture card and therefor order of check for match.
   *
   * @param a the first card
   * @param b the second card
   * @return true, if the cards are a match
   * @throws IllegalParameterException if at least one picture card does not exist
   */
  private boolean checkOrderForMatch(PictureCard a, PictureCard b) throws IllegalParameterException {
    if(a.getPictureCard().getPictureRef() == -1) {
      return checkForMatch(b,a);
    } else if(b.getPictureCard().getPictureRef() == -1) {
      return checkForMatch(a,b);
    } else {
      return false;
    }
  }

}
