package com.jfilowk.teamfactory.ui.presenter;

import com.jfilowk.teamfactory.datasource.entities.EventCollection;

/**
 * Created by Javi on 19/09/14.
 */
public interface HomeActivityPresenter {
  void onResume();

  void selectTeam();

  void attachView(HomeActivityView homeActivityView);

  interface HomeActivityView {
    void initProgressFragment();

    void initMainFragment(EventCollection eventCollection);

    void initSelectTeamFragment();

    void initErrorFragment();
  }
}
