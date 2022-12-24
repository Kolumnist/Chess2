package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.*;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.SortedMap;

/**
 * Realisation of ReactionGameService.
 */
public class CmpReactionGameService implements ReactiongameService {

  private Game game;
  private ArrayList<ReactiongameListener> callbacks;



  public ArrayList<ReactiongameListener> getCallbacks() {
    return callbacks;
  }

  public void setCallbacks(ArrayList<ReactiongameListener> callbacks) {
    this.callbacks = callbacks;
  }

  @Override
  public void newRun(Difficulty difficulty) throws IllegalParameterException {
    game = new Game(this, difficulty);

    for (ReactiongameListener l:
        callbacks) {
      // ?
    }
  }

  @Override
  public void pauseRun() throws IllegalStateException {
    if (game.getState() != GameState.RUNNING) {
      throw new IllegalStateException();
    }

    for (ReactiongameListener l:
         callbacks) {
      l.pauseRun();
    }

    game.setState(GameState.PAUSED);
  }

  @Override
  public void continueRun() throws IllegalStateException {
    if (game.getState() != GameState.PAUSED) {
      throw new IllegalStateException();
    }

    for (ReactiongameListener l:
            callbacks) {
      l.continueRun();
    }

    game.setState(GameState.PAUSED);
  }

  @Override
  public void endRun() {
    if (game.getState() == GameState.FINISHED) {
      throw new IllegalStateException();
    }

    for (ReactiongameListener l:
            callbacks) {
      l.gameOver();
    }


    game.setState(GameState.PAUSED);
  }

  @Override
  public void addCallback(int id, ReactiongameListener listener) {
    callbacks.add(id, listener);
  }

  @Override
  public void removeCallback(int id) {
    callbacks.remove(id);
  }

  @Override
  public void keyPressed(char key) throws IllegalStateException {

  }

  @Override
  public void setCurrentPlayerName(String playerName) {
    game.getPlayer().setName(playerName);
  }

  @Override
  public void loadHighscoreTable(SortedMap<String, Integer> newHighScoreTable) {

  }

  @Override
  public SortedMap<String, Integer> saveHighscoreTable() throws IllegalFormatException {
    return null;
  }


  public void addObstacle(Obstacle obstacle) {
    ObstacleDescriptor obstacleDescriptor = new ObstacleDescriptor(obstacle.getX1(), obstacle.getY1(),
            obstacle.getX2(), obstacle.getY2());

    for (ReactiongameListener l :
            callbacks) {
      l.addObstacle(obstacleDescriptor);
    }
  }

  public void removeObstacle(int id) {

    for (ReactiongameListener l :
            callbacks) {
      l.removeObstacle(id);
    }
  }

}
