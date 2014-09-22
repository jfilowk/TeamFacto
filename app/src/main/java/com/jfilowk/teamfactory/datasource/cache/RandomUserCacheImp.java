package com.jfilowk.teamfactory.datasource.cache;

import com.jfilowk.teamfactory.datasource.cache.callback.RandomUserCacheCallback;
import com.jfilowk.teamfactory.datasource.cache.helper.DatabaseHelper;
import com.jfilowk.teamfactory.datasource.entities.RandomUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jose Luis on 19/09/14.
 */
public class RandomUserCacheImp implements RandomUserCache {

    DatabaseHelper db;
    List<RandomUser> list;
  @Override public void getRandomUserCache(RandomUserCacheCallback callback) {

      list = db.getAllUsers();

      callback.onSuccess(list);

  }

    public void init(){
        db = new DatabaseHelper(null);
        list = new ArrayList<RandomUser>();
    }
}
