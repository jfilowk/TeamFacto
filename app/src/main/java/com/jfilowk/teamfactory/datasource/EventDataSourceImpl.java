package com.jfilowk.teamfactory.datasource;

import com.jfilowk.teamfactory.datasource.api.RandomUserApi;
import com.jfilowk.teamfactory.datasource.api.RandomUserApiImpl;
import com.jfilowk.teamfactory.datasource.api.callback.RandomUserApiCallback;
import com.jfilowk.teamfactory.datasource.binder.RandomUserMapper;
import com.jfilowk.teamfactory.datasource.cache.EventCache;
import com.jfilowk.teamfactory.datasource.cache.EventCacheImpl;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCacheCallback;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.callbacks.EventCallback;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;
import com.jfilowk.teamfactory.datasource.entities.RandomUser;
import com.jfilowk.teamfactory.datasource.entities.RandomUserCollection;
import com.jfilowk.teamfactory.datasource.entities.Team;
import com.jfilowk.teamfactory.datasource.entities.TeamCollection;
import com.terro.entities.UserRandomResponse;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Javi on 22/09/14.
 */
public class EventDataSourceImpl implements EventDataSource {

    EventCache eventCache;
    RandomUserApi randomUserApi;

    public EventDataSourceImpl() {
        this.eventCache = new EventCacheImpl();
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
    public void showEvent(final EventCallback eventCallback) {

        this.randomUserApi.getRandomUserApi(new RandomUserApiCallback() {
            @Override
            public void onSuccess(UserRandomResponse response) {
                RandomUserMapper mapper = new RandomUserMapper();
                RandomUserCollection responseCollection = mapper.transformResultToRandomUserCollection(response);

                //Create teams.
                Event event = new Event();
                int numUsers = 8; // 4 users each team
                int numTeams = 2; // 2 teams
                TeamCollection teamCollection = new TeamCollection();
                for (int i = 0; i < numTeams; i++) {
                    Team team = new Team();
                    team.setId(i);
                    team.setName("Team "+(char)('A' + i));
                    RandomUserCollection userCollection = new RandomUserCollection();
                    for (int j = 0; j < numUsers/numTeams; j++) {
                        RandomUser userTemp = responseCollection.get(j);
                        userCollection.add(userTemp);
                    }
                    team.setUserCollection(userCollection);
                    teamCollection.add(team);
                }
                event.setType("Sport");
                event.setListTeams(teamCollection);
                Calendar rightNow = Calendar.getInstance();
                event.setCreated_at(rightNow.getTime());
                event.setId(1);
                EventCollection eventCollection = new EventCollection();
                eventCollection.add(event);
                eventCallback.onSuccess(eventCollection);
            }

            @Override
            public void onError() {

            }
        });

    }
}
