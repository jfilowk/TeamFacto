package com.jfilowk.teamfactory.datasource.cache.callback;

import com.jfilowk.teamfactory.datasource.entities.Event;

/**
 * Created by Javi on 25/09/14.
 */
public interface AnEventCacheCallback {
  void onSuccess(Event event);

  void onError();
}
