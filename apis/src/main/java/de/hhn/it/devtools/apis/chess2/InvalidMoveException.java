package de.hhn.it.devtools.apis.chess2;

/**
 * Gets thrown when a player makes a move that is not possible.
 *
 * @author Collin, Lara, Michel
 * @version 1.0
 */
public class InvalidMoveException extends Exception {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(InvalidMoveException.class);

  /**
   * Constructs a new exception with {@code null} as its detail message. The cause is not
   * initialized, and may subsequently be initialized by a call to {@link #initCause}.
   */
  public InvalidMoveException() {
  }

  /**
   * Constructs a new exception with the specified detail message.  The cause is not initialized,
   * and may subsequently be initialized by a call to {@link #initCause}.
   *
   * @param message the detail message. The detail message is saved for later retrieval by the
   *                {@link #getMessage()} method.
   */
  public InvalidMoveException(final String message) {
    super(message);
  }

  /**
   * Constructs a new exception with the specified cause and a detail message of <code>(cause==null
   * ? null : cause.toString())</code> (which typically contains the class and detail message of
   * <code>cause</code>). This constructor is useful for exceptions that are little more than
   * wrappers for other throwables.
   *
   * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
   *              (A <code>null</code> value is permitted, and indicates that the cause is
   *              nonexistent or unknown.)
   */
  public InvalidMoveException(final Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new exception with the specified detail message and cause.
   *
   * <p>Note that the detail message associated with
   * {@code cause} is <i>not</i> automatically incorporated in this exception's detail message.
   *
   * @param message the detail message (which is saved for later retrieval by the
   *                {@link #getMessage()} method).
   * @param cause   the cause (which is saved for later retrieval by the {@link #getCause()}
   *                method).  (A <code>null</code> value is permitted, and indicates that the cause
   *                is nonexistent or unknown.)
   */
  public InvalidMoveException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
