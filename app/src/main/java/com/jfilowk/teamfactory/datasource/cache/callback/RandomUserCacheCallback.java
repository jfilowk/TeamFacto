package com.jfilowk.teamfactory.datasource.cache.callback;

import com.jfilowk.teamfactory.datasource.entities.RandomUser;

import java.util.List;

/**
 * Created by Javi on 19/09/14.
 */
public interface RandomUserCacheCallback {
  public void onSuccess (List<RandomUser> response);
  public void onError ();
}
