package com.example.topspin;

public class MatchSet {

    private int eventID;
    private int matchID;
    private int setNumber;
    private int homeScore;
    private int awayScore;
    private String result;

    public MatchSet(){}

    public MatchSet(int eventID, int matchID, int setNumber, int homeScore, int awayScore, String result) {
        this.eventID = eventID;
        this.matchID = matchID;
        this.setNumber = setNumber;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.result = result;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
