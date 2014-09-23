package com.jfilowk.teamfactory.datasource.entities;

/**
 * Created by Javi on 21/09/14.
 */
public class RandomUser {

    int id;
    String seed;
    String name;
    String picture;
    String gender;

    public RandomUser() {
    }

    public RandomUser(int id, String name, String picture, String gender, String seed) {
        this.id = id;
        this.seed = seed;
        this.name = name;
        this.picture = picture;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
