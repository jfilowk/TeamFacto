package com.jfilowk.teamfactory.ui.presenter;

import com.jfilowk.teamfactory.datasource.entities.Event;

/**
 * Created by Javi on 23/09/14.
 */
public interface GenerateTeamPresenter {
    public void onResume(Event event);
    public void onError();
}
