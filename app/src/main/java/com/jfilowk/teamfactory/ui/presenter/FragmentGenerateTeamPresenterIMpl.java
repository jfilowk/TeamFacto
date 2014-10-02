package com.jfilowk.teamfactory.ui.presenter;

import android.util.Log;

import com.jfilowk.teamfactory.BuildConfig;
import com.jfilowk.teamfactory.datasource.EventDataSource;
import com.jfilowk.teamfactory.datasource.EventDataSourceImpl;
import com.jfilowk.teamfactory.datasource.cache.callback.EventCallbackBase;
import com.jfilowk.teamfactory.datasource.entities.Event;
import com.jfilowk.teamfactory.ui.views.FragmentGenerateTeamView;

/**
 * Created by Javi on 26/09/14.
 */
public class FragmentGenerateTeamPresenterImpl implements FragmentGenerateTeamPresenter {

    private FragmentGenerateTeamView view;
    private EventDataSource eventDataSource;

    public FragmentGenerateTeamPresenterImpl(FragmentGenerateTeamView view) {
        this.view = view;
        this.eventDataSource = new EventDataSourceImpl();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void showTeams(Event event) {
        this.view.initListView(event);
    }

    @Override
    public void createEvent(Event event) {
        eventDataSource.createEvent(event, new EventCallbackBase() {
            @Override
            public void onSuccess() {
             if (BuildConfig.DEBUG) Log.d("Team", "Hi team!");
            }

            @Override
            public void onError() {

            }
        });
    }
}
