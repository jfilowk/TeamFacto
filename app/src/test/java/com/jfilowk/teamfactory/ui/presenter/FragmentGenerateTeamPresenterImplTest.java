package com.jfilowk.teamfactory.ui.presenter;

import com.jfilowk.teamfactory.datasource.EventDataSource;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.entities.Event;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class FragmentGenerateTeamPresenterImplTest {

  @Mock FragmentGenerateTeamPresenter.FragmentGenerateTeamView view;
  @Mock EventDataSource eventDataSource;

  @Captor ArgumentCaptor<EventCallbackBase> eventCallbackBaseArgumentCaptor;

  private FragmentGenerateTeamPresenter presenter;

  @Before public void setUp() throws Exception {

    MockitoAnnotations.initMocks(this);

    presenter = new FragmentGenerateTeamPresenterImpl(eventDataSource);
    presenter.attachView(view);
  }

  @Test public void testShowTeams() throws Exception {
    Event event = new Event();
    presenter.showTeams(event);

    verify(view).initListView(event);
  }

  @Test public void testCreateEvent() throws Exception {
    Event event = new Event();
    presenter.createEvent(event);

    verify(eventDataSource).createEvent(eq(event), eventCallbackBaseArgumentCaptor.capture());
    eventCallbackBaseArgumentCaptor.getValue().onSuccess();
  }

  @Test public void testCreateEvent_Error() throws Exception {
    Event event = new Event();
    presenter.createEvent(event);

    verify(eventDataSource).createEvent(eq(event), eventCallbackBaseArgumentCaptor.capture());
    eventCallbackBaseArgumentCaptor.getValue().onError();

    verify(view).initFragmentError();
  }
}