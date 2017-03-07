/**
 Created on 7/03/2017.
*/
package com.poker.sample;

import com.poker.sample.bean.Card;
import com.poker.sample.bean.Combination;
import com.poker.sample.bean.Hand;
import com.poker.sample.bean.Player;
import com.poker.sample.utils.CardMaps;
import com.poker.sample.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Scanner;

/**
 * Main class provides an implementation of Poker for a two players.
 * Hands have taken via input stream from command line arguments.
 * Class output prints how many hands Player 1 won, and how many hands Player 2 won.
 */
public class PokerMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(PokerMain.class);

    private static Player playerOne = new Player(Constants.PLAYER + "1: ");
    private static Player playerTwo = new Player(Constants.PLAYER + "2: ");
    private static Map<String, Integer> suitMap = CardMaps.getSuitMap();
    private static Map<String, Integer> valuesMap = CardMaps.getValuesMap();

    private PokerMain(){ }

    /**
     * Adds winner hand in the score
     * @param player int - winner number: 1 if Player One is winner, 2 if Player Two is winner, 0 if cards tie
     */
    private static void addScore(int player){
        if (player == Constants.PLAYER_ONE) {
            playerOne.addHandWin();
            LOGGER.debug(Constants.WINNNER + playerOne.getPlayerName());
        }else if (player == Constants.PLAYER_TWO) {
            playerTwo.addHandWin();
            LOGGER.debug(Constants.WINNNER + playerTwo.getPlayerName());
        }
    }

    /**
     * Calculates score in case combination ranks 3 and 7
     * @param player int - winner number: 1 if Player One is winner, 2 if Player Two is winner, 0 if cards tie
     * @param sumOne int - Sum of PlayerOne cards values
     * @param sumTwo int - Sum of PlayerTwo cards values
     */
    private static void makeScore(int player, int sumOne, int sumTwo){

        if (player > 0) {
            addScore(player);
        }else if (player == 0 && sumOne > sumTwo) {
            playerOne.addHandWin();
            LOGGER.debug(Constants.WINNNER + playerOne.getPlayerName());

        }else if (player == 0 && sumOne < sumTwo) {
            playerTwo.addHandWin();
            LOGGER.debug(Constants.WINNNER + playerTwo.getPlayerName());
        }
    }

    /**
     * Analyzes score if both player have the same card combination
     * @param rank int - combination rank value
     */
    private static void sameRank(int rank) {

        int hcOne = playerOne.getCurrentHand().getHighCard();
        int hcTwo = playerTwo.getCurrentHand().getHighCard();

        int sumOne = playerOne.getCurrentHand().getSumCards();
        int sumTwo = playerTwo.getCurrentHand().getSumCards();

        int[] valuesOne = playerOne.getCurrentHand().getSortValues();
        int[] valuesTwo = playerTwo.getCurrentHand().getSortValues();

        if(rank == 1 || rank == 6) {
            addScore(Analyzer.compareArrValues(valuesOne, valuesTwo));

        } else if (rank == 2 || rank == 4 || rank == 8) {
            addScore(Analyzer.scoreOfSet(valuesOne, valuesTwo, rank));

        } else if ((rank == 5 || rank == 9) && hcOne > hcTwo) {
            playerOne.addHandWin();
            LOGGER.debug(Constants.WINNNER + playerOne.getPlayerName());

        } else if ((rank == 5 || rank == 9) && hcOne < hcTwo) {
            playerTwo.addHandWin();
            LOGGER.debug(Constants.WINNNER + playerTwo.getPlayerName());

        } else if (rank == 3) {
            makeScore(Analyzer.highInTwoPairs(valuesOne, valuesTwo), sumOne, sumTwo);

        } else if (rank == 7 ) {
            makeScore(Analyzer.scoreOfSet(valuesOne, valuesTwo, rank), sumOne, sumTwo);

        }
    }

    /**
     * Sets up Hand object from given cards string array
     * @param cardArr String[] - Cards array in string representation as [value][suit char]
     * @return Hand - Hand object
     */
    private static Hand setUpHand(String[] cardArr) {
        Card[] cards = new Card[5];

        for (int i = 0; i < 5; i++) {
            String value = String.valueOf(cardArr[i].charAt(0));
            String suit = String.valueOf(cardArr[i].charAt(1));

            if (valuesMap.get(value) == null || suitMap.get(suit) == null) {
                throw new NullPointerException("ERROR: non existing card!");
            }

            cards[i] = new Card(valuesMap.get(value), suit, suitMap.get(suit));
        }
        return new Hand(cards);
    }

    /**
     * Main method. Poker implementation for a two players.
     *
     * @param args String[] - arguments from console. Not used here
     */
    public static void main (String[] args) {

        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);
        int j = 0;
        while (scanner.hasNextLine()) {

            try {
                String line = scanner.nextLine();
                LOGGER.info("(index=" + j + ") Reading line: " + line);

                final String[] cards = line.split(" ");
                if (line.length() != 29 || cards.length != 10) {
                    LOGGER.error("ERROR: wrong number of cards for two players!");
                    System.out.println("ERROR: wrong number of cards for two players!");
                    System.exit(1);
                }

                final String[] handOne = {cards[0], cards[1], cards[2], cards[3], cards[4]};
                final String[] handTwo = {cards[5], cards[6], cards[7], cards[8], cards[9]};

                playerOne.setCurrentHand(setUpHand(handOne));
                playerTwo.setCurrentHand(setUpHand(handTwo));

                Combination cOne = Analyzer.evaluateHand(playerOne.getCurrentHand().getCards());
                playerOne.getCurrentHand().setCombination(cOne);

                Combination cTwo = Analyzer.evaluateHand(playerTwo.getCurrentHand().getCards());
                playerTwo.getCurrentHand().setCombination(cTwo);

                int rankOne = playerOne.getCurrentHand().getCombination().getRank();
                int rankTwo = playerTwo.getCurrentHand().getCombination().getRank();

                LOGGER.info("==> " +playerOne.getPlayerName() + " : "
                        + playerOne.getCurrentHand().getCombination().getDescription());
                LOGGER.info("==> " + playerTwo.getPlayerName()  + " : "
                        + playerTwo.getCurrentHand().getCombination().getDescription());

                if (rankOne > rankTwo) {
                    playerOne.addHandWin();
                    LOGGER.debug(Constants.WINNNER + playerOne.getPlayerName());

                } else if (rankOne < rankTwo) {
                    playerTwo.addHandWin();
                    LOGGER.debug(Constants.WINNNER + playerTwo.getPlayerName());

                } else if (rankOne == rankTwo) {
                    sameRank(rankOne);
                   LOGGER.info("======> Same Rank!");

                }

            } catch (Exception e) {
                LOGGER.error("PokerMAin error: " + e.getMessage());
                System.out.println(e.getMessage());
                System.exit(1);
            }
            j++;
        }
        scanner.close();

        LOGGER.info(playerOne.getPlayerName() + playerOne.getTotalHands());
        LOGGER.info(playerTwo.getPlayerName() + playerTwo.getTotalHands());

        System.out.println("--------------- RESULT ------------------");
        System.out.println(playerOne.getPlayerName() + playerOne.getTotalHands());
        System.out.println(playerTwo.getPlayerName() + playerTwo.getTotalHands());
    }

}
