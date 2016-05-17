package com.jfilowk.teamfactory.datasource;

import com.jfilowk.teamfactory.datasource.api.RandomUserApi;
import com.jfilowk.teamfactory.datasource.cache.EventCache;
import com.jfilowk.teamfactory.datasource.cache.callback.AnEventCacheCallback;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.callbacks.EventCallback;
import com.path.android.jobqueue.JobManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EventDataSourceImplTest {

  @Mock EventCache eventCache;
  @Mock RandomUserApi randomUserApi;
  @Mock JobManager jobManager;
  @Mock EventCallbackBase eventCallbackBase;

  @Captor ArgumentCaptor<EventCallbackBase> eventCallbackBaseArgumentCaptor;
  @Captor ArgumentCaptor<EventCallback> eventCallbackArgumentCaptor;
  @Captor ArgumentCaptor<AnEventCacheCallback> eventCacheCallbackArgumentCaptor;

  private EventDataSource eventDataSource;

  @Before public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    eventDataSource = new EventDataSourceImpl(eventCache, randomUserApi, jobManager);
  }

  @Test public void testCreateEvent() throws Exception {

  }

  @Test public void testGetAllEvents() throws Exception {

  }

  @Test public void testGetEvent() throws Exception {

  }

  @Test public void testShowEvent() throws Exception {

  }

  @Test public void testDeleteEvent() throws Exception {

  }
}