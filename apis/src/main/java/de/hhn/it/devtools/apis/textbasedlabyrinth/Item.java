package de.hhn.it.devtools.apis.textbasedlabyrinth;


/**
 * Creates Items which can be found in Rooms and can be used to open doors.
 */
public class Item {

  private final int itemId;
  private String name;
  private final String info;
  protected boolean isTreasure;

  /**
   * Item constructor.
   *
   * @param id id of the item.
   * @param name name of the item.
   * @param info info of the item.
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