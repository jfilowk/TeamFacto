package com.jfilowk.teamfactory.ui.presenter;

import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;

/**
 * Created by Javi on 26/09/14.
 */
public interface FragmentGenerateTeamPresenter {
    public void onResume();
    public void onError();
    public void showTeams(EventCollection eventCollection);
    public void createEvent(Event event);
}
