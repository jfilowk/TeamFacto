package com.jfilowk.teamfactory.datasource;

import com.jfilowk.teamfactory.datasource.cache.callback.AnEventCacheCallback;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.callbacks.EventCallback;
import com.jfilowk.teamfactory.datasource.entities.Event;

/**
 * Created by Javi on 22/09/14.
 */
public interface EventDataSource {
  void createEvent(Event event, EventCallbackBase eventCallback);

  void getAllEvents(EventCallback eventCallback);

  void getEvent(Event event, AnEventCacheCallback eventCallback);

  void showEvent(Event event, AnEventCacheCallback eventCallback);

  void deleteEvent(long id, EventCallbackBase eventCallbackBase);
}
