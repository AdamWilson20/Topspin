package com.example.topspin;

public class MatchSet {



    private int matchID;
    private int setID;
   // private int setNumber;
    private int homeScore;
    private int awayScore;
    private String result;

    public MatchSet(){}

    public MatchSet(int setID, int matchID,  int homeScore, int awayScore, String result) {



        this.matchID = matchID;
        this.setID = setID;
       // this.setNumber = setNumber;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.result = result;
    }


    public int getSetID() {
        return setID;
    }

    public void setSetID(int setID) {
        this.setID = setID;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

  /*  public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }*/

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
