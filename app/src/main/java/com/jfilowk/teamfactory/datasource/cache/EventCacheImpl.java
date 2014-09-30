package com.jfilowk.teamfactory.datasource.cache;

import com.jfilowk.teamfactory.datasource.cache.callback.EventCacheCallback;
import com.jfilowk.teamfactory.datasource.cache.helper.EventDB;
import com.jfilowk.teamfactory.datasource.cache.helper.EventDBImpl;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.ui.TeamFactoApp;

/**
 * Created by Javi on 22/09/14.
 */
public class EventCacheImpl implements EventCache {

    private EventDB eventDB;

    @Override
    public boolean createEvent(Event event) {

        long id = eventDB.createEvent(event);

        if (id != -1) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void getEvent(EventCacheCallback callback) {
        eventDB.getAllEvents();
        callback.onSuccess(null);
    }

    public void init() {
        eventDB = new EventDBImpl(TeamFactoApp.get());
    }
}
