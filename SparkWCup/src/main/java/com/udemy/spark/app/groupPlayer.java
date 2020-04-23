package com.udemy.spark.app;

import java.io.Serializable;

public class groupPlayer implements Serializable {
    String playerName;
    int matchCount;

    public String getPlayerName() {
        return playerName;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public groupPlayer(String playerName, int matchCount) {
        this.playerName = playerName;
        this.matchCount = matchCount;
    }
}