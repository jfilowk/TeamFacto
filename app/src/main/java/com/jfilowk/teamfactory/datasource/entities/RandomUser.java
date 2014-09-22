package com.jfilowk.teamfactory.datasource.entities;

/**
 * Created by Javi on 21/09/14.
 */
public class RandomUser {

    String seed;
    String name;
    String picture;
    String gender;

    public RandomUser() {
    }

    public RandomUser(String name, String picture, String gender, String seed) {
        this.seed = seed;
        this.name = name;
        this.picture = picture;
        this.gender = gender;
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
