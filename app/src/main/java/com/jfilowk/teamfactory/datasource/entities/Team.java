package com.jfilowk.teamfactory.datasource.entities;

/**
 * Created by Javi on 26/09/14.
 */
public class Team {

    int id;
    String name;
    RandomUserCollection userCollection;

    public Team() {
    }

    public Team (int id, String name, RandomUserCollection userCollection) {
        this.id = id;
        this.name = name;
        this.userCollection = userCollection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RandomUserCollection getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(RandomUserCollection userCollection) {
        this.userCollection = userCollection;
    }
}
