package de.hhn.it.devtools.apis.textbasedlabyrinth;


/**
 * Item.
 */
public class Item {

  private final int itemId;
  private final String name;

  /**
     * Item.
     *
   */
  public Item(int id, String name) {

    this.itemId = id;
    this.name = name;
  }

  public String getName() {
    return name;
  }


  public int getItemId() {
    return itemId;
  }



}
