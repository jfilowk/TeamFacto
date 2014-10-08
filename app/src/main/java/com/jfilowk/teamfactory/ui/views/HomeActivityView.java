package com.jfilowk.teamfactory.ui.views;

import com.jfilowk.teamfactory.datasource.entities.EventCollection;

/**
 * Created by Jose Luis on 19/09/14.
 */
public interface HomeActivityView {
    public void initProgressFragment();

    public void initMainFragment(EventCollection eventCollection);

    public void initSelectTeamFragment();

    public void initErrorFragment();
}
