/**
 Created on 7/03/2017.
 */
package com.poker.sample.bean;

/**
 * Player bean
 */
public class Player {

    // Player name
    private String playerName;
    // Total winner score
    private int totalHands;
    // Current hand
    private Hand currentHand;

    public Player(String playerName) {
        this.playerName = playerName;
        this.totalHands = 0;
    }

    public final String getPlayerName() {
        return playerName;
    }

    public final int getTotalHands() {
        return totalHands;
    }

    public final void addHandWin() {
        totalHands++;
    }

    public final Hand getCurrentHand() {
        return currentHand;
    }

    public final void setCurrentHand(Hand currentHand) {
        this.currentHand = currentHand;
    }
}
