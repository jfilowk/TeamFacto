package com.jfilowk.teamfactory.datasource.entities;

import java.io.Serializable;

/**
 * Created by Javi on 21/09/14.
 */
public class RandomUser implements Serializable {

    int id;
    String firstName;
    String lastName;
    String gender;
    String picture;
    String seed;
    String created_at;

    public RandomUser() {

    }

    public RandomUser(String firstName, int id, String lastName, String gender, String picture, String seed, String created_at) {
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.picture = picture;
        this.seed = seed;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
