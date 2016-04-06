package com.jfilowk.teamfactory.datasource.cache.callback;

import com.jfilowk.teamfactory.datasource.entities.EventCollection;

/**
 * Created by Javi on 22/09/14.
 */
public interface EventCacheCallback {
  public void onSuccess(EventCollection eventCollection);

  public void onError();
}
