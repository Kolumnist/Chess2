package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.Difficulty;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameListener;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameService;
import java.util.IllegalFormatException;
import java.util.SortedMap;

public class CmpReactionGameService implements ReactiongameService {

  @Override
  public void newRun(Difficulty difficulty) throws IllegalParameterException {

  }

  @Override
  public void pauseRun() throws IllegalStateException {

  }

  @Override
  public void continueRun() throws IllegalStateException {

  }

  @Override
  public void endRun() {

  }

  @Override
  public void addCallback(ReactiongameListener listener, int id) {

  }

  @Override
  public void removeCallback(int id) {

  }

  @Override
  public void presentCourserPosition(int x, int y) throws IllegalStateException {

  }

  @Override
  public void keyPressed(char key) throws IllegalStateException {

  }

  @Override
  public void setCurrentPlayerName(String playerName) {

  }

  @Override
  public void loadHighscoreTable(SortedMap<String, Integer> newHighScoreTable) {

  }

  @Override
  public SortedMap<String, Integer> saveHighscoreTable() throws IllegalFormatException {
    return null;
  }
}
