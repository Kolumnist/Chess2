package de.hhn.it.devtools.components.helper;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * Helper class to check simple things.
 */
public class CheckingHelper {

  /**
   * Checks if the string parameter has some readable string within.
   *
   * @param string          string to be checked
   * @param nameInException name of the parameter to be shown in the exception
   * @throws IllegalParameterException if the string to be checked is a null reference or
   *                                   contains no or only whitespace chars
   */
  public static void assertThatIsReadableString(String string, String nameInException) throws
          IllegalParameterException {
    if (string == null) {
      throw new IllegalParameterException("String for " + nameInException + " is null reference.");
    }

    if (string.trim().length() == 0) {
      throw new IllegalParameterException("String  " + nameInException + " consists only of "
              + "whitespace.");

    }
  }

  /**
   * Checks if the given object reference is a null reference.
   *
   * @param object          reference to be checked
   * @param nameInException name of the object within an exception
   * @throws IllegalParameterException if the object is a null reference
   */
  public static void assertThatIsNotNull(Object object, String nameInException) throws
          IllegalParameterException {
    if (object == null) {
      throw new IllegalParameterException("Reference for " + nameInException + " is null "
              + "reference.");
    }
  }

}
