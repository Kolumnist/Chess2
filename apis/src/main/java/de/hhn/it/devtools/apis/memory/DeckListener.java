package de.hhn.it.devtools.apis.memory;

/**
 * Callback to notify about the current cards in the deck.
 */
public interface DeckListener {

  /**
   * Informs the listener about the cards in the current deck.
   *
   * @param deck the current deck of cards
   */
  public void currentDeck(PictureCardDescriptor[] deck);
}
