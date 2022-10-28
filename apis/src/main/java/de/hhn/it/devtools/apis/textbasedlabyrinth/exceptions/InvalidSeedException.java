package de.hhn.it.devtools.apis.textbasedlabyrinth.exceptions;



public class InvalidSeedException extends Exception {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(NoSuchItemFoundException.class);



    public InvalidSeedException() {

    }

    public InvalidSeedException(String message) {
        super(message);
    }


}
