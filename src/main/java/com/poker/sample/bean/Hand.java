/**
 Created on 7/03/2017.
 */
package com.poker.sample.bean;

import java.util.Arrays;

/**
 * Hand bean
 */
public class Hand {

    // Cards array
    private Card[] cards = new Card[5];
    // Cards values array which is sorted by increase
    private int[] sortValues;
    // Sum of cards values
    private int sumCards;
    // High card value
    private int highCard;

    private Combination combination;

    public Hand(Card[] cards) {
        this.cards = cards.clone();
        this.sumCards = 0;
        sum();
        this.highCard = high();
    }

    private void sum() {
        for(Card card : cards) {
            sumCards = sumCards + card.getValue();
        }
    }

    private int high() {
        sortValues = new int[5];
        for(int i = 0; i < 5; i++) {
            sortValues[i] = cards[i].getValue();
        }
        Arrays.sort(sortValues);

        return sortValues[4];
    }

    public final Card[] getCards() {
        return cards.clone();
    }

    public final int getSumCards() {
        return sumCards;
    }

    public final int getHighCard() {
        return highCard;
    }

    public final int[] getSortValues() {
        return sortValues.clone();
    }

    public final Combination getCombination() {
        return combination;
    }

    public final void setCombination(Combination combination) {
        this.combination = combination;
    }
}
