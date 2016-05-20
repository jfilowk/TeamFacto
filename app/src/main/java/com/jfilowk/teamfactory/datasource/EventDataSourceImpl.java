package com.jfilowk.teamfactory.datasource;

import android.support.annotation.NonNull;
import com.jfilowk.teamfactory.datasource.api.RandomUserApi;
import com.jfilowk.teamfactory.datasource.api.tasks.GetRandomUserList;
import com.jfilowk.teamfactory.datasource.api.tasks.GetRandomUserListImpl;
import com.jfilowk.teamfactory.datasource.binder.RandomUserMapper;
import com.jfilowk.teamfactory.datasource.cache.EventCache;
import com.jfilowk.teamfactory.datasource.cache.callback.AnEventCacheCallback;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.callbacks.EventCallback;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;
import com.jfilowk.teamfactory.datasource.entities.RandomUser;
import com.jfilowk.teamfactory.datasource.entities.RandomUserCollection;
import com.jfilowk.teamfactory.datasource.entities.Team;
import com.jfilowk.teamfactory.datasource.entities.TeamCollection;
import com.jfilowk.teamfactory.datasource.jobs.CreateEventJob;
import com.jfilowk.teamfactory.datasource.jobs.DeleteEventJob;
import com.jfilowk.teamfactory.datasource.jobs.GetEventJob;
import com.jfilowk.teamfactory.datasource.jobs.GetEventsJob;
import com.path.android.jobqueue.JobManager;
import com.terro.entities.UserRandomResponse;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.inject.Inject;

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
    eventCallback.onSuccess(event);
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

  @Override public void generateEvent(Event event, final AnEventCacheCallback eventCacheCallback) {
    populateEventWithTeams(event, new AnEventCacheCallback() {
      @Override public void onSuccess(Event event) {
        eventCacheCallback.onSuccess(event);
      }

      @Override public void onError() {
        eventCacheCallback.onError();
      }
    });
  }

  private void populateEventWithTeams(Event event, final AnEventCacheCallback callback) {
    RandomUserCollection randomUserCollection = null;

    try {
      randomUserCollection = getRandomUserCollection(event);
    } catch (InterruptedException | ExecutionException e) {
      callback.onError();
      return;
    }

    if (randomUserCollection == null) {
      callback.onError();
      return;
    }

    TeamCollection teamCollection = new TeamCollection();

    for (int i = 0; i < event.getNumTeams(); i++) {
      Team team = getTeam(randomUserCollection, event.getNumberPlayerPerTeam(), i);
      teamCollection.add(team);
    }

    event.setListTeams(teamCollection);
    callback.onSuccess(event);
  }

  private RandomUserCollection getRandomUserCollection(Event event)
      throws InterruptedException, ExecutionException {
    final ExecutorService threadpool = Executors.newFixedThreadPool(3);

    GetRandomUserList getRandomUserList =
        new GetRandomUserListImpl(randomUserApi, event.getNumUser());

    Future future = threadpool.submit(getRandomUserList);

    while (!future.isDone()) {
      System.out.println("Task is not completed yet....");
      Thread.sleep(1); //sleep for 1 millisecond before checking again }
    }

    threadpool.shutdown();
    UserRandomResponse randomUser = (UserRandomResponse) future.get();
    if (randomUser == null) {
      return null;
    }
    return randomUserMapper.transformResultToRandomUserCollection(randomUser);
  }

  @NonNull
  private Team getTeam(RandomUserCollection randomUserCollection, int numPlayerPerTeam, int i) {
    int start = (i * numPlayerPerTeam);
    int end = start + numPlayerPerTeam;
    List<RandomUser> subList = randomUserCollection.getCollection().subList(start, end);
    Team team = new Team();
    team.setId(i);
    team.setName("Team " + (char) ('A' + i));
    RandomUserCollection subUserCollection = new RandomUserCollection();
    subUserCollection.addAll(subList);
    team.setUserCollection(subUserCollection);
    return team;
  }
}
