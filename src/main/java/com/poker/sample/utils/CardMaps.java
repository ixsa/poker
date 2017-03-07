/**
 Created on 7/03/2017.
 */
package com.poker.sample.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Class provides mapping between cards suit or values and integer representation for Poker hand analyzing.
 */
public final class CardMaps {

    private CardMaps() { }

    private static Map<String, Integer> valuesMap = makeValuesMap();
    private static Map<String, Integer> suitMap = makeSuitMap();

    private static Map<String, Integer> makeValuesMap() {
        Map<String, Integer> tmp = new HashMap<String, Integer>();
        tmp.put("2", Integer.valueOf(2));
        tmp.put("3", Integer.valueOf(3));
        tmp.put("4", Integer.valueOf(4));
        tmp.put("5", Integer.valueOf(5));
        tmp.put("6", Integer.valueOf(6));
        tmp.put("7", Integer.valueOf(7));
        tmp.put("8", Integer.valueOf(8));
        tmp.put("9", Integer.valueOf(9));

        tmp.put(Constants.TEN, Integer.valueOf(10));
        tmp.put(Constants.JACK, Integer.valueOf(11));
        tmp.put(Constants.QUEEN, Integer.valueOf(12));
        tmp.put(Constants.KING, Integer.valueOf(13));
        tmp.put(Constants.ACE, Integer.valueOf(14));

        return Collections.unmodifiableMap(tmp);
    }

    private static Map<String, Integer> makeSuitMap() {
        Map<String, Integer> tmp = new HashMap<String, Integer>();
        tmp.put(Constants.SPADES, Integer.valueOf(1));
        tmp.put(Constants.CLUBS, Integer.valueOf(2));
        tmp.put(Constants.HEARTS, Integer.valueOf(4));
        tmp.put(Constants.DIAMONDS, Integer.valueOf(8));
        return Collections.unmodifiableMap(tmp);
    }

    /**
     * Method returns mapping between card value in string representation and card value as integer
     * @return Map<String, Integer> - where Key is card value in string representation<br>
     *                                Value is card value as integer
     */
    public static Map<String, Integer> getValuesMap() {
        return valuesMap;
    }

    /**
     * Method returns mapping between suit name and suit id
     * @return Map<String, Integer> - where Kay is suit name, Value is suit id as unique integer number
     */
    public static Map<String, Integer> getSuitMap() {
        return suitMap;
    }
}
