package com.jfilowk.teamfactory.datasource.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javi on 21/09/14.
 */
public class RandomUserCollection implements Serializable {

  private List<RandomUser> list;

  public RandomUserCollection() {
    list = new ArrayList<RandomUser>();
  }

  public RandomUser get(int position) {
    return list.get(position);
  }

  public void add(RandomUser randomUser) {
    list.add(randomUser);
  }

  public void addAll(List<RandomUser> randomUsers) {
    list.addAll(randomUsers);
  }

  public List<RandomUser> getCollection() {
    return this.list;
  }

  public int size() {
    return this.list.size();
  }
}
