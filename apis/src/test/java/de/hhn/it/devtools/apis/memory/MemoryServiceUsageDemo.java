package de.hhn.it.devtools.apis.memory;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.List;

/**
 * This is a usage demo for the MemoryService. It is not runnable in this module.
 */
public class MemoryServiceUsageDemo {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(MemoryServiceUsageDemo.class);

    public static void main(String[] args) throws IllegalParameterException {
        MemoryService memoryService = null;

        //register two picture cards
        PictureCardDescriptor pictureCardDescriptor1 = new PictureCardDescriptor(1, 1, "x");
        PictureCardDescriptor pictureCardDescriptor2 = new PictureCardDescriptor(0, 2, "Mario");

        //use the client interface
        List<PictureCardDescriptor> cardDescriptors = memoryService.getPictureCards();
        PictureCardDescriptor descriptor0 = cardDescriptors.get(0);

        int cardId0 = descriptor0.getId();
        String cardName0 = descriptor0.getName();
        int cardPictureRef0 = descriptor0.getPictureRef();
        logger.debug("" + descriptor0);

        //turn a card
        memoryService.turnCard(descriptor0);
        logger.info("turned around " + descriptor0);

        //create new game
        memoryService.newGame(Difficulty.EASY);
        logger.info("created EASY game");

        //start the timer
        memoryService.startTimer();
        logger.info("timer started");

        //change the difficulty
        memoryService.changeDifficulty(Difficulty.MEDIUM);
        logger.info("changed the difficulty to MEDIUM");

        //close the game
        memoryService.closeGame();
        logger.info("closed the game");

    }
}
