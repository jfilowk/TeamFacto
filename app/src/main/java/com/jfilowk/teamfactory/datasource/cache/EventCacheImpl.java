package com.jfilowk.teamfactory.datasource.cache;

import com.jfilowk.teamfactory.datasource.cache.callback.EventCacheCallback;
import com.jfilowk.teamfactory.datasource.cache.helper.EventDB;
import com.jfilowk.teamfactory.datasource.cache.helper.EventDBImpl;
import com.jfilowk.teamfactory.ui.TeamFactoApp;

/**
 * Created by Javi on 22/09/14.
 */
public class EventCacheImpl implements EventCache {

    private EventDB eventDB;

    @Override
    public void getEventCache(EventCacheCallback callback) {
        callback.onSuccess(null);
        eventDB.getAllEvents();
    }

    public void init(){
        eventDB = new EventDBImpl(TeamFactoApp.get());
    }
}
