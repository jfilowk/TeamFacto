package com.jfilowk.teamfactory.ui.presenter;

import com.jfilowk.teamfactory.datasource.EventDataSource;
import com.jfilowk.teamfactory.datasource.EventDataSourceImpl;
import com.jfilowk.teamfactory.datasource.cache.callback.AnEventCacheCallback;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.ui.views.GenerateTeamView;

/**
 * Created by Javi on 23/09/14.
 */
public class GenerateTeamPresenterImpl implements GenerateTeamPresenter {

  GenerateTeamView view;
  EventDataSource eventDataSource;

  public GenerateTeamPresenterImpl(GenerateTeamView view) {
    this.view = view;
    eventDataSource = new EventDataSourceImpl();
  }

  @Override public void onResume(Event event) {
    this.view.initProgressFragment();
    this.eventDataSource.showEvent(event, new AnEventCacheCallback() {
      @Override public void onSuccess(Event event) {
        view.initMainFragment(event);
      }

      @Override public void onError() {
        view.initErrorFragment();
      }
    });
  }

  @Override public void onError() {

  }
}