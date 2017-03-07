/**
 Created on 7/03/2017.
 */
package com.poker.sample.bean;

/**
 * Card bean
 */
public class Card {

    // Card value
    private int value;
    // Card suit name
    private String suit;
    // Card suit unique id
    private int suitId;

    public Card(int value, String suit, int suitId) {
        this.value = value;
        this.suit = suit;
        this.suitId = suitId;
    }

    public final int getValue() { return value; }

    public final String getSuit() {
        return suit;
    }

    public final int getSuitId() {
        return suitId;
    }
}
