/**
 Created on 7/03/2017.
 */
package com.poker.sample;

import com.poker.sample.bean.Card;
import com.poker.sample.utils.Constants;
import org.junit.jupiter.api.Assertions;

class AnalyzerTest {

    @org.junit.jupiter.api.Test
    void evaluateHand() {

        Card[] cards = {
            new Card(1, Constants.SPADES, 1),
            new Card(3, Constants.CLUBS, 2),
            new Card(7, Constants.DIAMONDS, 8),
            new Card(6, Constants.HEARTS, 4),
            new Card(9, Constants.SPADES, 1)
        };
        Assertions.assertEquals("High card", Analyzer.evaluateHand(cards).getDescription());

        Card[] cards2 = {
                new Card(6, Constants.SPADES, 1),
                new Card(12, Constants.CLUBS, 2),
                new Card(7, Constants.DIAMONDS, 8),
                new Card(10, Constants.HEARTS, 4),
                new Card(10, Constants.SPADES, 1)
        };
        Assertions.assertEquals("Pair", Analyzer.evaluateHand(cards2).getDescription());

        Card[] cards3 = {
                new Card(6, Constants.SPADES, 1),
                new Card(6, Constants.CLUBS, 2),
                new Card(7, Constants.DIAMONDS, 8),
                new Card(10, Constants.HEARTS, 4),
                new Card(10, Constants.SPADES, 1)
        };
        Assertions.assertEquals("Two pairs", Analyzer.evaluateHand(cards3).getDescription());

        Card[] cards4 = {
                new Card(6, Constants.SPADES, 1),
                new Card(6, Constants.CLUBS, 2),
                new Card(6, Constants.DIAMONDS, 8),
                new Card(12, Constants.HEARTS, 4),
                new Card(10, Constants.SPADES, 1)
        };
        Assertions.assertEquals("Three of a kind", Analyzer.evaluateHand(cards4).getDescription());

        Card[] cards5 = {
                new Card(6, Constants.SPADES, 1),
                new Card(7, Constants.CLUBS, 2),
                new Card(8, Constants.DIAMONDS, 8),
                new Card(9, Constants.HEARTS, 4),
                new Card(10, Constants.SPADES, 1)
        };
        Assertions.assertEquals("Straight", Analyzer.evaluateHand(cards5).getDescription());

        Card[] cards6 = {
                new Card(10, Constants.SPADES, 1),
                new Card(11, Constants.SPADES, 1),
                new Card(6, Constants.SPADES, 1),
                new Card(13, Constants.SPADES, 1),
                new Card(2, Constants.SPADES, 1)
        };
        Assertions.assertEquals("Flush", Analyzer.evaluateHand(cards6).getDescription());

        Card[] cards7 = {
                new Card(7, Constants.SPADES, 1),
                new Card(7, Constants.CLUBS, 2),
                new Card(7, Constants.DIAMONDS, 8),
                new Card(10, Constants.HEARTS, 4),
                new Card(10, Constants.SPADES, 1)
        };
        Assertions.assertEquals("Full House", Analyzer.evaluateHand(cards7).getDescription());

        Card[] cards8 = {
                new Card(7, Constants.SPADES, 1),
                new Card(7, Constants.CLUBS, 2),
                new Card(7, Constants.DIAMONDS, 8),
                new Card(7, Constants.HEARTS, 4),
                new Card(10, Constants.SPADES, 1)
        };
        Assertions.assertEquals("Four of a kind", Analyzer.evaluateHand(cards8).getDescription());

        Card[] cards9 = {
                new Card(10, Constants.SPADES, 1),
                new Card(11, Constants.SPADES, 1),
                new Card(9, Constants.SPADES, 1),
                new Card(12, Constants.SPADES, 1),
                new Card(8, Constants.SPADES, 1)
        };
        Assertions.assertEquals("Straight flush", Analyzer.evaluateHand(cards9).getDescription());

        Card[] cards10 = {
                new Card(10, Constants.SPADES, 1),
                new Card(11, Constants.SPADES, 1),
                new Card(13, Constants.SPADES, 1),
                new Card(12, Constants.SPADES, 1),
                new Card(14, Constants.SPADES, 1)
        };
        Assertions.assertEquals("Royal Flush", Analyzer.evaluateHand(cards10).getDescription());

        Card[] cards11 = {
                new Card(14, Constants.SPADES, 1),
                new Card(2, Constants.CLUBS, 2),
                new Card(3, Constants.SPADES, 1),
                new Card(4, Constants.CLUBS, 2),
                new Card(5, Constants.SPADES, 1)
        };
        Assertions.assertEquals("High card", Analyzer.evaluateHand(cards11).getDescription());
    }

    @org.junit.jupiter.api.Test
    void compareArrValuesTest() {
        int[] valOne = {0,1,2,3,4};
        int[] valTwo = {1,2,3,4,5};
        Assertions.assertEquals(2, Analyzer.compareArrValues(valOne, valTwo));

        int[] valOne1 = {1,6,1,3,4};
        int[] valTwo1 = {0,2,3,4,5};
        Assertions.assertEquals(2, Analyzer.compareArrValues(valOne1, valTwo1));

        int[] valOne2 = {1,2,2,3,4};
        int[] valTwo2 = {0,0,0,4,5};
        Assertions.assertEquals(2, Analyzer.compareArrValues(valOne2, valTwo2));
    }

    @org.junit.jupiter.api.Test
    void scoreOfSetTest() {
        int[] valOne = {1,1,2,3,4};
        int[] valTwo = {1,2,3,3,4};
        Assertions.assertEquals(2, Analyzer.scoreOfSet(valOne, valTwo, 2));

        int[] valOne1 = {1,2,3,4,4};
        int[] valTwo1 = {1,2,3,4,4};
        Assertions.assertEquals(0, Analyzer.scoreOfSet(valOne1, valTwo1, 2));

        int[] valOne2 = {1,2,3,3,4};
        int[] valTwo2 = {1,2,4,4,4};
        Assertions.assertEquals(2, Analyzer.scoreOfSet(valOne2, valTwo2, 2));

        int[] valOne3 = {1,2,3,3,3};
        int[] valTwo3 = {1,2,4,4,4};
        Assertions.assertEquals(2, Analyzer.scoreOfSet(valOne3, valTwo3, 4));

        int[] valOne4 = {1,2,4,4,4};
        int[] valTwo4 = {1,2,4,4,4};
        Assertions.assertEquals(0, Analyzer.scoreOfSet(valOne4, valTwo4, 4));

        int[] valOne42 = {2,1,4,4,4};
        int[] valTwo42 = {1,2,4,4,4};
        Assertions.assertEquals(0, Analyzer.scoreOfSet(valOne42, valTwo42, 4));

        int[] valOne41 = {2,3,4,4,4};
        int[] valTwo41 = {1,2,4,4,4};
        Assertions.assertEquals(1, Analyzer.scoreOfSet(valOne41, valTwo41, 4));

        int[] valOne5 = {1,3,3,3,3};
        int[] valTwo5 = {1,2,4,4,4};
        Assertions.assertEquals(2, Analyzer.scoreOfSet(valOne5, valTwo5, 4));

        int[] valOne6 = {1,3,3,3,3};
        int[] valTwo6 = {1,2,2,2,2};
        Assertions.assertEquals(1, Analyzer.scoreOfSet(valOne6, valTwo6, 8));

        int[] valOne7 = {1,3,3,3,3};
        int[] valTwo7 = {2,3,3,3,3};
        Assertions.assertEquals(2, Analyzer.scoreOfSet(valOne7, valTwo7, 8));

        int[] valOne8 = {1,3,3,3,3};
        int[] valTwo8 = {1,3,3,3,3};
        Assertions.assertEquals(0, Analyzer.scoreOfSet(valOne8, valTwo8, 8));

        int[] valOne9 = {2,2,3,3,3};
        int[] valTwo9 = {1,1,3,3,3};
        Assertions.assertEquals(1, Analyzer.scoreOfSet(valOne9, valTwo9, 7));

        int[] valOne10 = {2,2,3,3,3};
        int[] valTwo10 = {1,1,4,4,4};
        Assertions.assertEquals(2, Analyzer.scoreOfSet(valOne10, valTwo10, 7));

        int[] valOne11 = {1,1,4,4,4};
        int[] valTwo11 = {1,1,4,4,4};
        Assertions.assertEquals(0, Analyzer.scoreOfSet(valOne11, valTwo11, 7));
    }

    @org.junit.jupiter.api.Test
    void highInTwoPairsTest() {
        int[] valuesOne = {1,1,2,3,3};
        int[] valuesTwo = {1,1,3,3,4};
        Assertions.assertEquals(0, Analyzer.highInTwoPairs(valuesOne, valuesTwo));

        int[] valuesOne1 = {1,1,2,3,3};
        int[] valuesTwo1 = {2,2,3,3,4};
        Assertions.assertEquals(2, Analyzer.highInTwoPairs(valuesOne1, valuesTwo1));

        int[] valuesOne2 = {1,1,2,3,3};
        int[] valuesTwo2 = {1,1,4,4,5};
        Assertions.assertEquals(2, Analyzer.highInTwoPairs(valuesOne2, valuesTwo2));

        int[] valuesOne3 = {1,1,2,3,3};
        int[] valuesTwo3 = {1,1,3,3,3};
        Assertions.assertEquals(0, Analyzer.highInTwoPairs(valuesOne3, valuesTwo3));

    }

}