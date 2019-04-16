package com.example.topspin;

public class TennisMatch {

    private String[] matchTypes = {"Singles", "Doubles"};
    private String matchType;
    private String homePlayer1Name;
    private String homePlayer2Name;
    private String awayPlayer1Name;
    private String awayPlayer2Name;

    public TennisMatch(){}

    public TennisMatch(int index, String homePlayer1Name, String homePlayer2Name, String awayPlayer1Name, String awayPlayer2Name) {
        this.matchType = matchTypes[index];
        this.homePlayer1Name = homePlayer1Name;
        this.homePlayer2Name = homePlayer2Name;
        this.awayPlayer1Name = awayPlayer1Name;
        this.awayPlayer2Name = awayPlayer2Name;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getHomePlayer1Name() {
        return homePlayer1Name;
    }

    public void setHomePlayer1Name(String homePlayer1Name) {
        this.homePlayer1Name = homePlayer1Name;
    }

    public String getHomePlayer2Name() {
        return homePlayer2Name;
    }

    public void setHomePlayer2Name(String homePlayer2Name) {
        this.homePlayer2Name = homePlayer2Name;
    }

    public String getAwayPlayer1Name() {
        return awayPlayer1Name;
    }

    public void setAwayPlayer1Name(String awayPlayer1Name) {
        this.awayPlayer1Name = awayPlayer1Name;
    }

    public String getAwayPlayer2Name() {
        return awayPlayer2Name;
    }

    public void setAwayPlayer2Name(String awayPlayer2Name) {
        this.awayPlayer2Name = awayPlayer2Name;
    }



}