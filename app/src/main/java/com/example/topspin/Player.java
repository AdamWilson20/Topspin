package com.example.topspin;

public class Player {

    // I would recommend dropping the standing attribute
    // and adding the attributes from the website at http://www.odusports.com/ViewArticle.dbml?ATCLID=210331019&DB_OEM_ID=31100&Q_SEASON=2018
    //The Extra entries would be classYear, Major, and High school all Strings and easy to use
    //NOTED will fix shortly.
    private int playerID;
    private String playerImage;
    private String firstName;
    private String lastName;
    private String height;
    private String weight;
    private String year;
    private String hometown;



    public Player(int playerID,String playerImage,String firstName, String lastName,String height, String weight, String year, String hometown) {
        this.playerID = playerID;
        this.playerImage = playerImage;
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
        this.hometown = hometown;
        this.height = height;
        this.weight = weight;
    }
    public int getPlayerID(){return playerID;}
    public void setPlayerID(int playerID){this.playerID = playerID;}
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getYear() {
        return year;
    }
    public void setStanding(String standing) {
        this.year = year;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getFullName(){return firstName + " " +lastName;}

    public String getPlayerImage(){return playerImage;}
    public void setPlayerImage(String playerImage){this.playerImage = playerImage;}
}