package de.hhn.it.devtools.apis.textbasedlabyrinth;

/**
 * Callback handler, implementing Callback interface
 */
public class OutputNotifier implements OutputListener{

    public OutputNotifier(){ }

    @Override
    public void sendOutput(String output) {
        System.out.println(output);
    }
}
