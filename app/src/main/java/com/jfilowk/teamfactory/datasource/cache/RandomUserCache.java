package com.jfilowk.teamfactory.datasource.cache;

import com.jfilowk.teamfactory.datasource.cache.callback.RandomUserCacheCallback;
import com.jfilowk.teamfactory.datasource.entities.RandomUser;

/**
 * Created by Jose Luis on 19/09/14.
 */
public interface RandomUserCache {
  public boolean createUser (RandomUser user, long idTeam);
  public void getRandomUserCache (RandomUserCacheCallback callback);
}
