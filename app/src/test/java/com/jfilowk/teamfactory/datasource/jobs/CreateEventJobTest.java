package com.jfilowk.teamfactory.datasource.jobs;

import com.jfilowk.teamfactory.datasource.cache.EventCache;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.entities.Event;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateEventJobTest {

  @Mock Event event;
  @Mock EventCallbackBase listener;
  @Mock EventCache eventCache;

  private CreateEventJob createEventJob;

  @Before public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    createEventJob = new CreateEventJob(event, eventCache, listener);
  }

  @Test public void testOnRun() throws Throwable {
    when(eventCache.createEvent(event)).thenReturn(true);
    createEventJob.onRun();
    verify(listener).onSuccess();
  }

  @Test public void testOnRun_onError() throws Throwable {
    when(eventCache.createEvent(event)).thenReturn(false);
    createEventJob.onRun();
    verify(listener).onError();
  }
}