package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;


/**
 * Creates Items which can be found in Rooms and can be used to open doors.
 */
public class Item {

  private final int itemId;
  private String name;
  private final String info;
  protected boolean isTreasure;

  /**
   * Every Item has an Item id, a name and a information.
   *
   */
  public Item(int id, String name, String info) {
    this.itemId = id;
    this.name = name;
    this.info = info;
    this.isTreasure = false;
  }

  public String getName() {

    return name;
  }


  public int getItemId() {

    return itemId;
  }

  public String getInfo() {

    return info;
  }

  public boolean getIsTreasure(){
    return isTreasure;
  }

}
