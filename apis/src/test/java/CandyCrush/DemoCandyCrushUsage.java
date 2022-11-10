package CandyCrush;

import de.hhn.it.devtools.apis.candycrush.CandyCrush;
import de.hhn.it.devtools.apis.candycrush.NoProfileSelectedException;
import de.hhn.it.devtools.apis.candycrush.Profile;


public class DemoCandyCrushUsage {

    private static final org.slf4j.Logger logger =
        org.slf4j.LoggerFactory.getLogger(DemoCandyCrushUsage.class);

    public static void main(String[] args) throws NoProfileSelectedException {

        CandyCrush candycrush= null;
        candycrush.createProfile("Player 1");
        candycrush.chooseProfile(new Profile("hallo"));
        candycrush.startNewGame();

    }


}
