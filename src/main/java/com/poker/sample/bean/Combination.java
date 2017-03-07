/**
 Created on 7/03/2017.
 */
package com.poker.sample.bean;

/**
 * Combination bean
 */
public class Combination {

    // Combination rank
    private int rank;
    // Combination index in the map for hand calculation
    private int index;
    // Combination description
    private String description;

    public Combination(int rank, int index, String description) {
        this.rank = rank;
        this.index = index;
        this.description = description;
    }

    public final int getRank() {
        return rank;
    }

    public final String getDescription() {
        return description;
    }
}
