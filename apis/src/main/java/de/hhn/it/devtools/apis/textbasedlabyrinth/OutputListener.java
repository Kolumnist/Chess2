package de.hhn.it.devtools.apis.textbasedlabyrinth;

/**
 * Interface for callback method
 */
public interface OutputListener {

    public void sendOutputToMainField(String output);


    void sendOutputToInventoryField(String output);
}
