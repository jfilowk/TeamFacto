package com.jfilowk.teamfactory.ui.presenter;

import com.jfilowk.teamfactory.datasource.EventDataSource;
import com.jfilowk.teamfactory.datasource.cache.callback.AnEventCacheCallback;
import com.jfilowk.teamfactory.datasource.entities.Event;
import javax.inject.Inject;

/**
 * Created by Javi on 23/09/14.
 */
public class GenerateTeamPresenterImpl implements GenerateTeamPresenter {

  GenerateTeamView view;
  EventDataSource eventDataSource;

  @Inject public GenerateTeamPresenterImpl(EventDataSource eventDataSource) {
    this.eventDataSource = eventDataSource;
  }

  @Override public void onResume(Event event) {
    this.view.initProgressFragment();

    if (event.getListTeams() == null) {
      this.eventDataSource.generateEvent(event, new AnEventCacheCallback() {
        @Override public void onSuccess(Event event) {
          view.initMainFragment(event);
        }

        @Override public void onError() {
          view.initErrorFragment();
        }
      });
    } else {
      this.eventDataSource.showEvent(event, new AnEventCacheCallback() {
        @Override public void onSuccess(Event event) {
          view.initMainFragment(event);
        }

        @Override public void onError() {
          view.initErrorFragment();
        }
      });
    }
  }

  @Override public void onError() {

  }

  @Override public void attachView(GenerateTeamView generateTeamView) {
    this.view = generateTeamView;
  }
}