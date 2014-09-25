package com.jfilowk.teamfactory.datasource;

import android.text.format.Time;

import com.jfilowk.teamfactory.datasource.api.RandomUserApi;
import com.jfilowk.teamfactory.datasource.api.RandomUserApiEvent;
import com.jfilowk.teamfactory.datasource.api.RandomUserApiEventImpl;
import com.jfilowk.teamfactory.datasource.api.RandomUserApiImpl;
import com.jfilowk.teamfactory.datasource.api.callback.RandomUserApiCallback;
import com.jfilowk.teamfactory.datasource.api.callback.RandomUserApiEventCallback;
import com.jfilowk.teamfactory.datasource.binder.RandomUserMapper;
import com.jfilowk.teamfactory.datasource.cache.EventCache;
import com.jfilowk.teamfactory.datasource.cache.EventCacheImpl;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCacheCallback;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.callbacks.EventCallback;
import com.jfilowk.teamfactory.datasource.callbacks.RandomUserCallback;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;
import com.jfilowk.teamfactory.datasource.entities.RandomUserCollection;
import com.terro.entities.UserRandomResponse;

import java.util.Date;
import java.util.List;

/**
 * Created by Javi on 22/09/14.
 */
public class EventDataSourceImpl implements EventDataSource {

    EventCache eventCache;
    RandomUserApiEvent randomUserApiEvent;
    RandomUserApi randomUserApi;

    public EventDataSourceImpl() {
        this.eventCache = new EventCacheImpl();
        this.randomUserApiEvent = new RandomUserApiEventImpl();
        this.randomUserApi = new RandomUserApiImpl();
    }

    @Override
    public void createEvent(Event event, EventCallbackBase eventCallback) {

        boolean insert = eventCache.createEvent(event);

        if (insert) {
            eventCallback.onSuccess();
        } else {
            eventCallback.onError();
        }
    }

    @Override
    public void getAllEvents(EventCallback eventCallback) {
        this.eventCache.getEvent(new EventCacheCallback() {
            @Override
            public void onSuccess(List<Event> list) {
                //Usar mapper y demás.
            }

            @Override
            public void onError() {

            }
        });

    }

    @Override
    public void getEvent(EventCallback eventCallback) {

    }

    @Override
    public void showEvent(RandomUserCollection collection, EventCallback eventCallback) {

    }

    @Override
    public void showEvent(EventCallback eventCallback) {

        Event event = new Event();
        Time now = new Time();
        now.setToNow();



        event.setId(1);
        event.setNumUser(collection.size());
        event.setNumTeams(2);
        event.setCreated_at(new Date());
        event.setListUsers(collection);
        event.setType("Futbol");

        EventCollection collectionEvent = new EventCollection();
        collectionEvent.add(event);

        eventCallback.onSuccess(collectionEvent);

    }

    @Override
    public void getUsers(final RandomUserApiEventCallback callback) {
        this.randomUserApiEvent.getRandomUsers(new RandomUserApiEventCallback() {
            @Override
            public void onSuccess(UserRandomResponse response) {
                callback.onSuccess(response);
            }

            @Override
            public void onError() {

            }
        });
    }
}
