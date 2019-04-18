package com.example.topspin;

public class Player {

    // I would recommend dropping the standing attribute
    // and adding the attributes from the website at http://www.odusports.com/ViewArticle.dbml?ATCLID=210331019&DB_OEM_ID=31100&Q_SEASON=2018
    //The Extra entries would be classYear, Major, and High school all Strings and easy to use
    private String name;
    private String standing;
    private String hometown;
    private String height;
    private String weight;


    public Player(String name, String standing, String hometown, String height, String weight) {
        this.name = name;
        this.standing = standing;
        this.hometown = hometown;
        this.height = height;
        this.weight = weight;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStanding() {
        return standing;
    }
    public void setStanding(String standing) {
        this.standing = standing;
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
}