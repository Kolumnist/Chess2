package de.hhn.it.devtools.components.example.coffeemakerservice.provider.makerstates;

/**
 * Contains typical exceptions to be thrown in different situations of the coffee maker logic.
 */
public class MessageHelper {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(MessageHelper.class);

  public static final IllegalStateException alreadySwitchedOn =
          new IllegalStateException("Maker is already switched on.");
  public static final IllegalStateException stillHeating =
          new IllegalStateException("Maker is still heating ");
  public static final IllegalStateException errorState =
          new IllegalStateException("System error. Please fix me. "
                  + "Call the magic coffee maker operator.");
  public static final IllegalStateException stillBrewing =
          new IllegalStateException("Please wait until brewing has ended.");
  public static final IllegalStateException alreadyBrewing =
          new IllegalStateException("I am already brewing.");
  public static final IllegalStateException alreadySwitchedOff =
          new IllegalStateException("Maker is already switched of.");
  public static final IllegalStateException switchOnFirst =
          new IllegalStateException("Please switch maker on first.");
  public static final IllegalStateException stillCleaning =
          new IllegalStateException("Maker is still cleaning");
  public static final IllegalStateException alreadyCleaning =
          new IllegalStateException("Maker is already cleaning");




}
