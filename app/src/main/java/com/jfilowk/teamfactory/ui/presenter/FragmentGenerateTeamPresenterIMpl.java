package com.jfilowk.teamfactory.ui.presenter;

import com.jfilowk.teamfactory.datasource.EventDataSource;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.entities.Event;
import javax.inject.Inject;

/**
 * Created by Javi on 26/09/14.
 */
public class FragmentGenerateTeamPresenterImpl implements FragmentGenerateTeamPresenter {

  private FragmentGenerateTeamView view;
  private EventDataSource eventDataSource;

  @Inject public FragmentGenerateTeamPresenterImpl(EventDataSource eventDataSource) {
    this.eventDataSource = eventDataSource;
  }

  @Override public void onResume() {

  }

  @Override public void onError() {

  }

  @Override public void showTeams(Event event) {
    this.view.initListView(event);
  }

  @Override public void createEvent(Event event) {
    eventDataSource.createEvent(event, new EventCallbackBase() {
      @Override public void onSuccess() {

      }

      @Override public void onError() {
        view.initFragmentError();
      }
    });
  }

  @Override public void attachView(FragmentGenerateTeamView fragmentGenerateTeamView) {
    this.view = fragmentGenerateTeamView;
  }
}
