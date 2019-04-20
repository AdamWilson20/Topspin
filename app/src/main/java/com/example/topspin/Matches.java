package com.example.topspin;

public class Matches {
    private int matchID;
    private int eventID;
    private String matchType;
    private String homePlayer1;
    private String homePlayer2;
    private String awayPlayer1;
    private String awayPlayer2;
    private int homeTeamSets;
    private int awayTeamSets;
    private String result;

    public Matches(){}

    public Matches(int matchID, int eventID, String matchType, String homePlayer1, String homePlayer2, String awayPlayer1, String awayPlayer2, int homeTeamSets, int awayTeamSets, String result) {
        this.matchID = matchID;
        this.eventID = eventID;
        this.matchType = matchType;
        this.homePlayer1 = homePlayer1;
        this.homePlayer2 = homePlayer2;
        this.awayPlayer1 = awayPlayer1;
        this.awayPlayer2 = awayPlayer2;
        this.homeTeamSets = homeTeamSets;
        this.awayTeamSets = awayTeamSets;
        this.result = result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getHomePlayer1() {
        return homePlayer1;
    }

    public void setHomePlayer1(String homePlayer1) {
        this.homePlayer1 = homePlayer1;
    }

    public String getHomePlayer2() {
        return homePlayer2;
    }

    public void setHomePlayer2(String homePlayer2) {
        this.homePlayer2 = homePlayer2;
    }

    public String getAwayPlayer1() {
        return awayPlayer1;
    }

    public void setAwayPlayer1(String awayPlayer1) {
        this.awayPlayer1 = awayPlayer1;
    }

    public String getAwayPlayer2() {
        return awayPlayer2;
    }

    public void setAwayPlayer2(String awayPlayer2) {
        this.awayPlayer2 = awayPlayer2;
    }

    public int getHomeTeamSets() {
        return homeTeamSets;
    }

    public void setHomeTeamSets(int homeTeamSets) {
        this.homeTeamSets = homeTeamSets;
    }

    public int getAwayTeamSets() {
        return awayTeamSets;
    }

    public void setAwayTeamSets(int awayTeamSets) {
        this.awayTeamSets = awayTeamSets;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
