package com.jfilowk.teamfactory.ui.presenter;

import com.jfilowk.teamfactory.datasource.EventDataSource;
import com.jfilowk.teamfactory.datasource.EventDataSourceImpl;
import com.jfilowk.teamfactory.datasource.callbacks.EventCallback;
import com.jfilowk.teamfactory.datasource.entities.EventCollection;
import com.jfilowk.teamfactory.ui.views.GenerateTeamView;

/**
 * Created by Javi on 23/09/14.
 */
public class GenerateTeamPresenterImpl implements GenerateTeamPresenter {

    GenerateTeamView view;
    EventDataSource eventDataSource;

    public GenerateTeamPresenterImpl(GenerateTeamView view) {
        this.view = view;
        eventDataSource = new EventDataSourceImpl();
    }

    @Override
    public void onResume() {
        this.view.initProgressFragment();
        this.eventDataSource.showEvent(new EventCallback() {
            @Override
            public void onSuccess(EventCollection collection) {
                view.initMainFragment(collection);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void onError() {

    }
}