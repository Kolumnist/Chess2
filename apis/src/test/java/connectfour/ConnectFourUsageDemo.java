package connectfour;

import de.hhn.it.devtools.apis.connectfour.ConnectFour;
import de.hhn.it.devtools.apis.connectfour.Profile;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * This usage demo is not runnable because in this module there is no possibility to access the
 * implementation. The runnable demo will be accessible in the components-module.
 */


public class ConnectFourUsageDemo {

  public static void main(String[] args) throws IllegalParameterException, InterruptedException {
    ConnectFour connectFour = null;

    //create three profiles
    Profile user1 = connectFour.createProfile("User1");
    Profile user2 = connectFour.createProfile("User2");
    Profile user3 = connectFour.createProfile("User3");
  }
}
