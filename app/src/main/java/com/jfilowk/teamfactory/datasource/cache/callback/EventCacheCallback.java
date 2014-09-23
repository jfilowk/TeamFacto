package com.jfilowk.teamfactory.datasource.cache.callback;

import com.jfilowk.teamfactory.datasource.entities.Event;

import java.util.List;

/**
 * Created by Javi on 22/09/14.
 */
public interface EventCacheCallback {
    public void onSuccess (List<Event> list);
    public void onError ();

}
