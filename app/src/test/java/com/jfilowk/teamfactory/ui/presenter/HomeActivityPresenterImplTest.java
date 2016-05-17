package com.jfilowk.teamfactory.ui.presenter;

import com.jfilowk.teamfactory.datasource.EventDataSource;
import com.jfilowk.teamfactory.datasource.callbacks.EventCallback;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class HomeActivityPresenterImplTest {

  @Mock HomeActivityPresenter.HomeActivityView view;
  @Mock EventDataSource eventDataSource;

  @Captor ArgumentCaptor<EventCallback> eventCallbackArgumentCaptor;

  private HomeActivityPresenter presenter;

  @Before public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    presenter = new HomeActivityPresenterImpl(eventDataSource);
    presenter.attachView(view);
  }

  @Test public void onResume_withEvents() {
    presenter.onResume();

    verify(view).initProgressFragment();

    verify(eventDataSource).getAllEvents(eventCallbackArgumentCaptor.capture());

    eventCallbackArgumentCaptor.getValue().onSuccess(new EventCollection());

    verify(view).initMainFragment(any(EventCollection.class));
  }

  @Test public void onResume_withoutEvents() {

    presenter.onResume();

    verify(view).initProgressFragment();

    verify(eventDataSource).getAllEvents(eventCallbackArgumentCaptor.capture());

    eventCallbackArgumentCaptor.getValue().onError();

    verify(view).initErrorFragment();
  }

  @Test public void testSelectTeam() {
    presenter.selectTeam();

    verify(view).initSelectTeamFragment();
  }
}