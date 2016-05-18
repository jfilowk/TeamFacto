package com.jfilowk.teamfactory.datasource;

import com.jfilowk.teamfactory.datasource.api.RandomUserApi;
import com.jfilowk.teamfactory.datasource.api.callback.RandomUserApiCallback;
import com.jfilowk.teamfactory.datasource.binder.RandomUserMapper;
import com.jfilowk.teamfactory.datasource.cache.EventCache;
import com.jfilowk.teamfactory.datasource.cache.callback.AnEventCacheCallback;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.callbacks.EventCallback;
import com.jfilowk.teamfactory.datasource.callbacks.RandomUserCallback;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;
import com.jfilowk.teamfactory.datasource.entities.RandomUserCollection;
import com.jfilowk.teamfactory.datasource.entities.Team;
import com.jfilowk.teamfactory.datasource.entities.TeamCollection;
import com.jfilowk.teamfactory.datasource.jobs.CreateEventJob;
import com.jfilowk.teamfactory.datasource.jobs.DeleteEventJob;
import com.jfilowk.teamfactory.datasource.jobs.GetEventJob;
import com.jfilowk.teamfactory.datasource.jobs.GetEventsJob;
import com.path.android.jobqueue.JobManager;
import com.terro.entities.UserRandomResponse;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by Javi on 22/09/14.
 */
public class EventDataSourceImpl implements EventDataSource {

  private EventCache eventCache;
  private RandomUserApi randomUserApi;
  private JobManager jobManager;
  private RandomUserMapper randomUserMapper;

  @Inject public EventDataSourceImpl(EventCache eventCache, RandomUserApi randomUserApi,
      JobManager jobManager, RandomUserMapper randomUserMapper) {
    this.eventCache = eventCache;
    this.randomUserApi = randomUserApi;
    this.jobManager = jobManager;
    this.randomUserMapper = randomUserMapper;
  }

  @Override public void createEvent(Event event, final EventCallbackBase eventCallback) {

    CreateEventJob job = new CreateEventJob(event, eventCache, new EventCallbackBase() {
      @Override public void onSuccess() {
        eventCallback.onSuccess();
      }

      @Override public void onError() {
        eventCallback.onError();
        Timber.e("Entro en el CreateEventEventSource");
      }
    });

    jobManager.addJobInBackground(job);
  }

  @Override public void getAllEvents(final EventCallback eventCallback) {
    jobManager.addJobInBackground(new GetEventsJob(eventCache, new EventCallback() {
      @Override public void onSuccess(EventCollection collection) {
        eventCallback.onSuccess(collection);
      }

      @Override public void onError() {
        eventCallback.onError();
      }
    }));
  }

  @Override public void getEvent(Event event, final AnEventCacheCallback eventCallback) {
    jobManager.addJobInBackground(new GetEventJob(event, eventCache, new AnEventCacheCallback() {
      @Override public void onSuccess(Event event) {
        eventCallback.onSuccess(event);
      }

      @Override public void onError() {
        eventCallback.onError();
      }
    }));
  }

  @Override public void showEvent(final Event event, final AnEventCacheCallback eventCallback) {
    if (event.getListTeams() == null) {
      createCompleteEvent(event, new AnEventCacheCallback() {
        @Override public void onSuccess(Event event) {
          eventCallback.onSuccess(event);
        }

        @Override public void onError() {
          eventCallback.onError();
        }
      });
    } else {
      eventCallback.onSuccess(event);
    }
  }

  private void createCompleteEvent(Event event, final AnEventCacheCallback callback) {
    TeamCollection teamCollection = new TeamCollection();
    int numberPlayers = event.getNumUser() / event.getNumTeams();
    for (int i = 0; i < event.getNumTeams(); i++) {
      final Team team = new Team();

      team.setId(i);
      team.setName("Team " + (char) ('A' + i));
      // TODO: 18/05/2016 revisar 
      populateTeam(numberPlayers, new RandomUserCallback() {
        @Override public void onSuccess(RandomUserCollection collection) {
          team.setUserCollection(collection);
        }

        @Override public void onError() {
          callback.onError();
        }
      });

      teamCollection.add(team);
    }

    event.setListTeams(teamCollection);
    callback.onSuccess(event);
  }

  private void populateTeam(int numberPlayers, final RandomUserCallback randomUserCallback) {
    this.randomUserApi.getRandomUserApi(numberPlayers, new RandomUserApiCallback() {
      @Override public void onSuccess(UserRandomResponse userRandomResponse) {

        RandomUserCollection randomUserCollection =
            randomUserMapper.transformResultToRandomUserCollection(userRandomResponse);

        randomUserCallback.onSuccess(randomUserCollection);
      }

      @Override public void onError() {
        randomUserCallback.onError();
      }
    });
  }

  @Override public void deleteEvent(long id, final EventCallbackBase eventCallbackBase) {
    jobManager.addJobInBackground(new DeleteEventJob(id, eventCache, new EventCallbackBase() {
      @Override public void onSuccess() {
        eventCallbackBase.onSuccess();
      }

      @Override public void onError() {
        eventCallbackBase.onError();
      }
    }));
  }
}
