package de.hhn.it.devtools.apis.textbasedlabyrinth.exceptions;


public class RoomFailedException extends Exception {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(RoomFailedException.class);


    public RoomFailedException() {
    }

    public RoomFailedException(String message) {
        super(message);
    }

    public RoomFailedException(Throwable cause, String message) {
        super(message, cause);
    }


}
