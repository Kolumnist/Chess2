package CandyCrush;

import de.hhn.it.devtools.apis.candycrush.*;


public class DemoCandyCrushUsage {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DemoCandyCrushUsage.class);

  public static void main(String[] args) throws NoProfileSelectedException, ProfileNotFoundException {
    CandyCrushService candyCrushService = null;
    Profile profile1 = null;


    CandyCrushService candycrush = null;
    Profile profile = new Profile("Spieler 1");
    logger.info("SPIELER 1 CREATED");
    GameBoard gameBoard = candycrush.createGame(profile, GameMode.CLASSIC);
    logger.info("GAME CREATED");
    candycrush.swapBlocks(1,2,6,3);
    logger.info("TAKES ROW1 AND COL1 AND PUT IT TO POSITION: ROW6 AND COL3");
    candycrush.createProfile(profile,GameMode.CLASSIC);
    candycrush.swapBlocks(5,7,1,9);
    logger.info("TAKES ROW5 AND COL7 AND PUT IT TO POSITION: ROW1 AND COL9");
    candycrush.setMode(GameMode.CLASSIC);
    logger.info("GAME MODE CREATED");
    candycrush.chooseProfile(profile);
    logger.info("PROFILE COLLECTED");
    candycrush.deleteProfile(profile);
    logger.info("PROFILE DELETED");
    candycrush.addCallBack();
    logger.info("INFORMATION SUCCESSFUL");
    candycrush.markBlock(1,5);
    logger.info("BLOCK ON ROW1 AND COL5 MARKED");
  }






}
