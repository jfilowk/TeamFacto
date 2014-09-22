package com.jfilowk.teamfactory.datasource.entities;

/**
 * Created by Javi on 22/09/14.
 */
public class Event {

    int id;
    String type;
    int numberPlayers;
    int numberTeams;

    public Event() {
    }

    public Event(int id, String type, int numberPlayers, int numberTeams) {
        this.id = id;
        this.type = type;
        this.numberPlayers = numberPlayers;
        this.numberTeams = numberTeams;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberPlayers() {
        return numberPlayers;
    }

    public void setNumberPlayers(int numberPlayers) {
        this.numberPlayers = numberPlayers;
    }

    public int getNumberTeams() {
        return numberTeams;
    }

    public void setNumberTeams(int numberTeams) {
        this.numberTeams = numberTeams;
    }
}
