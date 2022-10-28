package de.hhn.it.devtools.apis.reactiongame;


import de.hhn.it.devtools.apis.ttrpgsheets.exceptions.IllegalParameterException;

public interface ReactiongameService {

  void newRun(Difficulty difficulty) throws IllegalParameterException;

  void endRun();

  void addCallback(int id, RunListener runListener) throws IllegalParameterException;

  void removeCallback(int id, RunListener runListener) throws IllegalParameterException;

  void startTimer();

  void pauseTimer();

  void endTimer();


}
