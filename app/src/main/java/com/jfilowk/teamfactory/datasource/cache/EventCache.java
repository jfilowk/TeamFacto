package com.jfilowk.teamfactory.datasource.cache;

import com.jfilowk.teamfactory.datasource.cache.callback.EventCacheCallback;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.entities.Event;

/**
 * Created by Javi on 22/09/14.
 */
public interface EventCache {
    public boolean createEvent (Event event);
    public void getEvents (EventCacheCallback callback);
    public void getEvent (Event event, EventCallbackBase callback);
}
