/**
 Created on 7/03/2017.
 */
package com.poker.sample;

import com.poker.sample.bean.Card;
import com.poker.sample.bean.Combination;
import com.poker.sample.utils.Constants;

import java.util.Arrays;

/**
 * Class provides methods to analyse Poker hands.
 */
public final class Analyzer {

        private Analyzer(){ }

    /**
     * Analyzes hand combination by given cards
     *
     * @param cards Card[] cards - cards array of hand
     * @return Combination - combination object of given cards
     */
    public static Combination evaluateHand(Card[] cards) {
        Combination[] combinations = Constants.combinations;

        int ranksbit = 1 << cards[0].getValue() | 1 << cards[1].getValue() | 1 << cards[2].getValue()
                | 1 << cards[3].getValue() | 1 << cards[4].getValue();
        int checkAce = ranksbit;

        long index = 0;
        long offset = 0;
        for (int i = 0; i < 5; i++) {
            offset = Analyzer.powerTwo(cards[i].getValue() * 4);
            index = index + offset * ((index / offset & 15) + 1);
        }
// by rank
        index = index % 15 - ((ranksbit / (ranksbit & -ranksbit) == 31)
                || ((ranksbit == 0x403c) && (checkAce != 16444)) ? 3 : 1);

// checking Flush, Straight flush, Royal flush
        int flush = 0;
        if (cards[0].getSuitId() == (cards[1].getSuitId() | cards[2].getSuitId()
                | cards[3].getSuitId() | cards[4].getSuitId())) {
            flush = 1;
        }
        index = index - (flush * ((ranksbit == 0x7c00) ? -5 : 1));
        return combinations[(int) index];
    }

    /**
     * Compares arrays values.
     *
     * @param valuesOne - sorted values of PlayerOne cards
     * @param valuesTwo - sorted values of PlayerTwo cards
     * @return int - winner number: 1 if highest value from array One, 2 if highest value from array Two,
     *                              0 if arrays values are equal
     */
    public static int compareArrValues(int[] valuesOne, int[] valuesTwo) {
        int result = 0;
        for (int i = 4; i >= 0; i--) {
            if(valuesOne[i] > valuesTwo[i]) {
                result = Constants.PLAYER_ONE;
                break;
            } else if(valuesOne[i] < valuesTwo[i]) {
                result = Constants.PLAYER_TWO;
                break;
            }
        }
        return result;
    }

    /**
     * Determines a winner in case combinations rank: 2,4,7,8
     *
     * @param valuesOne - sorted values of PlayerOne cards
     * @param valuesTwo - sorted values of PlayerTwo cards
     * @param rank - rank of combination
     * @return int - winner number: 1 if Player One is winner, 2 if Player Two is winner, 0 if cards tie
     */
    public static int scoreOfSet(int[] valuesOne, int[] valuesTwo, int rank) {
        int result = 0;
        int count = cardsNumberInSet(rank);
        int cardInSetOne = cardValueInSet(valuesOne, count);
        int cardInSetTwo = cardValueInSet(valuesTwo, count);

        if(cardInSetOne > cardInSetTwo) {
            result = Constants.PLAYER_ONE;
        } else if(cardInSetOne < cardInSetTwo) {
            result = Constants.PLAYER_TWO;
        } else if(cardInSetOne == cardInSetTwo) {
            result = compareArrValues(resortCardValues(valuesOne, cardInSetOne),
                                                    resortCardValues(valuesTwo, cardInSetTwo));
        }

        return result;
    }

    /**
     * Determines a winner in case both players have Two pairs.
     *
     * @param valuesOne int[] - sorted values of PlayerOne cards
     * @param valuesTwo int[] - sorted values of PlayerTwo cards
     * @return int - winner number: 1 if Player One is winner, 2 if Player Two is winner,
     *              0 if PlayerOne pairs have the same values as PlayerTwo pairs
     */
    public static int highInTwoPairs(int[] valuesOne, int[] valuesTwo) {
        int result = 0;
        int firstPairValueOne = cardValueInSetRevert(valuesOne);
        int firstPairValueTwo = cardValueInSetRevert(valuesTwo);
        int secondPairValueOne = cardValueInSet(valuesOne, 2);
        int secondPairValueTwo = cardValueInSet(valuesTwo, 2);

        if (firstPairValueOne > firstPairValueTwo) {
            result = Constants.PLAYER_ONE;
        }else if (firstPairValueOne < firstPairValueTwo) {
            result = Constants.PLAYER_TWO;
        }else if((firstPairValueOne == firstPairValueTwo) && (secondPairValueOne > secondPairValueTwo) ) {
            result = Constants.PLAYER_ONE;
        }else if((firstPairValueOne == firstPairValueTwo) && (secondPairValueOne < secondPairValueTwo) ) {
            result = Constants.PLAYER_TWO;
        }
        return result;
    }

    /**
     * Power of two
     * @param b int - power
     * @return long - Two is raised to the b power
     */
    private static long powerTwo(int b) {
        long power = 1;
        for (int i = 0; i < b; i++) {
            power = power * 2;
        }
        return power;
    }

    /**
     * Determines card value in the second pair for combination rank "Two pairs"
     *
     * @param values int[] - cards values array
     * @return int - value of pair in the reversed array
     */
    private static int cardValueInSetRevert(int[] values) {
        int value = 0;
        for (int i = 4; i >= 0; i--) {
            if(values[i] == values[i - 1]) {
                value = values[i];
                break;
            }
        }
        return value;
    }

    /**
     * Determines card value in the set. Number of cards in the set = count
     *
     * @param values int[] - cards values array
     * @param count int - number of the same cards values in the set
     * @return int - card value in the set of of the same card values
     */
    private static int cardValueInSet(int[] values, int count) {
        int value = 0;
        for (int i = 0; i < 5; i++) {
            if(values[i] == values[i + (count-1)]) {
                value = values[i];
                break;
            }
        }
        return value;
    }

    /**
     * Returns number of cards with the same value in the set of given combination rank
     * @param rank int - combination rank
     * @return int - Number of cards with the same value by given rank
     */
    private static int cardsNumberInSet(int rank) {
        int number = 0;
        switch (rank) {
            case 2:
                number = 2;
                break;
            case 3:
                number = 2;
                break;
            case 4:
                number = 3;
                break;
            case 7:
                number = 3;
                break;
            case 8:
                number = 4;
                break;
            default:
                number = 0;
        }
        return number;
    }

    /**
     * Returns new sorted array from given array without value which should be excluded.
     * Zero is inserted instead of excluded value.
     *
     * @param values int[] - given values array
     * @param excludeValue int - value which should be excluded
     * @return int[] - new sorted values array
     */
    private static int[] resortCardValues(int[] values, int excludeValue) {
        int[] newValues = values.clone();

        for (int i = 0; i < 5; i++) {
            if(newValues[i] == excludeValue) {
                newValues[i] = 0;
            }
        }
        Arrays.sort(newValues);
        return newValues;
    }

}