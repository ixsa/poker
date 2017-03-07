/**
 Created on 7/03/2017.
 */
package com.poker.sample.utils;

import com.poker.sample.bean.Combination;

/**
 * Class provides a static constants.
 */
public final class Constants {

    private Constants() { }

    public final static String PLAYER = "Player ";
    public final static String WINNNER = "WINNNER: ";

    public final static String DIAMONDS = "D";
    public final static String CLUBS = "C";
    public final static String HEARTS = "H";
    public final static String SPADES = "S";

    public final static String TEN = "T";
    public final static String JACK = "J";
    public final static String QUEEN = "Q";
    public final static String KING = "K";
    public final static String ACE = "A";

    public final static Combination[] combinations = {
        new Combination(8, 0, "Four of a kind"),
        new Combination(9, 1, "Straight flush"),
        new Combination(5, 2, "Straight"),
        new Combination(6, 3, "Flush"),
        new Combination(1, 4, "High card"),
        new Combination(2, 5, "Pair"),
        new Combination(3, 6, "Two pairs"),
        new Combination(10, 7, "Royal Flush"),
        new Combination(4, 8, "Three of a kind"),
        new Combination(7, 9, "Full House"),
    };

    public final static int PLAYER_ONE = 1;
    public final static int PLAYER_TWO = 2;
}
