package de.hhn.it.devtools.javafx.text_based_labyrinth_FX;

public class UnknownTransitionException extends Exception {


    private String to;
    private String from;



    public UnknownTransitionException(final String message, final String from, final String to) {
        super(message);
    }

}
