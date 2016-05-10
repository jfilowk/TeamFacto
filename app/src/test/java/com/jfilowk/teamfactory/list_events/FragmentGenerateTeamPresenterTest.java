package com.jfilowk.teamfactory.list_events;

import com.jfilowk.teamfactory.datasource.EventDataSource;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.ui.presenter.FragmentGenerateTeamPresenter;
import com.jfilowk.teamfactory.ui.presenter.FragmentGenerateTeamPresenterImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;


public class FragmentGenerateTeamPresenterTest {

  @Mock EventDataSource eventDataSource;

  @Mock FragmentGenerateTeamPresenter.FragmentGenerateTeamView fragmentGenerateTeamView;

  @Captor
  private ArgumentCaptor<EventCallbackBase> eventCallbackBaseArgumentCaptor;

  private FragmentGenerateTeamPresenter presenter;

  @Before
  public void setupGenerateTeamPresenter (){
    MockitoAnnotations.initMocks(this);

    presenter = new FragmentGenerateTeamPresenterImpl(eventDataSource);
    presenter.attachView(fragmentGenerateTeamView);

  }

  @Test
  public void loadObjects_ShowList(){
    Event event = new Event();
    presenter.showTeams(event);

    verify(fragmentGenerateTeamView).initListView(event);
  }

  @Test
  public void createEvent_checkSuccess(){
    Event event = new Event();
    presenter.createEvent(event);

    verify(eventDataSource).createEvent(any(Event.class), eventCallbackBaseArgumentCaptor.capture());

    eventCallbackBaseArgumentCaptor.getValue().onSuccess();

    verify(fragmentGenerateTeamView).createdOk();
  }

  @Test
  public void createEvent_checkError() {
    presenter.createEvent(new Event());

    verify(eventDataSource).createEvent(any(Event.class), eventCallbackBaseArgumentCaptor.capture());

    eventCallbackBaseArgumentCaptor.getValue().onError();

    verify(fragmentGenerateTeamView).initFragmentError();
  }
}
