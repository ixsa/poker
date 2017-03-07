## Poker Hand Sorter
A poker hand consists of a combination of five playing cards, ranked in the following ascending order (lowest to highest):

| Rank | Combination     | Description                                                   |
|:-----|----------------:|:-------------------------------------------------------------:|
| 1    | High card       | Highest value card                                            |
| 2    | Pair            | Two cards of same value                                       |
| 3    | Two pairs       | Two different pairs                                           |
| 4    | Three of a kind | Three cards of the same value                                 |
| 5    | Straight        | All five cards in consecutive value order                     |
| 6    | Flush           | All five cards having the same suit                           |
| 7    | Full house      | Three of a kind and a Pair                                    |
| 8    | Four of a kind  | Four cards of the same value                                  |
| 9    | Straight flush  | All five cards in consecutive value order, with the same suit |
| 10   | Royal Flush     |  Ten, Jack, Queen, King and Ace in the same suit              |

The cards are valued in the order:
2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace*

* For this exercise, Ace is considered high only. (i.e. cannot be used as a low card below 2 in a straight).

Suits are:

Diamonds (D), Hearts (H), Spades (S), Clubs (C)

When multiple players have the same ranked hand then the rank made up of the highest value cards wins. For example, pair of kings beats a
pair of queens, and a straight with a high card of Jack beats a straight with high card of nine.

If two ranks tie, for example, if both players have a pair of Jacks, then highest cards in each hand are compared; if the highest cards tie then the
next highest cards are compared, and so on.

Note - suits are not taken into account to break a tie for this exercise - only the value of the card determines a winner.

## Build

Using Maven:

1. Go to `poker` directory
2. Run command `mvn package`

Using Java: https://docs.oracle.com/javase/tutorial/deployment/jar/build.html

## Execution

 1. Put  poker-hands.txt and poker-1.0-jar-with-dependencies.jar into target directory `mydir`
 2. Go to `mydir`
 3. Run command:
    * `cat poker-hands.txt | java -jar poker-1.0-jar-with-dependencies.jar` for Linux
    * `type poker-hands.txt | java -jar poker-1.0-jar-with-dependencies.jar` for Windows
 4. Expected output:
 ```
    --------------- RESULT ------------------
        Player 1: 263
        Player 2: 237
```