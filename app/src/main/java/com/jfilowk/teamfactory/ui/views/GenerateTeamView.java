package com.jfilowk.teamfactory.ui.views;

import com.jfilowk.teamfactory.datasource.entities.EventCollection;

/**
 * Created by Javi on 23/09/14.
 */
public interface GenerateTeamView {
    public void initProgressFragment();
    public void initMainFragment(EventCollection collection);
    public void initErrorFragment();
}
