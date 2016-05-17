package com.jfilowk.teamfactory.datasource.jobs;

import com.jfilowk.teamfactory.datasource.cache.EventCache;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCacheCallback;
import com.jfilowk.teamfactory.datasource.callbacks.EventCallback;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class GetEventsJobTest {

  @Mock EventCache eventCache;
  @Mock EventCallback eventCallback;

  @Captor ArgumentCaptor<EventCacheCallback> eventCacheCallbackArgumentCaptor;

  private GetEventsJob getEventsJob;

  @Before public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    getEventsJob = new GetEventsJob(eventCache, eventCallback);
  }

  @Test public void testOnRun() throws Throwable {
    getEventsJob.onRun();

    verify(eventCache).getEvents(eventCacheCallbackArgumentCaptor.capture());

    EventCollection eventCollection = new EventCollection();
    eventCacheCallbackArgumentCaptor.getValue().onSuccess(eventCollection);

    verify(eventCallback).onSuccess(eventCollection);
  }

  @Test public void testOnRun_onError() throws Throwable {
    getEventsJob.onRun();

    verify(eventCache).getEvents(eventCacheCallbackArgumentCaptor.capture());

    eventCacheCallbackArgumentCaptor.getValue().onError();

    verify(eventCallback).onError();
  }
}