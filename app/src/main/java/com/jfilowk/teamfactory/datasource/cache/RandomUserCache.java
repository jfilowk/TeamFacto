package com.jfilowk.teamfactory.datasource.cache;

import com.jfilowk.teamfactory.datasource.cache.callback.RandomUserCacheCallback;

/**
 * Created by Jose Luis on 19/09/14.
 */
public interface RandomUserCache {
  public void getRandomUserCache (RandomUserCacheCallback callback);
}
