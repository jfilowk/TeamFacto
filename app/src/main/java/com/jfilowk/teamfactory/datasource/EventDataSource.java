package com.jfilowk.teamfactory.datasource;

import com.jfilowk.teamfactory.datasource.api.callback.RandomUserApiEventCallback;
import com.jfilowk.teamfactory.datasource.cache.callback.AnEventCacheCallback;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.callbacks.EventCallback;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.datasource.entities.RandomUserCollection;

/**
 * Created by Javi on 22/09/14.
 */
public interface EventDataSource {
    public void createEvent (Event event, EventCallbackBase eventCallback);
    public void getAllEvents(EventCallback eventCallback);
    public void getEvent (EventCallback eventCallback);
    public void getUsers (RandomUserApiEventCallback callback);
    public void showEvent(RandomUserCollection collection, AnEventCacheCallback eventCallback);

}
