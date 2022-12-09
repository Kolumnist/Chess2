package de.hhn.it.devtools.apis.wordle;

public interface WordleGuessService {

  public WordlePanelService[] getWordleWord();

  public void setWordleWord(WordlePanelService[] wordleWord);

  public void setLetterAtIndex(int index, char letter);

  public char getLetterAtIndex(int index);


  public void deleteLetterAtIndex(int index);

  public String getWordleGuessAsString();

}
