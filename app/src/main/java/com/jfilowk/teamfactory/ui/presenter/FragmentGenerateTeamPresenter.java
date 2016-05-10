package com.jfilowk.teamfactory.ui.presenter;

import com.jfilowk.teamfactory.datasource.entities.Event;

/**
 * Created by Javi on 26/09/14.
 */
public interface FragmentGenerateTeamPresenter {
  void onResume();

  void onError();

  void showTeams(Event event);

  void createEvent(Event event);

  void attachView(FragmentGenerateTeamView fragmentGenerateTeamView);

  interface FragmentGenerateTeamView {
    void initListView(Event event);

    void createdOk();

    void initFragmentError();
  }
}
