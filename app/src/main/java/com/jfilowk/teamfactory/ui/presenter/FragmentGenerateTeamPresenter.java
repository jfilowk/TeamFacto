package com.jfilowk.teamfactory.ui.presenter;

import com.jfilowk.teamfactory.datasource.entities.Event;

/**
 * Created by Javi on 26/09/14.
 */
public interface FragmentGenerateTeamPresenter {
  public void onResume();

  public void onError();

  public void showTeams(Event event);

  public void createEvent(Event event);
}
