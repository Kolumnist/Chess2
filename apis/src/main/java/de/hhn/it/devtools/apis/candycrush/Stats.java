package de.hhn.it.devtools.apis.candycrush;

public class Stats {
/*
   * Enum that describe the state of the candy´s life.
 */
    public enum stats {
    /*
     * Candy is visible and is not cruched.
     */
    ALIVE,
    /*
     * Candy is crushed and changes to a crushed candy.
     */
    CRUSHED,

    /*
     * Candy is dead and will be deleted.
     */
    DEAD

}
}
