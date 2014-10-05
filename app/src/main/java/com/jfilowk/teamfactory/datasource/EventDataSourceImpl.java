package com.jfilowk.teamfactory.datasource;

import com.jfilowk.teamfactory.datasource.api.RandomUserApi;
import com.jfilowk.teamfactory.datasource.api.RandomUserApiImpl;
import com.jfilowk.teamfactory.datasource.api.callback.RandomUserApiCallback;
import com.jfilowk.teamfactory.datasource.binder.RandomUserMapper;
import com.jfilowk.teamfactory.datasource.cache.EventCache;
import com.jfilowk.teamfactory.datasource.cache.EventCacheImpl;
import com.jfilowk.teamfactory.datasource.cache.callback.AnEventCacheCallback;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCacheCallback;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.callbacks.EventCallback;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;
import com.jfilowk.teamfactory.datasource.entities.RandomUser;
import com.jfilowk.teamfactory.datasource.entities.RandomUserCollection;
import com.jfilowk.teamfactory.datasource.entities.Team;
import com.jfilowk.teamfactory.datasource.entities.TeamCollection;
import com.jfilowk.teamfactory.datasource.jobs.CreateEventJob;
import com.jfilowk.teamfactory.datasource.jobs.GetEventJob;
import com.jfilowk.teamfactory.ui.TeamFactoApp;
import com.path.android.jobqueue.JobManager;
import com.terro.entities.UserRandomResponse;

/**
 * Created by Javi on 22/09/14.
 */
public class EventDataSourceImpl implements EventDataSource {

    private EventCache eventCache;
    private RandomUserApi randomUserApi;
    private JobManager jobManager;

    public EventDataSourceImpl() {
        this.eventCache = new EventCacheImpl();
        this.randomUserApi = new RandomUserApiImpl();
        jobManager = TeamFactoApp.get().getJobManager();
    }

    @Override
    public void createEvent(Event event, final EventCallbackBase eventCallback) {
        jobManager.addJobInBackground(new CreateEventJob(event, eventCache, new EventCallbackBase() {
            @Override
            public void onSuccess() {
                eventCallback.onSuccess();
            }

            @Override
            public void onError() {
                eventCallback.onError();
            }
        }));
    }

    @Override
    public void getAllEvents(final EventCallback eventCallback) {
        this.eventCache.getEvents(new EventCacheCallback() {
            @Override
            public void onSuccess(EventCollection eventCollection) {
                eventCallback.onSuccess(eventCollection);
            }

            @Override
            public void onError() {
                eventCallback.onError();
            }
        });

    }

    @Override
    public void getEvent(Event event, final AnEventCacheCallback eventCallback) {
        jobManager.addJobInBackground(new GetEventJob(event, eventCache, new AnEventCacheCallback() {
            @Override
            public void onSuccess(Event event) {
                eventCallback.onSuccess(event);
            }

            @Override
            public void onError() {
                eventCallback.onError();
            }
        }));

    }

    @Override
    public void showEvent(Event event, final AnEventCacheCallback eventCallback) {
        if  (event.getListTeams() == null){
            final Event eventReturn = event;
            this.randomUserApi.getRandomUserApi(new RandomUserApiCallback() {
                @Override
                public void onSuccess(UserRandomResponse response) {
                    RandomUserMapper mapper = new RandomUserMapper();
                    RandomUserCollection responseCollection = mapper.transformResultToRandomUserCollection(response);

                    int x = 0;

                    TeamCollection teamCollection = new TeamCollection();
                    for (int i = 0; i < eventReturn.getNumTeams(); i++) {
                        Team team = new Team();
                        team.setId(i);
                        team.setName("Team " + (char) ('A' + i)); //TODO: You are the fucking boss for this code!! Rocks!
                        RandomUserCollection userCollection = new RandomUserCollection();
                        int numberOfPlayers = eventReturn.getNumUser() / eventReturn.getNumTeams();
                        for (int j = 0; j < numberOfPlayers; j++) {
                            System.out.println(x);
                            RandomUser userTemp = responseCollection.get(x);
                            userCollection.add(userTemp);
                            x++;
                        }
                        team.setUserCollection(userCollection);
                        teamCollection.add(team);
                    }
                    eventReturn.setListTeams(teamCollection);
                    eventCallback.onSuccess(eventReturn);
                }

                @Override
                public void onError() {

                }
            });

        } else{
            eventCallback.onSuccess(event);
        }
    }
}
