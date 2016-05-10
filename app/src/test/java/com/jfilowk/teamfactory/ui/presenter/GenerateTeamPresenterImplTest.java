package com.jfilowk.teamfactory.ui.presenter;

import com.jfilowk.teamfactory.datasource.EventDataSource;
import com.jfilowk.teamfactory.datasource.cache.callback.AnEventCacheCallback;
import com.jfilowk.teamfactory.datasource.entities.Event;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class GenerateTeamPresenterImplTest {

  @Mock GenerateTeamPresenter.GenerateTeamView view;

  @Mock EventDataSource eventDataSource;

  @Captor
  ArgumentCaptor<AnEventCacheCallback> eventCacheCallback;

  private GenerateTeamPresenter presenter;


  @Before public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    presenter = new GenerateTeamPresenterImpl(eventDataSource);
    presenter.attachView(view);
  }

  @Test public void testOnResume() throws Exception {
    Event event = new Event();
    presenter.onResume(event);
    verify(view).initProgressFragment();

    verify(eventDataSource).showEvent(any(Event.class), eventCacheCallback.capture());
    Event event1 = new Event();
    eventCacheCallback.getValue().onSuccess(event1);

    verify(view).initMainFragment(event1);
  }

  @Test public void testOnResumeOnError() throws Exception {
    Event event = new Event();
    presenter.onResume(event);
    verify(view).initProgressFragment();

    verify(eventDataSource).showEvent(any(Event.class), eventCacheCallback.capture());
    Event event1 = new Event();
    eventCacheCallback.getValue().onError();

    verify(view).initErrorFragment();
  }
}