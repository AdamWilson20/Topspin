package com.example.topspin;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Tournament {

    private int tID;
    private String oppTeam;
    private String location;
    private String date;
    private String time;
    private Boolean isHome = true;
    private Boolean isIndoor = true;

    public Tournament(){}


    public Tournament(int id, String oppTeam, String location, String date, String time, Boolean isHome) {
        this.tID = id;
        this.oppTeam = oppTeam;
        this.location = location;
        this.date = date;
        this.time = time;
        this.isHome= isHome;
        //this.isIndoor = isIndoor;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                ", oppTeam='" + oppTeam + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", isHome=" + isHome +
                ", isIndoor=" + isIndoor +
                '}';
    }


    public int gettID() {
        return tID;
    }

    public void settID(int tID) {
        this.tID = tID;
    }

    public String getOppTeam() {
        return oppTeam;
    }

    public void setOppTeam(String oppTeam) {
        this.oppTeam = oppTeam;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getIndoor() {
        return isIndoor;
    }

    public Boolean getHome() {
        return isHome;
    }

    public void setHome(Boolean home) {
        isHome = home;
    }

    public void setIndoor(Boolean indoor) {
        isIndoor = indoor;
    }
}
