package de.hhn.it.devtools.components.example.coffeemakerservice.provider;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerDescriptor;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerListener;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.Recipe;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * Interface to a coffee maker to allow remote control of the machine.
 */
public interface CoffeeMaker {

  void switchOn() throws IllegalStateException;

  void switchOff() throws IllegalStateException;

  void brew(Recipe recipe) throws IllegalParameterException, IllegalStateException;

  void cleanIt() throws IllegalStateException;

  void addCallback(CoffeeMakerListener listener) throws IllegalParameterException;

  void removeCallback(CoffeeMakerListener listener) throws IllegalParameterException;

  CoffeeMakerDescriptor getDescriptor();
}
