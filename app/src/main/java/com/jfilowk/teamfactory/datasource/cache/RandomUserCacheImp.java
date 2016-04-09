package com.jfilowk.teamfactory.datasource.cache;

import com.jfilowk.teamfactory.datasource.cache.callback.RandomUserCacheCallback;
import com.jfilowk.teamfactory.datasource.cache.helper.RandomUserDB;
import com.jfilowk.teamfactory.datasource.cache.helper.RandomUserDBImpl;
import com.jfilowk.teamfactory.datasource.entities.RandomUser;
import com.jfilowk.teamfactory.ui.TeamFactoApp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javi on 19/09/14.
 */
public class RandomUserCacheImp implements RandomUserCache {

  RandomUserDB randomUserDB;
  List<RandomUser> list;

  public RandomUserCacheImp() {
    init();
  }

  @Override public boolean createUser(RandomUser user, long idTeam) {

    long id = randomUserDB.createRandomUser(user, idTeam);
    System.out.println("He sido insertado" + id);

    if (id != -1) {
      return true;
    } else {
      return false;
    }
  }

  @Override public void getRandomUserCache(RandomUserCacheCallback callback) {

    callback.onSuccess(list);
  }

  public void init() {

    randomUserDB = new RandomUserDBImpl(TeamFactoApp.get());
    list = new ArrayList<RandomUser>();
  }
}
