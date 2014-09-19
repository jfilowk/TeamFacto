package com.jfilowk.teamfactory.datasource.cache;

import com.jfilowk.teamfactory.datasource.cache.callback.RandomUserCacheCallback;
import com.jfilowk.teamfactory.datasource.callbacks.RandomUserCallback;

/**
 * Created by Jose Luis on 19/09/14.
 */
public class RandomUserCacheImp implements RandomUserCache {
  @Override public void getRandomUserCache(RandomUserCacheCallback callback) {
      callback.onSuccess();
  }
}
