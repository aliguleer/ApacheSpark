package com.udemy.spark.app;

import java.io.Serializable;


public class PlayersModel  implements Serializable{
    String roundID;
    String matchID;
    String team;
    String coachTeam;
    String lineup;
    String shirtNumber;
    String playerName;
    String position;
    String event;

    public PlayersModel(String roundID, String matchID, String team, String coachTeam, String lineup, String shirtNumber, String playerName, String position, String event) {
        this.roundID = roundID;
        this.matchID = matchID;
        this.team = team;
        this.coachTeam = coachTeam;
        this.lineup = lineup;
        this.shirtNumber = shirtNumber;
        this.playerName = playerName;
        this.position = position;
        this.event = event;
    }

    public String getRoundID() {
        return roundID;
    }

    public String getMatchID() {
        return matchID;
    }

    public String getTeam() {
        return team;
    }

    public String getCoachTeam() {
        return coachTeam;
    }

    public String getLineup() {
        return lineup;
    }

    public String getShirtNumber() {
        return shirtNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPosition() {
        return position;
    }

    public String getEvent() {
        return event;
    }
}
