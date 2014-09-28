package com.jfilowk.teamfactory.datasource.entities;

import java.util.Date;

/**
 * Created by Javi on 22/09/14.
 */
public class Event {

    int id;
    String type;
    int numUser;
    int numTeams;
    TeamCollection listTeams;
    Date created_at;

    public Event() {

    }

    public Event(int id, String type, int numUser, int numTeams, TeamCollection listTeams, Date created_at) {
        this.id = id;
        this.type = type;
        this.numUser = numUser;
        this.numTeams = numTeams;
        this.listTeams = listTeams;
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

    public int getNumUser() {
        return numUser;
    }

    public void setNumUser(int numUser) {
        this.numUser = numUser;
    }

    public int getNumTeams() {
        return numTeams;
    }

    public void setNumTeams(int numTeams) {
        this.numTeams = numTeams;
    }

    public TeamCollection getListTeams() {
        return listTeams;
    }

    public void setListTeams(TeamCollection listTeams) {
        this.listTeams = listTeams;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
