package de.hhn.it.devtools.components.memory.provider;


import de.hhn.it.devtools.apis.memory.CardSetDescriptor;
import de.hhn.it.devtools.apis.memory.Difficulty;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import java.util.HashMap;
import java.util.List;

public class SfsCardSet {
  private CardSetDescriptor descriptor;

  public SfsCardSet(CardSetDescriptor descriptor) {
    this.descriptor = descriptor;
  }

  public CardSetDescriptor getDescriptor() {
    return descriptor;
  }
}
