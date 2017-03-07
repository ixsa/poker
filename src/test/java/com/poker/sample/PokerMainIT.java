/**
 Created on 7/03/2017.
 */
package com.poker.sample;

import java.io.*;

class PokerMainIT {

    @org.junit.jupiter.api.Test
    void mainTest() throws IOException {
        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("poker-hands.txt").getFile());

        InputStream in = new FileInputStream(file);
        System.setIn(in);

        PokerMain.main(null);

        StringBuilder expected = new StringBuilder("");
        expected.append("--------------- Expected RESULT ------------------").append("\n");
        expected.append("Player 1: 263").append("\n");
        expected.append("Player 2: 237").append("\n");
        System.out.println(expected.toString());

    }

}