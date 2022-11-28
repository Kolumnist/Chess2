package de.hhn.it.devtools.apis.minesweeper;

/**
 * Enum to describe the different field statuses
 * @author Lara Weller, Jason Herrmann
 * @version 0.1
 */

public enum Status{
    BOMB,
    /** uncovered field has a bomb on it*/
    ONE,
    /** uncovered field has number 1 on it*/
    TWO,
    /** uncovered field has number 2 on it*/
    THREE,
    /** uncovered field has number 3 on it*/
    FOUR,
    /** uncovered field has number 4 on it*/
    FIVE,
    /** uncovered field has number 5 on it*/
    SIX,
    /** uncovered field has number 6 on it*/
    SEVEN,
    /** uncovered field has number 7 on it*/
    EIGHT,
    /** uncovered field has number 8 on it*/
    NOTHING,
    /** uncovered field has nothing on it*/
    FLAG
    /** field marked with a bomb */
}