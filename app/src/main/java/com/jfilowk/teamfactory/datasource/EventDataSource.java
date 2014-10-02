package com.jfilowk.teamfactory.datasource;

import com.jfilowk.teamfactory.datasource.cache.callback.AnEventCacheCallback;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.callbacks.EventCallback;
import com.jfilowk.teamfactory.datasource.entities.Event;

/**
 * Created by Javi on 22/09/14.
 */
public interface EventDataSource {
    public void createEvent (Event event, EventCallbackBase eventCallback);
    public void getAllEvents(EventCallback eventCallback);
    public void getEvent (Event event, AnEventCacheCallback eventCallback);
    public void showEvent(Event event, AnEventCacheCallback eventCallback);
}
