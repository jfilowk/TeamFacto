package com.jfilowk.teamfactory.ui.presenter;

import com.jfilowk.teamfactory.datasource.EventDataSource;
import com.jfilowk.teamfactory.datasource.callbacks.EventCallback;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;

/**
 * Created by Javi on 19/09/14.
 */
public class HomeActivityPresenterImpl implements HomeActivityPresenter {

  private EventDataSource eventDataSource;
  private HomeActivityView view;

  public HomeActivityPresenterImpl(EventDataSource eventDataSource) {
    this.eventDataSource = eventDataSource;
  }

  @Override public void onResume() {
    this.view.initProgressFragment();

    this.eventDataSource.getAllEvents(new EventCallback() {
      @Override public void onSuccess(EventCollection collection) {
        view.initMainFragment(collection);
      }

      @Override public void onError() {
        view.initErrorFragment();
      }
    });
  }

  @Override public void selectTeam() {
    this.view.initSelectTeamFragment();
  }

  @Override public void attachView(HomeActivityView homeActivityView) {
    this.view = homeActivityView;
  }
}
