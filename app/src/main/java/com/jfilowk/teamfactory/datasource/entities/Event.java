package com.jfilowk.teamfactory.datasource.entities;

/**
 * Created by Javi on 22/09/14.
 */
public class Event {

    int id;
    String type;
    int numberPlayers;
    int numberTeams;
    String created_at;

    public Event() {
    }

    public Event(int id, String type, int numberPlayers, int numberTeams, String created_at) {
        this.id = id;
        this.type = type;
        this.numberPlayers = numberPlayers;
        this.numberTeams = numberTeams;
        this.created_at = created_at;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
