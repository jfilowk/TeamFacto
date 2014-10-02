package com.jfilowk.teamfactory.datasource.entities;

import java.io.Serializable;

/**
 * Created by Javi on 26/09/14.
 */
public class Team implements Serializable {

    int id;
    String name;
    RandomUserCollection userCollection;

    public Team() {
        this.userCollection = new RandomUserCollection();
    }

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
        this.userCollection = new RandomUserCollection();
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
