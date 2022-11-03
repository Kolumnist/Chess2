package de.hhn.it.devtools.components.memory.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.apis.memory.MemoryService;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import de.hhn.it.devtools.apis.memory.PictureCardListener;

import java.util.List;

/**
 * Implementation of MemoryService interface.
 */
public class SfsMemoryService implements MemoryService {

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

    }

    @Override
    public void addCallback(int id, PictureCardListener listener) throws IllegalParameterException {

    }

    @Override
    public void removeCallback(int id, PictureCardListener listener) throws IllegalParameterException {

    }

    @Override
    public List<PictureCardDescriptor> getPictureCards() {
        return null;
    }

    @Override
    public PictureCardDescriptor getPictureCard(int id) throws IllegalParameterException {
        return null;
    }

    @Override
    public void turnCard(int id) throws IllegalParameterException {

    }
}
