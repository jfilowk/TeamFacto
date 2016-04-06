package com.jfilowk.teamfactory.ui.presenter;

import com.jfilowk.teamfactory.datasource.entities.Event;

/**
 * Created by Javi on 23/09/14.
 */
public interface GenerateTeamPresenter {
  void onResume(Event event);

  void onError();

  void attachView(GenerateTeamView generateTeamView);

  interface GenerateTeamView {
    void initProgressFragment();

    void initMainFragment(Event collection);

    void initErrorFragment();
  }
}
