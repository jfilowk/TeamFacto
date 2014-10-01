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
import com.jfilowk.teamfactory.datasource.jobs.CreateEventJob;
import com.jfilowk.teamfactory.ui.TeamFactoApp;
import com.path.android.jobqueue.JobManager;
import com.terro.entities.UserRandomResponse;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Javi on 22/09/14.
 */
public class EventDataSourceImpl implements EventDataSource {

    EventCache eventCache;
    RandomUserApi randomUserApi;
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
        this.eventCache.getEvent(new EventCacheCallback() {
            @Override
            public void onSuccess(EventCollection eventCollection) {
                eventCallback.onSuccess(eventCollection);
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
                int x = 0;
                TeamCollection teamCollection = new TeamCollection();
                for (int i = 0; i < numTeams; i++) {
                    Team team = new Team();
                    team.setId(i);
                    team.setName("Team "+(char)('A' + i));
                    RandomUserCollection userCollection = new RandomUserCollection();
                    for (int j = 0; j < numUsers/numTeams; j++) {
                        System.out.println(x);
                        RandomUser userTemp = responseCollection.get(x);
                        userCollection.add(userTemp);
                        x++;
                    }
                    team.setUserCollection(userCollection);
                    teamCollection.add(team);
                }
                event.setType("Sport");
                event.setListTeams(teamCollection);
                Calendar rightNow = Calendar.getInstance();
                Date date = Calendar.getInstance().getTime();
                event.setCreated_at(date.toString());
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
