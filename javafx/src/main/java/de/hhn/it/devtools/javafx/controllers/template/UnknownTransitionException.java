package de.hhn.it.devtools.javafx.controllers.template;

public class UnknownTransitionException extends RuntimeException {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(UnknownTransitionException.class);

  public UnknownTransitionException(final String message) {
    super(message);
  }
}
