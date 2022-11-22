package de.hhn.it.devtools.apis.textbasedlabyrinth;

import java.util.ArrayList;
import java.util.Random;

/**
 * This is a unique number String.
 */
public class Seed {

  public String[] stringParser;
  public ArrayList<Integer> roomParser;
  public final int minDigitNumber = 7;

  /**
   * Constructor for Seed Class
   * @param seed the digit chain for the layout creation, the first 7 digits dictate the layout
   */
  public Seed(String seed) {

    stringParser = seed.split("(?!^)");
    ArrayList<Integer> intParser = new ArrayList<Integer>();

    for (String s : stringParser) {
      intParser.add(Integer.parseInt(s));
    }

    while(intParser.size() < minDigitNumber){
      intParser.add(0);
    }

    this.roomParser = intParser;
  }

  /**
   * Constructor for Seed without any input.
   */
  public Seed() {
    Random rand = new Random();
    while(roomParser.size() < minDigitNumber){
      roomParser.add(rand.nextInt(9));
    }
  }

  public int getTotalRoomCount(){
    return roomParser.get(0) + roomParser.get(1) + roomParser.get(2);
  }

  public ArrayList<Integer> getRoomParser(){
    return roomParser;
  }

}
