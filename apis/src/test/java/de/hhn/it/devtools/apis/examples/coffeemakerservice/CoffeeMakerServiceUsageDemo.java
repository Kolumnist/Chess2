package de.hhn.it.devtools.apis.examples.coffeemakerservice;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.List;

/**
 * This usage demo is not runnable because in this module there is no possibility to access the
 * implementation. The runnable demo is accessible in the components module.
 */
public class CoffeeMakerServiceUsageDemo {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(CoffeeMakerServiceUsageDemo.class);

  public static void main(String[] args) throws IllegalParameterException, InterruptedException {
    AdminCoffeeMakerService adminCoffeeMakerService = null;
    CoffeeMakerService coffeeMakerService = null;

    // register two coffee makers via admin interface
    CoffeeMakerDescriptor coffeeMakerDescriptor1 = new CoffeeMakerDescriptor("A317", "BrewWizard");
    adminCoffeeMakerService.addCoffeeMaker(coffeeMakerDescriptor1);
    CoffeeMakerDescriptor coffeeMakerDescriptor2 = new CoffeeMakerDescriptor("F141", "BrewGuru");
    adminCoffeeMakerService.addCoffeeMaker(coffeeMakerDescriptor2);

    // Now use the client interface
    List<CoffeeMakerDescriptor> makerDescriptors = coffeeMakerService.getCoffeeMakers();
    CoffeeMakerDescriptor descriptor0 = makerDescriptors.get(0);

    int makerId0 = descriptor0.getId();
    logger.debug("" + descriptor0);

    logger.info(">>> switch on");
    // Switch it on, start heating, get ready
    coffeeMakerService.switchOn(makerId0);
    descriptor0 = coffeeMakerService.getCoffeeMaker(makerId0);
    logger.info("" + descriptor0);

    Thread.sleep(3000);

    logger.info(">>> check again");
    descriptor0 = coffeeMakerService.getCoffeeMaker(makerId0);
    logger.info("" + descriptor0);

    Thread.sleep(1000);

    logger.info(">>> cleaning");
    // clean it before you brew something
    coffeeMakerService.cleanIt(makerId0);
    descriptor0 = coffeeMakerService.getCoffeeMaker(makerId0);
    logger.info("" + descriptor0);

    Thread.sleep(3000);

    descriptor0 = coffeeMakerService.getCoffeeMaker(makerId0);
    logger.debug("" + descriptor0);


    // now get some coffee, black, no milk, no sugar, no milk froth
    logger.debug(">>> brewing");
    Recipe recipe = new Recipe(Quantity.LARGE, Quantity.NONE, Quantity.NONE, Quantity.NONE);
    coffeeMakerService.brewCoffee(makerId0, recipe);

    descriptor0 = coffeeMakerService.getCoffeeMaker(makerId0);
    logger.debug("" + descriptor0);

    Thread.sleep(3000);

    descriptor0 = coffeeMakerService.getCoffeeMaker(makerId0);
    logger.debug("" + descriptor0);

    // switch it off
    logger.debug(">>> switch off");
    coffeeMakerService.switchOff(makerId0);
    descriptor0 = coffeeMakerService.getCoffeeMaker(makerId0);
    logger.debug("" + descriptor0);

    Thread.sleep(3000);

    descriptor0 = coffeeMakerService.getCoffeeMaker(makerId0);
    logger.debug("" + descriptor0);



  }

}
