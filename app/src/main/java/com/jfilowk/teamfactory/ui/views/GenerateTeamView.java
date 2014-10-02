package com.jfilowk.teamfactory.ui.views;

import com.jfilowk.teamfactory.datasource.entities.Event;

/**
 * Created by Javi on 23/09/14.
 */
public interface GenerateTeamView {
    public void initProgressFragment();
    public void initMainFragment(Event collection);
    public void initErrorFragment();
}
